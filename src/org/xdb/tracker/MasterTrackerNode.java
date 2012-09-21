package org.xdb.tracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;

public class MasterTrackerNode {
	//compute slots
	private Map<String, Integer> computeSlots;
	
	//query tracker slots
	private Map<String, Integer> queryTrackerSlots;
	
	//new plans, waiting for execution
	private Queue<QueryTrackerPlan> queuedPlans = new LinkedList<QueryTrackerPlan>();
	
	//running plans by plan identifier
	private HashMap<Identifier, QueryTrackerPlan> runningPlans = new HashMap<Identifier, QueryTrackerPlan>();
	
	//running plans with executing tracker identifier
	private HashMap<Identifier, String> planDeployment = new HashMap<Identifier, String>();
	
	// Helpers
	private Logger logger;
	
	
	public MasterTrackerNode(){
		this.computeSlots = Collections
				.synchronizedMap(new HashMap<String, Integer>()); 
		
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}
	
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
	 * @return number of free compute slots
	 */
	public int getNoFreeComputeSlots() {
		int n = 0;
		
		for(Entry<String, Integer> entry : computeSlots.entrySet()) {
			n += entry.getValue();
		}
		
		return n;
	}
	
	/**
	 * Get number of free slots on all query trackers.
	 * @return number of free slots
	 */
	public int getNoFreeQueryTrackerSlots() {
		int n = 0;
		
		for(Entry<String, Integer> entry : queryTrackerSlots.entrySet()) {
			n += entry.getValue();
		}
		
		return n;
	}
	
	
	/**
	 * Register new compute node for management.
	 * @param desc ComputeNodeDesc
	 */
	public Error registerComputeNode(ComputeNodeDesc desc){
		Error err = new Error();

		logger.log(Level.INFO, "Added compute slots: " + desc);
		this.computeSlots.put(desc.getUrl(), desc.getSlots());
		
		exeutePlanIfPossible();
		
		return err;
	}
	
	/**
	 * Transform a CompilePlan to into multiple QueryTrackerPlans
	 * @param plan
	 * @return
	 */
	public static Set<QueryTrackerPlan> generateQueryTrackerPlan(CompilePlan plan) {
		
		//TODO: generate QueryTrackerPlan from CompilePlan
		
		return null;
	}
	
	/**
	 * Tries to find next free compute slot.
	 * @return compute slot url, if found - else null
	 */
	private String getFreeQueryTrackerSlot() {
		for(Entry<String, Integer> entry : queryTrackerSlots.entrySet()) {
			if(entry.getValue() > 0)
				return entry.getKey();
		}
		
		return null;
	}
	
	
	private Error executeOnQueryTracker(String tracker, QueryTrackerPlan plan) {
		runningPlans.put(plan.getPlanId(), plan);
		planDeployment.put(plan.getPlanId(), tracker);
		
		//TODO: send plan to query tracker and execute
		
		return new Error();
	}
	
	private void exeutePlanIfPossible() {
		if(queuedPlans.isEmpty())
			return;
		
		logger.log(Level.INFO, "Trying to execute queued plan.");
		
		String slot = getFreeQueryTrackerSlot();
		if(slot == null) {
			logger.log(Level.INFO, "No slot available.");
		}
			
		queryTrackerSlots.put(slot, queryTrackerSlots.get(slot)-1);
		
		executeOnQueryTracker(slot, queuedPlans.poll());
	}
	
	public Error executePlan(CompilePlan plan) {
		Error err = new Error();
		logger.log(Level.INFO, "Got new compileplan: "+plan);
		
		Set<QueryTrackerPlan> qtps = MasterTrackerNode.generateQueryTrackerPlan(plan);
		
		for(QueryTrackerPlan qtp : qtps) {
			Error rErr = executePlan(qtp);
			if(!rErr.equals(Error.NO_ERROR)) {
				err = rErr;
			}
		}
		
		return err;
	}
	
	public Error executePlan(QueryTrackerPlan plan) {
		//possibly optimization: execute directly if possible
		Error err = new Error();
		
		logger.log(Level.INFO, "Queued new plan for execution: "+plan);
		if(plan == null) {
			return new Error(EnumError.TRACKER_PLAN_INVALID_GENERIC, null);
		}
		
		queuedPlans.add(plan);
		exeutePlanIfPossible();
		
		return err;
	}
}
