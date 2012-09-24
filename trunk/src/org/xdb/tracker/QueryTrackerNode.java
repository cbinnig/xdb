package org.xdb.tracker;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;

public class QueryTrackerNode {
	private final String[] nodes = {"127.0.0.1"};
	private final ComputeClient computeClient = new ComputeClient();

	private final MasterTrackerClient masterTrackerClient;
	private final QueryTrackerNodeDesc description;

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


}
