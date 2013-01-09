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
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.client.QueryTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;


/**
 * Responsible to keep track of operators: 
 * - installs new operators 
 * - executes operators 
 * - closes operators 
 * on compute node
 * 
 * @author cbinnig
 * 
 */
public class ComputeNode {

	// Map of operatorId -> operator
	private final Map<Identifier, AbstractExecuteOperator> operators;

	// Map of consumer -> sources which are ready
	private final Map<Identifier, HashSet<Identifier>> receivedReadySignals;
	private final Lock readySignalsLock = new ReentrantLock();

	// Compute node slot description (i.e., available threads on node)
	private final ComputeNodeDesc computeNodeDesc;

	// Clients for communication
	private final ComputeClient computeClient;
	private final MasterTrackerClient mTrackerClient;
	
	// Helpers
	private final Logger logger;

	// constructors
	public ComputeNode() throws Exception {
		this.operators = Collections
				.synchronizedMap(new HashMap<Identifier, AbstractExecuteOperator>());
		this.receivedReadySignals = Collections
				.synchronizedMap(new HashMap<Identifier, HashSet<Identifier>>());

		final InetAddress addr = InetAddress.getLocalHost();
		computeNodeDesc = new ComputeNodeDesc(addr.getHostAddress());

		this.computeClient = new ComputeClient();
		this.mTrackerClient = new MasterTrackerClient();
		final Error err = mTrackerClient.registerNode(computeNodeDesc);
		if (err.isError()) {
			throw new IllegalArgumentException(err.toString());
		}
		
		logger = XDBLog.getLogger(this.getClass().getName());
	}


	/**
	 * Starts up compute node and drops all temporary
	 * tables of compute DB by recreating the database
	 * @return
	 */
	public Error startup() {
		// recreate tmp database of compute node
		Error err = new Error();
		try {

			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			Connection conn = DriverManager.getConnection(Config.COMPUTE_DB_URL,
					Config.COMPUTE_DB_USER, Config.COMPUTE_DB_PASSWD);
			Statement stmt = conn.createStatement();
			stmt.execute("DROP DATABASE IF EXISTS "+Config.COMPUTE_DB_NAME);
			stmt.execute("CREATE DATABASE "+Config.COMPUTE_DB_NAME);
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
	 * Receives signals of input operators which are ready and 
	 * executes consuming operator if all inputs are ready
	 * 
	 * @param signal
	 * @return
	 */
	public Error signalOperator(final ReadySignal signal) {
		Error err = new Error();

		final Identifier source = signal.getSource();
		final Identifier consumer = signal.getConsumer();

		// Get signaled operator
		AbstractExecuteOperator op = null;
		op = operators.get(consumer);
		if (op == null) {
			return err;
		}

		logger.log(Level.INFO,
				"Received READY_SIGNAL for operator: " + op.getOperatorId()
						+ " from source: " + source);

		// Add signaling source to list of finished sources
		boolean execute = false;
		this.readySignalsLock.lock();
		HashSet<Identifier> sourceIds = null;
		if (!this.receivedReadySignals.containsKey(consumer)) {
			sourceIds = new HashSet<Identifier>();
			this.receivedReadySignals.put(source, sourceIds);
		} else {
			sourceIds = this.receivedReadySignals.get(source);
		}
		sourceIds.add(source);

		if (sourceIds.containsAll(op.getSourceIds())) {
			logger.log(Level.INFO, "All READY_SIGNALs received for operator: "
					+ op.getOperatorId());
			execute = true;
		}
		readySignalsLock.unlock();

		// execute operator 
		if (execute) {
			err = executeOperator(op);
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
	private Error executeOperator(final AbstractExecuteOperator op) {
		Error err = new Error();

		// execute operator
		err = op.execute();
		if (err.isError()) {
			return err;
		}

		// send READY_SIGNAL to QueryTracker
		QueryTrackerClient queryTrackerClient = op.getQueryTrackerClient();
		if (queryTrackerClient != null && Config.COMPUTE_SIGNAL2QUERY_TRACKER) {
			logger.log(Level.INFO,
					"Send READY_SIGNAL from operator " + op.getOperatorId()
							+ " to Query Tracker "+queryTrackerClient.getUrl());
			err = queryTrackerClient.operatorReady(op);
		} 
		// send READY_SIGNAL directly to consumer on local node using w/o query tracker
		else {
			final Set<OperatorDesc> consumers = op.getConsumers();
			for (final OperatorDesc consumer : consumers) {
				if (consumer != null) {
					logger.log(Level.INFO, "Send READY_SIGNAL from operator "
							+ op.getOperatorId() + " to consumer: " + consumer);

					err = computeClient.executeOperator(op.getOperatorId(),
							consumer);
					if (err.isError()) {
						return err;
					}
				}
			}
		}

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