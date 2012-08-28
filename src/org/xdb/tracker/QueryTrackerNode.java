package org.xdb.tracker;

import org.xdb.client.ComputeClient;

public class QueryTrackerNode {
	private String[] nodes = {"127.0.0.1"};
	private ComputeClient computeClient = new ComputeClient();

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
		
		int idx = (int)(Math.random() * nodes.length);
		return nodes[idx];
	}
}
