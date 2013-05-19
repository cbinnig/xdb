package org.xdb.tracker.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.xdb.Config;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;

/**
 * Abstract class for resource scheduling
 * 
 * @author cbinnig
 * 
 */
public abstract class AbstractResourceScheduler {
	public static final String RANDOM_COMPUTE_NODE = "";

	protected final QueryTrackerPlan plan;
	protected EnumResourceScheduler type;
	private static EnumResourceScheduler usedScheduler = Config.QUERYTRACKER_SCHEDULER;

	// assigned slots which are actually available: compute slot URL -> count
	protected Map<String, ComputeNodeDesc> assignedComputeNodesSlots = new HashMap<String, ComputeNodeDesc>();

	// constructor
	public AbstractResourceScheduler(final QueryTrackerPlan plan) {
		this.plan = plan;
	}

	/**
	 * Creates scheduler for given query tracker plan plan
	 * 
	 * @param plan
	 * @return
	 */
	public static AbstractResourceScheduler createScheduler(
			final QueryTrackerPlan plan) {
		switch (usedScheduler) {
		case SIMPLE_SCHEDULER:
			return new SimpleResourceScheduler(plan);
		case LOCALITY_AWARE_SCHEDULER:
			return new LocalityAwareScheduler(plan);

		}
		return new SimpleResourceScheduler(plan);
	}

	/**
	 * Changes the used scheduler
	 */
	public static void changeScheduler(EnumResourceScheduler newScheduler) {
		usedScheduler = newScheduler;
	}

	/**
	 * Resets the used scheduler to the original one
	 */
	public static void resetScheduler() {
		usedScheduler = Config.QUERYTRACKER_SCHEDULER;
	}

	/**
	 * Creates wish-list of compute slots to execute plan
	 * 
	 * @return
	 */
	public abstract Set<String> createComputeNodesWishList();

	/**
	 * Assigns available compute slots to operators of plan
	 * 
	 * @param slots
	 */
	public void assignComputeNodes(Map<String, ComputeNodeDesc> nodes) {
		this.assignedComputeNodesSlots.putAll(nodes);
	}

	/**
	 * Returns assigned compute slot after slots have been assigned
	 * 
	 * @param operId
	 * @return
	 */
	public abstract ComputeNodeDesc getComputeNode(final Identifier operId);
}
