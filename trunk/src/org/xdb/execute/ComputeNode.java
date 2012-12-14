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
 * Responsible to keep track of operators: - installs new operators - executes
 * operators - closes operators on one node
 * 
 * @author cbinnig
 * 
 */
public class ComputeNode {

	// Map of operatorId -> operator
	private final Map<Identifier, AbstractExecuteOperator> operators;

	// Map of consumer -> sources which are ready
	private final Map<Identifier, HashSet<Identifier>> readySignals;
	private final Lock readySignalsLock = new ReentrantLock();

	// computing slots
	private final ComputeNodeDesc computeNodeDesc;

	// Clients
	private final ComputeClient computeClient;
	private final MasterTrackerClient mTrackerClient;
	private final QueryTrackerClient queryTrackerClient;
	
	// Helpers
	private final Logger logger;
	private Error err = new Error();

	// constructors
	public ComputeNode() throws Exception {
		operators = Collections
				.synchronizedMap(new HashMap<Identifier, AbstractExecuteOperator>());
		readySignals = Collections
				.synchronizedMap(new HashMap<Identifier, HashSet<Identifier>>());

		logger = XDBLog.getLogger(this.getClass().getName());
		computeClient = new ComputeClient();
		mTrackerClient = new MasterTrackerClient();
		
		if (Config.COMPUTE_SIGNAL2QUERY_TRACKER) {
			//TODO: use correct url of query tracker
			queryTrackerClient = new QueryTrackerClient(
					Config.QUERYTRACKER_URL, Config.QUERYTRACKER_PORT);
		}
		else{
			queryTrackerClient = null;
		}

		final InetAddress addr = InetAddress.getLocalHost();
		computeNodeDesc = new ComputeNodeDesc(addr.getHostAddress(),
				Config.COMPUTE_SLOTS);

		final Error err = mTrackerClient.registerNode(computeNodeDesc);
		if (err.isError()) {
			throw new IllegalArgumentException(err.toString());
		}
	}

	/**
	 * Installs a new operator and prepares operator for execution
	 * 
	 * @param operator
	 * @return
	 */
	public Error openOperator(final AbstractExecuteOperator op) {
		operators.put(op.getOperatorId(), op);

		// open operator
		logger.log(Level.INFO, "Open operator: " + op.getOperatorId());
		err = op.open();

		return err;
	}

	/**
	 * Receives signals of inputs which are ready and executes operator when all
	 * inputs are ready
	 * 
	 * @param signal
	 * @return
	 */
	public Error signalOperator(final ReadySignal signal) {
		final Identifier source = signal.getSource();
		final Identifier consumer = signal.getConsumer();

		// Get node
		AbstractExecuteOperator op = null;
		op = operators.get(consumer);
		if (op == null) {
			return err;
		}

		logger.log(Level.INFO,
				"Received READY_SIGNAL for operator: " + op.getOperatorId()
						+ " from source: " + source);

		// Add source to sources
		boolean execute = false;
		readySignalsLock.lock();
		HashSet<Identifier> sourceIds = null;
		if (!readySignals.containsKey(consumer)) {
			sourceIds = new HashSet<Identifier>();
			readySignals.put(source, sourceIds);
		} else {
			sourceIds = readySignals.get(source);
		}
		sourceIds.add(source);

		if (sourceIds.containsAll(op.getSourceIds())) {
			logger.log(Level.INFO, "All signals received to execute operator: "
					+ op.getOperatorId());
			execute = true;
		}
		readySignalsLock.unlock();

		// execute operator and send signal to consumer
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

		// send READY signal to QueryTracker
		if (queryTrackerClient != null && Config.COMPUTE_SIGNAL2QUERY_TRACKER) {
			logger.log(Level.INFO,
					"Send READY_SIGNA from operator " + op.getOperatorId()
							+ " to QueryTracker "+queryTrackerClient.getUrl());
			err = queryTrackerClient.operatorReady(op);
		} 
		// send READY signal directly to consumer on local node
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
		operators.remove(op.getOperatorId());
		readySignals.remove(op.getOperatorId());
	}
	
	/**
	 * Starts up compute node
	 * @return
	 */
	public Error startup() {

		
		// open connection and compile/execute statements
		try {

			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			Connection conn = DriverManager.getConnection(Config.COMPUTE_DB_URL,
					Config.COMPUTE_DB_USER, Config.COMPUTE_DB_PASSWD);
			Statement stmt = conn.createStatement();
			stmt.execute("DROP DATABASE "+Config.COMPUTE_DB_NAME);
			stmt.execute("CREATE DATABASE "+Config.COMPUTE_DB_NAME);
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			this.err = createMySQLError(e);
		} catch (Exception e) {
			this.err = createMySQLError(e);
		}

		return this.err;
	}
	
	/**
	 * Create MYSQL_ERROR from an exception
	 * 
	 * @param e
	 *            Exception
	 * @return Error
	 */
	protected Error createMySQLError(Exception e) {
		String[] args = { e.toString() + "," + e.getCause() };
		Error err = new Error(EnumError.MYSQL_ERROR, args);
		return err;
	}
}
