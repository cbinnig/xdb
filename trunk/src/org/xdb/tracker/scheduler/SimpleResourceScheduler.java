package org.xdb.tracker.scheduler;

import org.xdb.tracker.QueryTrackerPlan;


/**
 * Simple resource scheduler
 * @author cbinnig
 *
 */
public class SimpleResourceScheduler extends AbstractResourceScheduler{
	
	protected SimpleResourceScheduler(final QueryTrackerPlan plan) {
		super(plan);
		
		this.type = EnumResourceScheduler.SIMPLE_SCHEDULER;
	}

	public int calcMaxParallelization() {
		return plan.getOperators().size();
	}

}
