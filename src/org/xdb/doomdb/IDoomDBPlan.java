package org.xdb.doomdb;

import java.util.Collection;
import java.util.Set;

public interface IDoomDBPlan {
	/**
	 * gets the estimated Time (in ms) how long the Query will need to finish
	 * 
	 * @return length of time in ms
	 */
	long getEstimatedTime();

	
	/**
	 * Returns a set of operators in plan
	 * 
	 * @return
	 */
	Set<String> getOperators();


	/**
	 * Get nodes running plan
	 * 
	 * @return
	 */
	Collection<String> getNodes();

	/**
	 * Get the compute node executing an operator
	 * 
	 * @param operation
	 * @return
	 */
	String getNodeForOperator(String opId);
	
	/**
	 * checks status of operator if it is alive 
	 * 
	 * @param opId
	 * @return
	 */
	boolean isAlive(String opId);
	
	/**
	 * Generates file with visualized plan and returns path to file
	 * 
	 * @return
	 */
	String tracePlan();
}
