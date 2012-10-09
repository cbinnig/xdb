package org.xdb.tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;
import org.xdb.server.QueryTrackerServer;
import org.xdb.tracker.operator.AbstractOperator;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;
import org.xdb.utils.Tuple;

public class QueryTrackerNode {
	private final String[] nodes = {"127.0.0.1"};
	private final ComputeClient computeClient = new ComputeClient();

	private final MasterTrackerClient masterTrackerClient;
	private final QueryTrackerNodeDesc description;
	private QueryTrackerServer server;

	public QueryTrackerNode() {
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
		final Set<Identifier> leaves = plan.getLeaves();
		final Map<String, MutableInteger> requiredSlots = new HashMap<String, MutableInteger>();
		for (final AbstractOperator op : plan.getOperators()) {
			final String computeNode = plan.getComputeNode(op);
			final MutableInteger numNodes = requiredSlots.get(computeNode);
			if (numNodes == null) {
				requiredSlots.put(computeNode, new MutableInteger(1));
			} else {
				numNodes.inc();
			}
		}
		final Tuple<Map<String, MutableInteger>, Error> tuple = masterTrackerClient.requestComputeNodes(requiredSlots);
		final Map<String, MutableInteger> allocatedSlots = tuple.getObject1();
		final Error error = tuple.getObject2();

		final Map<Identifier, AbstractOperator> operators = plan.getOperatorMapping();
		for (final Identifier leaf : leaves) {
			final String bestNode = plan.getComputeNode(operators.get(leaf));
			final MutableInteger numOfFreeNodes = allocatedSlots.get(bestNode);
			String usedNode = null;
			if (numOfFreeNodes == null) {
				// TODO: Get next best ComputeNode
			} else {
				numOfFreeNodes.dec();
				usedNode = bestNode;
			}
			final ComputeClient computeClient = new ComputeClient(usedNode, Config.COMPUTE_PORT);
			//			computeClient.executeOperator(...)
		}

		// TODO further actions need due to executing
	}


}
