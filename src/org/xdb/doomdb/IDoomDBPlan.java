package org.xdb.doomdb;

import java.util.Collection;
import java.util.Set;

/**
 * INterface for DoomDB Plan
 * @author cbinnig
 *
 */
public interface IDoomDBPlan {
	
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
	 * is node alive
	 * @param nodeDesc
	 * @return
	 */
	boolean nodeAlive(String nodeDesc);

	/**
	 * Get the compute node executing an operator
	 * 
	 * @param operation
	 * @return
	 */
	String getNodeForOperator(String opId);
	
	/**
	 * Generates file with visualized plan and returns path to file
	 * 
	 * @return
	 */
	String tracePlan();
}
