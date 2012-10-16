package org.xdb.tracker;

import java.util.Map;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;
import org.xdb.server.QueryTrackerServer;
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
		plan.execute();
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


}
