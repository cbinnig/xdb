package org.xdb.tracker.scheduler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xdb.execute.ComputeNodeDesc;
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
	private Set<String> wishSlots = new HashSet<String>();

	public LocalityAwareScheduler(QueryTrackerPlan plan) {
		super(plan);
		
		this.type = EnumResourceScheduler.LOCALITY_AWARE_SCHEDULER;
	}

	@Override
	public Set<String> calcRequiredSlots() {

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
				for (TableDesc inTable : this.plan.getTrackerOperator(opId)
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

			// add to operator locations and wish-list
			if(sourceUrl == null)
				sourceUrl = AbstractResourceScheduler.RANDOM_CONN;
			
			
			this.wishLocations.put(opId, sourceUrl);
			if (!this.wishSlots.contains(sourceUrl)) {
				this.wishSlots.add(sourceUrl);
			} 
			
			// Find URL for consumers
			for (Identifier consumerId : this.plan.getConsumers(opId)) {
				this.calcRequiredSlots(consumerId);
			}
		}
	}

	@Override
	public ComputeNodeDesc getSlot(Identifier opId) {
		// get slot URL for operator
		String wishUrl = this.wishLocations.get(opId);
		return this.assignedSlots.get(wishUrl);
	}

}
