package org.xdb.tracker;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.logging.XDBLog;
import org.xdb.utils.MutableInteger;
import org.xdb.utils.Tuple;

public class QueryTrackerNode {
	private final String[] nodes = {"127.0.0.1"};
	private final ComputeClient computeClient = new ComputeClient();

	private final MasterTrackerClient masterTrackerClient;
	private final QueryTrackerNodeDesc description;
	private final Logger logger;

	public QueryTrackerNode() {

		logger = XDBLog.getLogger(this.getClass().getName());
		masterTrackerClient = new MasterTrackerClient();
		description = new QueryTrackerNodeDesc(Config.QUERYTRACKER_URL);
		final Error err = masterTrackerClient.registerNode(description);
		if(err.isError()){
			throw new IllegalArgumentException(err.toString());
		}
	}

	//getters and setters
	public ComputeClient getComputeClient() {
		return computeClient;
	}

	/**
	 * Get a free node (currently: dummy method)
	 * @return
	 */
	public String getFreeNode(){
		//TODO: Implement resource management using master tracker

		final int idx = (int)(Math.random() * nodes.length);
		return nodes[idx];
	}

	public void executePlan(final QueryTrackerPlan plan) {
		plan.execute();
		final Error err = masterTrackerClient.noticeFreeSlots(plan.getSlots());
		if (err.isError()) {
			throw new RuntimeException(err.toString());
		}
	}

	/**
	 * Method used to request ComputeNode-Slots from MasterTracker
	 * @param requiredSlots
	 * @return
	 */
	public Tuple<Map<String, MutableInteger>, Error> requestComputeNodes(
			final Map<String, MutableInteger> requiredSlots) {
		final Tuple<Map<String, MutableInteger>, Error> tuple = masterTrackerClient.requestComputeNodes(requiredSlots);
		return tuple;
	}

	public Error operatorReady(final AbstractOperator op) {
		Error err = new Error();
		// Send READY Signal to Customers
		final Set<OperatorDesc> consumers = op.getConsumers();
		for (final OperatorDesc consumer : consumers) {
			if (consumer != null) {
				logger.log(Level.INFO,
						"Send READY_SIGNAL from operator " + op.getOperatorId()
						+ " to consumer: " + consumer);
				err = computeClient.executeOperator(op.getOperatorId(), consumer);
				if (err.isError()) {
					return err;
				}

			}
		}
		return err;
	}


}
