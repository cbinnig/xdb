package org.xdb.tracker.scheduler;

import java.util.HashSet;
import java.util.Set;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;

/**
 * Simple resource scheduler which assigns one compute node randomly to each operator 
 * 
 * @author cbinnig
 * 
 */
public class SimpleResourceScheduler extends AbstractResourceScheduler {

	protected SimpleResourceScheduler(final QueryTrackerPlan plan) {
		super(plan);

		this.type = EnumResourceScheduler.SIMPLE;
	}

	@Override
	public Set<String> createComputeNodesWishList() {
		final Set<String> requiredNodes = new HashSet<String>();
		requiredNodes.add(AbstractResourceScheduler.RANDOM_COMPUTE_NODE);
		return requiredNodes;
	}

	@Override
	public ComputeNodeDesc getComputeNode(Identifier operId) {
		return this.assignedComputeNodes.get(AbstractResourceScheduler.RANDOM_COMPUTE_NODE);
	}
}
