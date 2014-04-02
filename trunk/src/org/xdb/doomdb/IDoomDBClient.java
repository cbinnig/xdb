package org.xdb.doomdb;

public interface IDoomDBClient {

	/**
	 * Starts DB and results true if possible
	 * @return
	 */
	boolean startDB();
	
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
	void setQuery(int queryNum);

	
	/**
	 * Return query string
	 * @return
	 */
	String getQuery();

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
	 * sets MTTR = mean time to repair a compute node in s
	 * @param time
	 */
	void setMTTR(int time);
	
	/**
	 * Set MTBF = mean time between failure a compute node in s
	 * @param time
	 */
	void setMTBF(int time);
	
	/**
	 * Get MTTR
	 * @return
	 */
	int getMTTR();
	
	/**
	 * Get MTBF
	 * @return
	 */
	int getMTBF();
}