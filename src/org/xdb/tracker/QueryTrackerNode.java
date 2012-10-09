package org.xdb.tracker;

import java.util.Set;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;
import org.xdb.server.QueryTrackerServer;
import org.xdb.utils.Identifier;
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
		for (final Identifier leaf : leaves) {
			final Tuple<String, Error> tuple = masterTrackerClient.requestComputeNode();
			final String computeNode = tuple.getObject1();
			final Error error = tuple.getObject2();
			final ComputeClient computeClient = new ComputeClient(computeNode, Config.COMPUTE_PORT);
			//			computeClient.executeOperator(...)
		}

		// TODO further actions need due to executing
	}


}
