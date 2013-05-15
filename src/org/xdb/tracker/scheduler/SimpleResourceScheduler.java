package org.xdb.tracker.scheduler;

import java.util.HashSet;
import java.util.Set;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;

/**
 * Simple resource scheduler which assigns one compute slots randomly to all operators 
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
	public Set<String> createComputeNodesWishList() {
		final Set<String> requiredSlots = new HashSet<String>();

		// slot can be on any compute node
		requiredSlots.add(AbstractResourceScheduler.RANDOM_CONN);

		return requiredSlots;
	}

	@Override
	public ComputeNodeDesc getComputeNode(Identifier operId) {
		return this.assignedComputeNodesSlots.get(AbstractResourceScheduler.RANDOM_CONN);
	}
}
