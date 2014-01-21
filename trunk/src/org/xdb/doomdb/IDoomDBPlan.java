package org.xdb.doomdb;

import java.util.Set;

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
}
