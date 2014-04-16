package org.xdb.doomdb;

/**
 * Interface for DoomDB Client
 * 
 * @author cbinnig
 *
 */
public interface IDoomDBClient {

	/**
	 * Starts DB and results true if possible
	 * @return
	 */
	void startDB();
	
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
	 * gets the estimated Time (in ms) how long the Query will need to finish
	 * 
	 * @return length of time in ms
	 */
	long getEstimatedTime();

	/**
	 * Starts execution of the Query
	 */
	void startQuery();
	
	/**
	 * Stops execution of the Query
	 */
	void stopQuery();
	
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
	 * is node alive
	 * @param nodeDesc
	 * @return
	 */
	boolean nodeAlive(String nodeDesc);
	
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