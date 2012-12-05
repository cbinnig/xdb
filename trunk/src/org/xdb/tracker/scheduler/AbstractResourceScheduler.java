package org.xdb.tracker.scheduler;

import org.xdb.Config;
import org.xdb.tracker.QueryTrackerPlan;

/**
 * Abstract class for resource scheduling
 * @author cbinnig
 *
 */
public abstract class AbstractResourceScheduler {
	public static final String RANDOM = "RANDOM CONNECTION";
	protected final QueryTrackerPlan plan;
	protected EnumResourceScheduler type;
	
	public AbstractResourceScheduler(final QueryTrackerPlan plan) {
		this.plan = plan;
	}

	public abstract int calcMaxParallelization();
	
	public static AbstractResourceScheduler createScheduler(final QueryTrackerPlan plan){
		switch(Config.RESOURCE_SCHEDULER){
		case SIMPLE_SCHEDULER:
			return new SimpleResourceScheduler(plan);
		}
		return new SimpleResourceScheduler(plan);
	}
}
