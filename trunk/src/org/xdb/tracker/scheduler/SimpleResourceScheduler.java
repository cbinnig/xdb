package org.xdb.tracker.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.xdb.execute.ComputeNodeSlot;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;

/**
 * Simple resource scheduler which assigns compute slots randomly to operators 
 * 
 * @author cbinnig
 * 
 */
public class SimpleResourceScheduler extends AbstractResourceScheduler {
	private Map<ComputeNodeSlot, MutableInteger> slots = new HashMap<ComputeNodeSlot, MutableInteger>();
	
	protected SimpleResourceScheduler(final QueryTrackerPlan plan) {
		super(plan);

		this.type = EnumResourceScheduler.SIMPLE_SCHEDULER;
	}

	@Override
	public Map<String, MutableInteger> calcRequiredSlots() {
		final Map<String, MutableInteger> requiredSlots = new HashMap<String, MutableInteger>();

		// request one slot per operator
		final MutableInteger numSlots = new MutableInteger(plan.getTrackerOperators()
				.size());

		// slot can be on any compute node
		requiredSlots.put(AbstractResourceScheduler.RANDOM, numSlots);

		return requiredSlots;
	}

	@Override
	public ComputeNodeSlot getSlot(Identifier operId) {
		if(slots == null)
			return null;

		ComputeNodeSlot usedNode = null;
		for (final Entry<ComputeNodeSlot, MutableInteger> availableNode : slots
				.entrySet()) {
			if ( availableNode.getValue().intValue() > 0 ) {
				usedNode = availableNode.getKey();
				final MutableInteger numOfFreeNodes = slots.get(usedNode);
				numOfFreeNodes.dec();
				return usedNode;
			}
		}
		

		return null;
	}

	@Override
	public void assignSlots(Map<ComputeNodeSlot, MutableInteger> slots) {
		this.slots.putAll(slots);
	}
}
