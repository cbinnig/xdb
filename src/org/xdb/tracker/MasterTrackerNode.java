package org.xdb.tracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.client.QueryTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.codegen.CodeGenerator;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;

public class MasterTrackerNode {
	// compute slots
	private final Map<String, Integer> computeSlots = Collections
			.synchronizedMap(new HashMap<String, Integer>());;

	// query tracker slots
	private final Map<String, Integer> queryTrackerSlots = Collections
			.synchronizedMap(new HashMap<String, Integer>());

	// query tracker clients
	private final Map<String, QueryTrackerClient> queryTrackerClients = new HashMap<String, QueryTrackerClient>();

	// new plans, waiting for execution
	private final Queue<QueryTrackerPlan> queuedPlans = new LinkedList<QueryTrackerPlan>();

	// running plans by plan identifier
	private final HashMap<Identifier, QueryTrackerPlan> runningPlans = new HashMap<Identifier, QueryTrackerPlan>();

	// running plans with executing tracker identifier
	private final HashMap<Identifier, String> planAssignment = new HashMap<Identifier, String>();

	// logger
	private final Logger logger;

	//constructor
	public MasterTrackerNode() {
		logger = XDBLog.getLogger(this.getClass().getName());
	}

	//getters and setters
	/**
	 * Grab number of waiting plans.
	 */
	public int getNoWaitingPlans() {
		return queuedPlans.size();
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

		for (final Entry<String, Integer> entry : computeSlots.entrySet()) {
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
		int n = 0;

		for (final Entry<String, Integer> entry : queryTrackerSlots.entrySet()) {
			n += entry.getValue();
		}

		return n;
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

		exeutePlanIfPossible();

		return err;
	}

	/**
	 * Transform a CompilePlan to into multiple QueryTrackerPlans
	 * 
	 * @param plan
	 * @return
	 */
	public QueryTrackerPlan generateQueryTrackerPlan(
			final CompilePlan compilePlan) {
		CodeGenerator codeGen = new CodeGenerator(compilePlan);
		Error err = codeGen.generate();
		if(err.isError())
			return null;
		
		return codeGen.getQueryTrackerPlan();
	}

	/**
	 * Tries to find next free query tracker slot.
	 * 
	 * @return tracker url, if found - else null
	 */
	private String getFreeQueryTrackerSlot() {
		for (final Entry<String, Integer> entry : queryTrackerSlots.entrySet()) {
			int freeSlots = entry.getValue();
			if (freeSlots > 0) {
				return entry.getKey();
			}
		}

		return null;
	}

	private Error executeOnQueryTracker(final String tracker,
			final QueryTrackerPlan plan) {

		runningPlans.put(plan.getPlanId(), plan);
		planAssignment.put(plan.getPlanId(), tracker);

		final QueryTrackerClient client = queryTrackerClients.get(tracker);

		client.executePlan(plan);

		return new Error();
	}

	private void exeutePlanIfPossible() {
		if (queuedPlans.isEmpty()) {
			return;
		}

		logger.log(Level.INFO, "Trying to execute queued plan.");

		final String slot = getFreeQueryTrackerSlot();
		if (slot == null) {
			logger.log(Level.INFO, "No slot available.");
		}

		queryTrackerSlots.put(slot, queryTrackerSlots.get(slot) - 1);

		executeOnQueryTracker(slot, queuedPlans.poll());
	}

	public Error executePlan(final CompilePlan plan) {
		logger.log(Level.INFO, "Got new compileplan: " + plan);

		final QueryTrackerPlan qtp = generateQueryTrackerPlan(plan);
		if (qtp == null) {
			return new Error(EnumError.TRACKER_PLAN_INVALID_GENERIC, null);
		}
		
		qtp.traceGraph(this.getClass().getCanonicalName());

		return executePlan(qtp);
	}

	public Error executePlan(final QueryTrackerPlan plan) {
		// possibly optimization: execute directly if possible
		final Error err = new Error();

		if (plan == null) {
			return new Error(EnumError.TRACKER_PLAN_INVALID_GENERIC, null);
		}

		logger.log(Level.INFO, "Queued new plan for execution: " + plan);

		queuedPlans.add(plan);
		exeutePlanIfPossible();

		return err;
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
		queryTrackerSlots.put(desc.getUrl(), desc.getSlots());

		return err;
	}

	public Map<String, MutableInteger> getComputeSlots(
			final Map<String, MutableInteger> requiredSlots) {
		final HashMap<String, MutableInteger> allocatedSlots = new HashMap<String, MutableInteger>();

		// First handle allocatable Wishlist-Slots
		for (final Entry<String, MutableInteger> reqSlot : requiredSlots
				.entrySet()) {
			if (computeSlots.containsKey(reqSlot.getKey())) {
				final int available = computeSlots.get(reqSlot.getKey());
				final MutableInteger required = reqSlot.getValue();
				final int difference = available - required.intValue();
				if (difference >= 0) {
					allocatedSlots.put(reqSlot.getKey(), required.clone());
					computeSlots.put(reqSlot.getKey(), difference);
					required.setValue(0);
				} else {
					allocatedSlots.put(reqSlot.getKey(), new MutableInteger(
							available));
					computeSlots.put(reqSlot.getKey(), 0);
					required.setValue(required.substract(available));
				}
			}
		}

		// Now return random slots that are not on the wishlist
		for (final Entry<String, MutableInteger> reqSlot : requiredSlots
				.entrySet()) {
			final MutableInteger required = reqSlot.getValue();
			for (final Entry<String, Integer> computeSlot : computeSlots
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

		return allocatedSlots;
	}

	/**
	 * Add free nodes that were before in use by querytracker
	 * 
	 * @param freeNodes
	 */
	public void addFreeNodes(final Map<String, MutableInteger> freeNodes) {
		for (final Entry<String, MutableInteger> node : freeNodes.entrySet()) {
			Integer num = computeSlots.get(node.getKey());
			if (num == null) {
				num = 0;
			}
			num += node.getValue().intValue();
			computeSlots.put(node.getKey(), num);
		}
	}
}
