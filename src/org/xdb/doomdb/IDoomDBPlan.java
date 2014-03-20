package org.xdb.doomdb;

import java.util.Collection;
import java.util.Set;

import org.xdb.execute.ComputeNodeDesc;

public interface IDoomDBPlan {
	/**
	 * Returns a set of operators in plan
	 * @return
	 */
	 Set<String> getOperators();
	 
	 /**
	  * Generates file with visualized plan and returns path to file
	  * @return
	  */
	 String tracePlan(); 
	 
	 Collection<ComputeNodeDesc> getNodes();
}
