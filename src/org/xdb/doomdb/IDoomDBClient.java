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
	 * gets the current Node for a specific Operation
	 * @param operation
	 * @return
	 */
	ComputeNodeDesc getCurrentNode(Identifier opId);

	/**
	 * get Number of Nodes for Processing the Plan
	 * 
	 * @return Number of Nodes
	 */
	int getNodeCount();

	/**
	 * Kills a specific Node in the Tree
	 * 
	 * @param node
	 */
	void killNode(ComputeNodeDesc nodeDesc);

	/**
	 * recovers Node node from EStatus terminated to Estatus running.
	 * 
	 * @param node
	 */
	void recoverNode(ComputeNodeDesc nodeDesc);
}