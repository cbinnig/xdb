package org.xdb.tracker.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;

/**
 * Simple resource scheduler
 * 
 * @author cbinnig
 * 
 */
public class SimpleResourceScheduler extends AbstractResourceScheduler {
	private Map<String, MutableInteger> slots;
	
	protected SimpleResourceScheduler(final QueryTrackerPlan plan) {
		super(plan);

		this.type = EnumResourceScheduler.SIMPLE_SCHEDULER;
	}

	@Override
	public Map<String, MutableInteger> calcRequiredSlots() {
		final Map<String, MutableInteger> requiredSlots = new HashMap<String, MutableInteger>();

		// request one slot per operator
		final MutableInteger numSlots = new MutableInteger(plan.getOperators()
				.size());

		// slot can be on any compute node
		requiredSlots.put(AbstractResourceScheduler.RANDOM, numSlots);

		return requiredSlots;
	}

	@Override
	public String getSlot(Identifier operId) {
		if(slots == null)
			return null;

		String usedNode = null;
		for (final Entry<String, MutableInteger> availableNode : slots
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
	public void assignSlots(Map<String, MutableInteger> slots) {
		this.slots = slots;
	}
}
