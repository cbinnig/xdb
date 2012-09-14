package org.xdb.execute;

import java.net.InetAddress;
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
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractOperator;
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
	private Map<Identifier, AbstractOperator> operators;

	// Map of consumer -> sources which are ready
	private Map<Identifier, HashSet<Identifier>> readySignals;
	private final Lock readySignalsLock = new ReentrantLock();

	//computing slots
	private ComputeNodeDesc computeNodeDesc;
	
	// Helpers
	private Logger logger;
	private ComputeClient computeClient;
	private MasterTrackerClient mTrackerClient;

	// constructors
	public ComputeNode() throws Exception  {
		this.operators = Collections
				.synchronizedMap(new HashMap<Identifier, AbstractOperator>());
		this.readySignals = Collections
				.synchronizedMap(new HashMap<Identifier, HashSet<Identifier>>());

		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.computeClient = new ComputeClient();
		this.mTrackerClient = new MasterTrackerClient();
		
		InetAddress addr = InetAddress.getLocalHost();
		this.computeNodeDesc = new ComputeNodeDesc(addr.getHostAddress(), Config.COMPUTE_SLOTS);
		
		Error err = this.mTrackerClient.registerNode(computeNodeDesc);
		if(err.isError()){
			throw new IllegalArgumentException(err.toString());
		}
	}

	/**
	 * Installs a new operator and prepares operator for execution
	 * 
	 * @param operator
	 * @return
	 */
	public Error openOperator(AbstractOperator op) {
		Error err = Error.NO_ERROR;

		this.operators.put(op.getOperatorId(), op);

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
	public Error signalOperator(ReadySignal signal) {
		Error err = Error.NO_ERROR;
		Identifier source = signal.getSource();
		Identifier consumer = signal.getConsumer();

		// Get node
		AbstractOperator op = null;
		op = this.operators.get(consumer);
		if (op == null)
			return err;

		logger.log(Level.INFO,
				"Received READY_SIGNAL for operator: " + op.getOperatorId()
						+ " from source: " + source);

		// Add source to sources
		boolean execute = false;
		this.readySignalsLock.lock();
		HashSet<Identifier> sourceIds = null;
		if (!this.readySignals.containsKey(consumer)) {
			sourceIds = new HashSet<Identifier>();
			this.readySignals.put(source, sourceIds);
		} else {
			sourceIds = this.readySignals.get(source);
		}
		sourceIds.add(source);

		if (sourceIds.containsAll(op.getSourceIds())) {
			logger.log(Level.INFO, "All signals received to execute operator: "
					+ op.getOperatorId());
			execute = true;
		}
		this.readySignalsLock.unlock();

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
	public Error closeOperator(CloseSignal signal) {
		Error err = Error.NO_ERROR;

		// execute operator
		AbstractOperator op = this.operators.get(signal.getConsumer());
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
	private Error executeOperator(AbstractOperator op) {
		Error err = Error.NO_ERROR;

		// execute operator
		err = op.execute();
		if (err.isError())
			return err;

		// send READY signal to consumer
		Set<OperatorDesc> consumers = op.getConsumers();
		for (OperatorDesc consumer : consumers) {
			if (consumer != null) {
				logger.log(Level.INFO,
						"Send READY_SIGNAL from operator " + op.getOperatorId()
								+ " to consumer: " + consumer);

				err = this.computeClient.executeOperator(op.getOperatorId(), consumer);
				if (err.isError())
					return err;

			}
		}

		return err;
	}

	/**
	 * Removes operator from node
	 * 
	 * @param op
	 */
	private synchronized void removeOperator(AbstractOperator op) {
		this.operators.remove(op.getOperatorId());
		this.readySignals.remove(op.getOperatorId());
	}
}
