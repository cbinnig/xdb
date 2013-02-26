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
		switch(Config.RESOURCE_SCHEDULER){
		case SIMPLE_SCHEDULER:
			return new SimpleResourceScheduler(plan);
		case LOCALITY_AWARE_SCHEDULER:
			return new LocalityAwareScheduler(plan);
		
		}
		return new SimpleResourceScheduler(plan);
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
