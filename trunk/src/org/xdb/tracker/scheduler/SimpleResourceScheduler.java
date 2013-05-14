package org.xdb.tracker.scheduler;

import java.util.HashSet;
import java.util.Set;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;

/**
 * Simple resource scheduler which assigns compute slots randomly to operators 
 * 
 * @author cbinnig
 * 
 */
public class SimpleResourceScheduler extends AbstractResourceScheduler {

	protected SimpleResourceScheduler(final QueryTrackerPlan plan) {
		super(plan);

		this.type = EnumResourceScheduler.SIMPLE_SCHEDULER;
	}

	@Override
	public Set<String> calcRequiredSlots() {
		final Set<String> requiredSlots = new HashSet<String>();

		// slot can be on any compute node
		requiredSlots.add(AbstractResourceScheduler.RANDOM_CONN);

		return requiredSlots;
	}

	@Override
	public ComputeNodeDesc getSlot(Identifier operId) {
		return this.assignedSlots.get(AbstractResourceScheduler.RANDOM_CONN);
	}
}
