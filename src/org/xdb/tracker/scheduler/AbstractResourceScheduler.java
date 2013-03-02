package org.xdb.tracker.scheduler;


import java.util.Map;

import org.xdb.Config;
import org.xdb.execute.ComputeNodeSlot;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;

/**
 * Abstract class for resource scheduling
 * @author cbinnig
 *
 */
public abstract class AbstractResourceScheduler {
	public static final String RANDOM = "RANDOM CONNECTION";
	
	protected final QueryTrackerPlan plan;
	protected EnumResourceScheduler type;
	private static EnumResourceScheduler usedScheduler = Config.RESOURCE_SCHEDULER;
	
	//constructor
	public AbstractResourceScheduler(final QueryTrackerPlan plan) {
		this.plan = plan;
	}
	
	/**
	 * Creates scheduler for given query tracker plan plan
	 * @param plan
	 * @return
	 */
	public static AbstractResourceScheduler createScheduler(final QueryTrackerPlan plan){
		switch(usedScheduler){
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
		usedScheduler = Config.RESOURCE_SCHEDULER;
	}
	
	/**
	 * Creates wish-list of compute slots to execute plan
	 * @return
	 */
	public abstract Map<String, MutableInteger> calcRequiredSlots();
	
	/**
	 * Assigns available compute slots to operators of plan
	 * @param slots
	 */
	public abstract void assignSlots(final Map<ComputeNodeSlot, MutableInteger> slots);
	
	/**
	 * Returns assigned compute slot after slots have been assigned
	 * @param operId
	 * @return
	 */
	public abstract ComputeNodeSlot getSlot(final Identifier operId);
}
