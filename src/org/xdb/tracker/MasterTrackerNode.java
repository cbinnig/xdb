package org.xdb.tracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.client.QueryTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.ComputeNodeSlot;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;

public class MasterTrackerNode {
	// compute slots
	private final Map<ComputeNodeSlot, Integer> computeSlots = Collections
			.synchronizedMap(new HashMap<ComputeNodeSlot, Integer>());;

	// query tracker slots
	private final List<String> queryTrackerSlots = Collections
			.synchronizedList(new Vector<String>());

	private int lastQueryTrackerSlot = 0;

	// query tracker clients for each registered query tracker
	private final Map<String, QueryTrackerClient> queryTrackerClients = new HashMap<String, QueryTrackerClient>();

	// running plans by plan identifier
	private final HashMap<Identifier, CompilePlan> runningPlans = new HashMap<Identifier, CompilePlan>();

	// running plans with executing query tracker url
	private final HashMap<Identifier, String> planAssignment = new HashMap<Identifier, String>();

	// logger
	private final Logger logger;
	private Error err = new Error();

	// constructor
	public MasterTrackerNode() {
		logger = XDBLog.getLogger(this.getClass().getName());
	}

	// getters and setters
	public Error getLastError() {
		return this.err;
	}

	/**
	 * Grab number of running plans.
	 */
	public int getRunningPlans() {
		return runningPlans.size();
	}

	/**
	 * Get number of free slots on all compute servers.
	 * 
	 * @return number of free compute slots
	 */
	public int getNoFreeComputeSlots() {
		int n = 0;

		for (final Entry<ComputeNodeSlot, Integer> entry : computeSlots.entrySet()) {
			n += entry.getValue();
		}

		return n;
	}

	/**
	 * Get number of free slots on all query trackers.
	 * 
	 * @return number of free slots
	 */
	public int getNoFreeQueryTrackerSlots() {
		return this.queryTrackerSlots.size();
	}

	// methods



	/**
	 * Determines query tracker and hands over compile plan for execution
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan plan) {
		if (plan == null) {
			String[] args = { "No query tracker plan provided" };
			return new Error(EnumError.TRACKER_GENERIC, args);
		}

		// tracing
		if (Config.TRACE_TRACKER_PLAN){
			plan.tracePlan(plan.getClass().getCanonicalName()+"MASTER_TRACKER");
		}

		logger.log(Level.INFO, "Got new plan for execution: " + plan);

		final String slot = getFreeQueryTrackerSlot();
		if (slot == null) {
			String[] args = { "No query tracker slot provided" };
			return new Error(EnumError.TRACKER_GENERIC, args);
		}
		this.err = this.executeOnQueryTracker(slot, plan);

		return this.err;
	}

	/**
	 * Determines next query tracker using a round-robin scheme
	 * 
	 * @return tracker URL if found - else null
	 */
	private String getFreeQueryTrackerSlot() {
		if (this.queryTrackerSlots.size() > 0) {
			this.lastQueryTrackerSlot++;
			this.lastQueryTrackerSlot = (this.lastQueryTrackerSlot % this.queryTrackerSlots
					.size());

			return this.queryTrackerSlots.get(this.lastQueryTrackerSlot);
		}

		return null;
	}

	/**
	 * Executes compile plan on given query tracker
	 * 
	 * @param tracker
	 * @param plan
	 * @return
	 */
	private Error executeOnQueryTracker(final String tracker,
			final CompilePlan plan) {

		runningPlans.put(plan.getPlanId(), plan);
		planAssignment.put(plan.getPlanId(), tracker);

		final QueryTrackerClient client = this.queryTrackerClients.get(tracker);

		// execute plan
		this.err = client.executePlan(plan);

		return this.err;
	}

	/**
	 * Returns a list of compute slots for a given wish-list of compute-slots
	 * (nodes)
	 * 
	 * @param requiredSlots
	 *            wish-list of compute-slots
	 * @return assigned compute-slots
	 */
	public Map<ComputeNodeSlot, MutableInteger> getComputeSlots(
			final Map<String, MutableInteger> requiredSlots) {
		final HashMap<ComputeNodeSlot, MutableInteger> allocatedSlots = new HashMap<ComputeNodeSlot, MutableInteger>();

		// First handle wish list-slots
		for (final Entry<String, MutableInteger> reqSlot : requiredSlots
				.entrySet()) {
			final ComputeNodeSlot k = getKey(computeSlots, reqSlot.getKey());
			if (k != null) {
				final int available = computeSlots.get(k);
				final MutableInteger required = reqSlot.getValue();
				final int difference = available - required.intValue();
				if (difference >= 0) {
					allocatedSlots.put(k, required.clone());
					computeSlots.put(k, difference);
					required.setValue(0);
				} else {
					allocatedSlots.put(k, new MutableInteger(
							available));
					computeSlots.put(k, 0);
					required.setValue(required.substract(available));
				}
			}
		}

		// Now return random slots that are not available + satisfy requests for
		// random slots
		for (final Entry<String, MutableInteger> reqSlot : requiredSlots
				.entrySet()) {
			final MutableInteger required = reqSlot.getValue();
			for (final Entry<ComputeNodeSlot, Integer> computeSlot : computeSlots
					.entrySet()) {
				final int available = computeSlot.getValue();
				final int difference = available - required.intValue();
				final MutableInteger old = allocatedSlots.get(computeSlot
						.getKey());
				if (difference >= 0) {
					if (old != null) {
						old.setValue(old.add(required.intValue()));
					} else {
						allocatedSlots.put(computeSlot.getKey(),
								required.clone());
					}
					computeSlot.setValue(difference);
					required.setValue(0);
					break;
				} else {
					if (old != null) {
						old.setValue(old.add(available));
					} else {
						allocatedSlots.put(computeSlot.getKey(),
								new MutableInteger(available));
					}
					computeSlot.setValue(0);
					required.setValue(required.substract(available));
				}
			}
		}

		// check if request could be satisfied
		for (final Entry<String, MutableInteger> reqSlot : requiredSlots
				.entrySet()) {
			final MutableInteger required = reqSlot.getValue();

			if (required.intValue() > 0) {
				String[] args = { "Not enough free compute slots available" };
				this.err = new Error(EnumError.TRACKER_GENERIC,
						args);
				break;
			}
		}
		return allocatedSlots;
	}

	/**
	 * Register a new QueryTrackerNode
	 * 
	 * @param desc
	 * @return
	 */
	public Error registerQueryTrackerNode(final QueryTrackerNodeDesc desc) {
		final Error err = new Error();

		logger.log(Level.INFO, "Added QueryTrackerNode: " + desc);
		queryTrackerSlots.add(desc.getUrl());
		this.queryTrackerClients.put(desc.getUrl(),
				new QueryTrackerClient(desc.getUrl()));

		return err;
	}

	/**
	 * Register new compute node for management.
	 * 
	 * @param desc
	 *            ComputeNodeDesc
	 */
	public Error registerComputeNode(final ComputeNodeDesc desc) {
		final Error err = new Error();

		logger.log(Level.INFO, "Added compute slots: " + desc);
		computeSlots.put(desc.getUrl(), desc.getSlots());

		return err;
	}

	/**
	 * Add free nodes that were before in use by query tracker
	 * 
	 * @param freeNodes
	 */
	public void addFreeComputeSlots(final Map<ComputeNodeSlot, MutableInteger> freeNodes) {
		for (final Entry<ComputeNodeSlot, MutableInteger> node : freeNodes.entrySet()) {
			Integer num = computeSlots.get(node.getKey());
			if (num == null) {
				num = 0;
			}
			num += node.getValue().intValue();
			computeSlots.put(node.getKey(), num);
		}
	}
	
	private static final ComputeNodeSlot getKey(final Map<ComputeNodeSlot, ?> map1, final String key) {
		for (ComputeNodeSlot k : map1.keySet()) {
			if (k.getHost().equals(key)) {
				return k;
			}
		}
		return null;
	}
}
