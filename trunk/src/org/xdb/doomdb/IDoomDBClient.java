package org.xdb.doomdb;


public interface IDoomDBClient {

	/**
	 * Create database schema selected when starting the game
	 * @param schema
	 */
	void setSchema(String schemaName);
	
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
	IDoomDBPlan getPlan();
	
	/**
	 * gets the estimated Time (in ms) how long the Query will need to finish
	 * 
	 * @return length of time in ms
	 */
	long getEstimatedTime();

	
	/**
	 * checks status of operator if it is alive 
	 * 
	 * @param opId
	 * @return
	 */
	boolean isAlive(String opId);
	
	/**
	 * gets the compute node executing an operator
	 * @param operation
	 * @return
	 */
	String getNode(String opId);

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
	void killNode(String nodeDesc);

	
	/**
	 * sets MTTR = mean time to repair a compute node in ms
	 * @param time
	 */
	void setMTTR(int time);
}