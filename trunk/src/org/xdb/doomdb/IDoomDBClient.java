package org.xdb.doomdb;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.utils.Identifier;

public interface IDoomDBClient {

	void setSchema(DoomDBSchema schema);
	
	/**
	 * Sets the Query of the Game with String query
	 * 
	 * @param query
	 */
	void setQuery(String query);

	/**
	 * Starts execution of the Query
	 */
	void startQuery();
	
	/**
	 * returns true if the Query is finished.
	 * 
	 * @return
	 */
	boolean isQueryFinished();
	
	/**
	 * gets the plan of the query
	 * 
	 * @return ITree<String>
	 */
	DoomDBPlan getPlan();
	
	/**
	 * gets the estimated Time (in ms) how long the Query will need to finish
	 * 
	 * @return length of time in ms
	 */
	long getEstimatedTime();

	
	/**
	 * gets the compute node executing an operator
	 * @param operation
	 * @return
	 */
	ComputeNodeDesc getNode(Identifier opId);

	/**
	 * get number of nodes for processing the plan
	 * 
	 * @return Number of Nodes
	 */
	int getNodeCount();

	/**
	 * kills a specific compute node 
	 * @param nodeDesc
	 */
	void killNode(ComputeNodeDesc nodeDesc);

	/**
	 * sets MTTR = mean time to repair a compute node in ms
	 * @param time
	 */
	void setMTTR(int time);
}