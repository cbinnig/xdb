package org.xdb.execute;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.client.MasterTrackerClient;
import org.xdb.client.QueryTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractExecuteOperator;

import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.KillSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.logging.XDBExecuteTimeMeasurement;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;

/**
 * Responsible to keep track of operators: - installs new operators - executes
 * operators - closes operators on compute node
 * 
 * @author cbinnig
 * 
 */
public class ComputeNode {

	// Map of operatorId -> operator
	private final Map<Identifier, AbstractExecuteOperator> operators = Collections
	.synchronizedMap(new HashMap<Identifier, AbstractExecuteOperator>());;

	// Map of consumer -> sources which are ready
	private final Map<Identifier, HashSet<Identifier>> receivedReadySignals = Collections
			.synchronizedMap(new HashMap<Identifier, HashSet<Identifier>>());; 
	
	// Map of operator ->  which are ready
	public static final Map<Identifier, Long> executingOperator = Collections
			.synchronizedMap(new HashMap<Identifier, Long>());; 
			
	private final Lock readySignalsLock = new ReentrantLock();

	// Compute node description (i.e., available threads on node)
	private final ComputeNodeDesc computeNodeDesc;

	// Clients for communication
	private final MasterTrackerClient mTrackerClient;

	// Helpers
	private final Logger logger;
	private final XDBExecuteTimeMeasurement timeMeasure;

	// constructors
	public ComputeNode(final int port) throws Exception {
		String url = InetAddress.getLocalHost().getHostAddress();
		this.computeNodeDesc = new ComputeNodeDesc(url, port);

		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.timeMeasure = XDBExecuteTimeMeasurement
				.getXDBExecuteTimeMeasurement("op_runtime");
		
		//register at master tracker
		this.mTrackerClient = new MasterTrackerClient();
	}

	public ComputeNodeDesc getComputeNode(){
		return this.computeNodeDesc;
	}
	
	/**
	 * Starts up compute node and drops all temporary tables of compute DB by
	 * recreating the database
	 * 
	 * @return
	 */
	public Error startup() {
		//register at master tracker
		Error err = mTrackerClient.registerNode(computeNodeDesc);
		if(err.isError())
			return err;
		
		// recreate XDB temporary database of compute node
		try {

			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			Connection conn = DriverManager.getConnection(
					Config.COMPUTE_DB_URL, Config.COMPUTE_DB_USER,
					Config.COMPUTE_DB_PASSWD);
			Statement stmt = conn.createStatement();
			stmt.execute("DROP DATABASE IF EXISTS " + Config.COMPUTE_DB_NAME);
			stmt.execute("CREATE DATABASE " + Config.COMPUTE_DB_NAME);
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			err = createMySQLError(e);
		} catch (Exception e) {
			err = createMySQLError(e);
		}

		return err;
	}

	/**
	 * Installs a new operator and prepares operator for execution
	 * 
	 * @param operator
	 * @return
	 */
	public Error openOperator(final AbstractExecuteOperator op) {
		Error err = new Error();

		operators.put(op.getOperatorId(), op);

		// open operator
		logger.log(Level.INFO, "Open operator: " + op.getOperatorId());
		err = op.open();

		return err;
	}

	/**
	 * Receives signals of input operators which are ready and executes
	 * consuming operator if all inputs are ready
	 * 
	 * @param signal
	 * @return
	 */
	public Error signalOperator(final ReadySignal signal) {
		Error err = new Error();

		final Identifier srcExecuteOpId = signal.getSource();
		final Identifier srcTrackerOpId = (srcExecuteOpId.equals(Config.COMPUTE_NOOP_ID))?srcExecuteOpId:srcExecuteOpId.getParentId(1);
		final Identifier consumerExecuteOpId = signal.getConsumer(); 
		final Identifier consumerTrackerOpId = (consumerExecuteOpId.equals(Config.COMPUTE_NOOP_ID))?consumerExecuteOpId:consumerExecuteOpId.getParentId(1);


		// Get signaled operator
		AbstractExecuteOperator op = null;
		op = operators.get(consumerExecuteOpId);
		if (op == null) {
			return err;
		}


		logger.log(Level.INFO, "Received READY_SIGNAL for operator: "
				+ consumerExecuteOpId + " from source: " + srcExecuteOpId);

		// Add signaling source to list of finished sources
		boolean execute = false;
		this.readySignalsLock.lock();
		HashSet<Identifier> sourceTrackerIds = null;
		if (!this.receivedReadySignals.containsKey(consumerTrackerOpId)) {
			sourceTrackerIds = new HashSet<Identifier>();
			this.receivedReadySignals.put(consumerTrackerOpId, sourceTrackerIds);
		} else {
			sourceTrackerIds = this.receivedReadySignals.get(consumerTrackerOpId);
		}
		sourceTrackerIds.add(srcTrackerOpId);

		if (sourceTrackerIds.containsAll(op.getSourceTrackerIds())) {
			
			logger.log(Level.INFO, "All READY_SIGNALs received for operator: "
					+ op.getOperatorId()+", "+op.getSourceTrackerIds());
			execute = true;
		}
		readySignalsLock.unlock();

		// execute operator
		if (execute) { 
			OperatorExecutor executor = new OperatorExecutor(op);  
			executingOperator.put(op.getOperatorId(), executor.getId());
			executor.start(); 
			return executor.getLastError();
		}

		return err;
	}

	/**
	 * Closes operator when result is consumed
	 * 
	 * @param signal
	 * @return
	 */
	public Error closeOperator(final CloseSignal signal) {
		Error err = new Error();

		// execute operator
		final AbstractExecuteOperator op = operators.get(signal.getConsumer());
		if (op != null) {
			err = op.close();
			logger.log(Level.INFO, "Closed operator: " + op.getOperatorId());

			removeOperator(op);
		}

		return err;
	}

	/**
	 * Executes operator and signals consumer
	 * 
	 * @param op
	 * @return
	 */
	private class OperatorExecutor extends Thread {
		private AbstractExecuteOperator op;
		private Error err = new Error();
		
		public OperatorExecutor(AbstractExecuteOperator op) {
			super();
			this.op = op;
		}
		
		public Error getLastError(){
			return this.err;
		}

		private void executeOperator(final AbstractExecuteOperator op) {
			// start timer
			timeMeasure.start(op.getOperatorId().toString());
			
			// execute operator
			this.err = op.execute();
				
			// stop timer
			timeMeasure.stop(op.getOperatorId().toString());
			
			// send READY_SIGNAL to QueryTracker who takes care of error handling
			QueryTrackerClient queryTrackerClient = op.getQueryTrackerClient();
			logger.log(
					Level.INFO,
					"Send READY_SIGNAL from operator " + op.getOperatorId()
							+ " to Query Tracker "
							+ queryTrackerClient.getUrl()); 
			ComputeNode.executingOperator.remove(op.getOperatorId());
			this.err = queryTrackerClient.operatorReady(op);
		}

		@Override
		public void run() {  
			
			this.executeOperator(op);
		}
	} 
	
	/**
	 * kill the failed operator if it is running, otherwise 
	 * remove it from the execution plan. 
	 * 
	 * @param killSignal the failed operator op id. 
	 * @return
	 */
	public Error killOperator(KillSignal killSignal) {
		
		Error err = new Error (); 
		Identifier failedExecOpId = killSignal.getFailedExecOpId(); 
		
		final AbstractExecuteOperator op = operators.get(failedExecOpId);
		 
		if(!executingOperator.containsKey(failedExecOpId)) {  
			logger.log(Level.INFO, "Failed Operator "+failedExecOpId+" has been removed from the execution plan");
			this.removeOperator(op);  
		} else {
			Set<Thread> threadSet = Thread.getAllStackTraces().keySet(); 
			Long failedOpThreadId = executingOperator.get(failedExecOpId); 
			for (Thread thread : threadSet) { 
				if(thread != null && thread.getId() == failedOpThreadId){
					logger.log(Level.INFO, "Failed Operator "+failedExecOpId +" running on thread "+thread.getId()+" has been interrupted"); 
					thread.interrupt();
					break;
					
				}
			} 

		} 
		
		this.receivedReadySignals.remove(op.getOperatorId().getParentId(1));	    
		return err;
	} 
	
	public Error restartComputeNode(){
		Error err = new Error(); 
		// restart the mysql server 
		// sleep the meantime between failure 
		
		
		return err; 
	}

	/**
	 * Removes operator from node
	 * 
	 * @param op
	 */
	private synchronized void removeOperator(final AbstractExecuteOperator op) {
		this.operators.remove(op.getOperatorId());
		this.receivedReadySignals.remove(op.getOperatorId());

	}

	/**
	 * Create MYSQL_ERROR from an exception
	 * 
	 * @param e
	 *            Exception
	 * @return Error
	 */
	private Error createMySQLError(Exception e) {
		String[] args = { e.toString() + "," + e.getCause() };
		Error err = new Error(EnumError.MYSQL_ERROR, args);
		return err;
	}

	
}
