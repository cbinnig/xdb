package org.xdb.tracker;

import java.util.List;

/**
 * Helper class used to perform actions on QueryTrackerPlan optimization and execution
 * @author Timo Jacobs
 *
 */
public class ResourceScheduler {
	public static final String RANDOM = "RANDOM CONNECTION";
	private final QueryTrackerPlan plan;

	public ResourceScheduler(final QueryTrackerPlan plan) {
		this.plan = plan;
	}

	public int calcMaxParallelization() {
		// maximum >= num(leaves)
		//int maximum = plan.getLeaves().size();

		// TODO should be implemented later

		return plan.getOperators().size();
	}

	public String getBestConnection(final List<String> inTableConnection) {
		// TODO implement better logic
		if (inTableConnection != null && !inTableConnection.isEmpty()) {
			return inTableConnection.get(0);
		}
		return null;
	}

}
