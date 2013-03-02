package org.xdb.tracker.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.xdb.execute.ComputeNodeSlot;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;

/**
 * Resource Scheduler which Determines best compute slot URL 
 * for given operator in plan based on the
 * locations of its source. The compute slot URL which is used for most
 * sources is also used for the operator itself.
 * @author cbinnig
 *
 */
public class LocalityAwareScheduler extends AbstractResourceScheduler {
	// slots per operator that have been requested
	private Map<Identifier, String> wishLocations = new HashMap<Identifier, String>();

	// slots that have been requested: compute slot URL -> count
	private Map<String, MutableInteger> wishSlots = new HashMap<String, MutableInteger>();

	// assigned slots which are actually available: compute slot URL -> count
	private Map<ComputeNodeSlot, MutableInteger> assignedSlots = new HashMap<ComputeNodeSlot, MutableInteger>();

	public LocalityAwareScheduler(QueryTrackerPlan plan) {
		super(plan);
		
		this.type = EnumResourceScheduler.LOCALITY_AWARE_SCHEDULER;
	}

	@Override
	public Map<String, MutableInteger> calcRequiredSlots() {

		for (Identifier leaveId : plan.getLeaves()) {
			calcRequiredSlots(leaveId);
		}
		
		return wishSlots;
	}

	/**
	 * Determines best compute slot URL for given operator in plan based on the
	 * locations of its source. The compute slot URL which is used for most
	 * sources is also used for the operator itself.
	 * 
	 * @param opId
	 */
	private void calcRequiredSlots(Identifier opId) {
		if (!this.wishLocations.containsKey(opId)) {

			// count source URLs
			Set<Identifier> sources = this.plan.getSources(opId);
			Map<String, MutableInteger> locCounts = new HashMap<String, MutableInteger>();
			if (!sources.isEmpty()) {
				for (Identifier source : sources) {
					String sourceUrl = this.wishLocations.get(source);
					
					if (!locCounts.containsKey(sourceUrl)) {
						MutableInteger count = new MutableInteger(1);
						locCounts.put(sourceUrl, count);
					} else {
						MutableInteger count = locCounts.get(sourceUrl);
						count.inc();
					}
				}
			} else {
				for (TableDesc inTable : this.plan.getOperator(opId)
						.getInTableSources()) {
					String sourceUrl = inTable.getURI().getHost().toString();
					
					if (!locCounts.containsKey(sourceUrl)) {
						MutableInteger count = new MutableInteger(1);
						locCounts.put(sourceUrl, count);
					} else {
						MutableInteger count = locCounts.get(sourceUrl);
						count.inc();
					}
				}
			}

			// get URL with max count
			int maxCount = 0;
			String sourceUrl = null;
			for (Map.Entry<String, MutableInteger> locCount : locCounts
					.entrySet()) {
				if (locCount.getValue().intValue() > maxCount) {
					maxCount = locCount.getValue().intValue();
					sourceUrl = locCount.getKey();
				}
			}

			// add to operator locations and wishlist
			if(sourceUrl == null)
				sourceUrl = AbstractResourceScheduler.RANDOM;
			
			
			this.wishLocations.put(opId, sourceUrl);
			if (!this.wishSlots.containsKey(sourceUrl)) {
				MutableInteger count = new MutableInteger(1);
				this.wishSlots.put(sourceUrl, count);
			} else {
				MutableInteger count = this.wishSlots.get(sourceUrl);
				count.inc();
			}

			// Find URL for consumers
			for (Identifier consumerId : this.plan.getConsumers(opId)) {
				this.calcRequiredSlots(consumerId);
			}
		}
	}

	@Override
	public void assignSlots(Map<ComputeNodeSlot, MutableInteger> slots) {
		this.assignedSlots.putAll(slots);
	}

	@Override
	public ComputeNodeSlot getSlot(Identifier opId) {
		// get slot URL for operator
		String wishUrl = this.wishLocations.get(opId);
		
		ComputeNodeSlot newWishUrl = null;
		for (ComputeNodeSlot otherUrl : this.assignedSlots.keySet()) {
			if (otherUrl.getHost().equals(wishUrl)) {
				newWishUrl = otherUrl;
				break;
			}
			if (newWishUrl == null) {
				newWishUrl = otherUrl;
			}
		}
		for (ComputeNodeSlot otherUrl : this.assignedSlots.keySet()) {
			newWishUrl = otherUrl;
			break;
		}
			
		// decrement count
		MutableInteger count = this.assignedSlots.get(newWishUrl);
		count.dec();
		if (count.intValue() == 0) {
			this.assignedSlots.remove(newWishUrl);
		}

		return newWishUrl;
	}

}
