package org.xdb.doomdb;

import java.util.List;

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
	 * gets the current Node for a specific Operation in the Tree
	 * 
	 * @param operation
	 * @return Tree<String>
	 */
	String getCurrentNode(String operation);

	
	/**
	 * gets all possible Nodes in the Tree that can run a specific Operation
	 * 
	 * @param operation
	 * @return ArrayList<Tree<String>>
	 */
	List<String> getPossibleNodes(String operation);

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
	void killNode(String node);

	/**
	 * gets the estimated Time (in ms) how long the Query will need to finish
	 * 
	 * @return length of time in ms
	 */
	long getTime();

	/**
	 * recovers Node node from EStatus terminated to Estatus running.
	 * 
	 * @param node
	 */
	void recoverNode(String node);
}