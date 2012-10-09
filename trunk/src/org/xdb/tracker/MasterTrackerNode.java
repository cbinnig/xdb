package org.xdb.tracker;

import java.util.Collection;
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

import org.xdb.client.QueryTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public class MasterTrackerNode {
	//compute slots
	private final Map<String, Integer> computeSlots;

	//query tracker slots
	private final Map<String, Integer> queryTrackerSlots = Collections.synchronizedMap(new HashMap<String, Integer>());

	//query tracker clients
	private final Map<String, QueryTrackerClient> queryTrackerClients = new HashMap<String, QueryTrackerClient>();

	//new plans, waiting for execution
	private final Queue<QueryTrackerPlan> queuedPlans = new LinkedList<QueryTrackerPlan>();

	//running plans by plan identifier
	private final HashMap<Identifier, QueryTrackerPlan> runningPlans = new HashMap<Identifier, QueryTrackerPlan>();

	//running plans with executing tracker identifier
	private final HashMap<Identifier, String> planAssignment = new HashMap<Identifier, String>();

	// Helpers
	private final Logger logger;


	public MasterTrackerNode(){
		computeSlots = Collections
				.synchronizedMap(new HashMap<String, Integer>()); 

		logger = XDBLog.getLogger(this.getClass().getName());
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

		for(final Entry<String, Integer> entry : computeSlots.entrySet()) {
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

		for(final Entry<String, Integer> entry : queryTrackerSlots.entrySet()) {
			n += entry.getValue();
		}

		return n;
	}


	/**
	 * Register new compute node for management.
	 * @param desc ComputeNodeDesc
	 */
	public Error registerComputeNode(final ComputeNodeDesc desc){
		final Error err = new Error();

		logger.log(Level.INFO, "Added compute slots: " + desc);
		computeSlots.put(desc.getUrl(), desc.getSlots());

		exeutePlanIfPossible();

		return err;
	}

	/**
	 * Transform a CompilePlan to into multiple QueryTrackerPlans
	 * @param plan
	 * @return
	 */
	public QueryTrackerPlan generateQueryTrackerPlan(final CompilePlan plan) {
		final QueryTrackerPlan qPlan = new QueryTrackerPlan(plan.getPlanId());

		//map of operator identifiers to all depended operator's identifiers
		final Map<Identifier, Set<Identifier>> dependencies = new HashMap<Identifier, Set<Identifier>>();

		for(final org.xdb.funsql.compile.operator.AbstractOperator op : plan.getOperators()) {

			//add current operator to all source operators as dependent
			for(final org.xdb.funsql.compile.operator.AbstractOperator depOp : op.getSourceOperators()) {

				//get existing depended operators
				Set<Identifier> dependendOperators = dependencies.get(depOp.getOperatorId());
				if(dependendOperators == null) {
					dependendOperators = new HashSet<Identifier>();
					dependencies.put(depOp.getOperatorId(), dependendOperators);
				}

				//add current operator as dependent
				dependendOperators.add(op.getOperatorId());
			}
		}

		/*
		 * parsing strategie:
		 * - queue up all roots
		 * - scan through childs of all queue elements until queue is empty
		 * - scan until next operator, which has multiple dependent operators or 
		 *   is marked as materialized or has no dependencies
		 * - queue this operators up
		 * - set source/consumer attributes accordingly
		 * - work until queue is empty
		 */

		final Queue<org.xdb.funsql.compile.operator.AbstractOperator> scanQueue = 
				new LinkedList<org.xdb.funsql.compile.operator.AbstractOperator>();

		//add root ops as initial new MySQLOperators
		final Collection<Identifier> rootOps = plan.getRoots();
		for(final org.xdb.funsql.compile.operator.AbstractOperator op : plan.getOperators()) {
			if(rootOps.contains(op)) {
				scanQueue.add(op);
			}
		}

		if(scanQueue.isEmpty()) {
			logger.log(Level.INFO, "Could not convert CompilePlan: no root ops.");
			return null;
		}

		//map of QTP operator's identifiers to set of source operators
		//this is needed, because dependent CP operator's are probably not in place anymore (assembled into QTP operators)
		final Map<Identifier, Set<Identifier>> operatorSources = new HashMap<Identifier, Set<Identifier>>();

		while(!scanQueue.isEmpty()) {
			final org.xdb.funsql.compile.operator.AbstractOperator op =
					scanQueue.poll();
			final ResultDesc result = op.getResult(0);

			final org.xdb.tracker.operator.MySQLOperator queryOp = 
					new org.xdb.tracker.operator.MySQLOperator(op.getOperatorId());
			//add single output table; TODO modify for multiple outputs
			queryOp.addOutTables("R_OUT", new StringTemplate("<R_OUT> "+result.toSqlString()), "R_REGIONKEY");

			//sources & consumers
			final Set<Identifier> sources = new HashSet<Identifier>();
			final Set<Identifier> consumers = new HashSet<Identifier>();

			//add all dependend operators of root operator as consumers
			if(operatorSources.containsKey(op.getOperatorId())) {
				consumers.addAll(operatorSources.get(op.getOperatorId()));
			}

			//assembled sql exeution statement
			String executeSqlStatement = "INSERT INTO <R_OUT> "+op.toSqlString();

			//queue to assemble this operator
			final Queue<org.xdb.funsql.compile.operator.AbstractOperator> assemblingQueue = 
					new LinkedList<org.xdb.funsql.compile.operator.AbstractOperator>();
			assemblingQueue.addAll(op.getSourceOperators());

			while(!assemblingQueue.isEmpty()) {
				final org.xdb.funsql.compile.operator.AbstractOperator childOp =
						assemblingQueue.poll();

				//break on multiple dependends or materialized-state
				if(childOp.getResult(0).isMaterialize() || (dependencies.containsKey(childOp.getOperatorId())
						&& dependencies.get(childOp.getOperatorId()).size() > 1)) {

					//add as new operator root
					scanQueue.add(childOp);

					//register as source operator
					sources.add(childOp.getOperatorId());
					// ... and as consumers for the new operator
					Set<Identifier> dependendOperators = operatorSources.get(childOp.getOperatorId());
					if(dependendOperators == null) {
						dependendOperators = new HashSet<Identifier>();
						operatorSources.put(childOp.getOperatorId(), dependendOperators);
					}
					dependendOperators.add(queryOp.getOperatorId());

					//add as input
					queryOp.addInTables(childOp.getOperatorId().toString(), 
							new StringTemplate("<"+childOp.getOperatorId().toString()+"> "+childOp.getResult(0).toSqlString()), "R_REGIONKEY");

					//do not process this operator and do not add children of this operator to assemlingQueue
					continue;
				}

				final StringTemplate sqlAssemblyTemplate = new StringTemplate(executeSqlStatement.toString());
				final Map<String, String> sqlAssemblyStepArgs = new HashMap<String, String>();
				sqlAssemblyStepArgs.put(childOp.getOperatorId().toString(), "("+childOp.toSqlString()+")");
				executeSqlStatement = sqlAssemblyTemplate.toString(sqlAssemblyStepArgs);

				assemblingQueue.addAll(childOp.getSourceOperators());
			}

			//add compiled query
			queryOp.addExecuteSQL(new StringTemplate(executeSqlStatement));

			logger.log(Level.INFO, "Created new MySQLOperator node in QueryTrackerPlan");

			if(sources.isEmpty() && consumers.isEmpty()) {
				logger.log(Level.INFO, "Operator has neither sources nor consumers. Something is probably wrong.");
			}

			//add to plan
			qPlan.addOperator(queryOp, sources, consumers);
		}

		return qPlan;
	}

	/**
	 * Tries to find next free compute slot.
	 * @return tracker url, if found - else null
	 */
	private String getFreeQueryTrackerSlot() {
		for(final Entry<String, Integer> entry : queryTrackerSlots.entrySet()) {
			if(entry.getValue() > 0) {
				return entry.getKey();
			}
		}

		return null;
	}


	private Error executeOnQueryTracker(final String tracker, final QueryTrackerPlan plan) {
		runningPlans.put(plan.getPlanId(), plan);
		planAssignment.put(plan.getPlanId(), tracker);

		final QueryTrackerClient client = queryTrackerClients.get(tracker);

		client.executePlan(plan);

		return new Error();
	}

	private void exeutePlanIfPossible() {
		if(queuedPlans.isEmpty()) {
			return;
		}

		logger.log(Level.INFO, "Trying to execute queued plan.");

		final String slot = getFreeQueryTrackerSlot();
		if(slot == null) {
			logger.log(Level.INFO, "No slot available.");
		}

		queryTrackerSlots.put(slot, queryTrackerSlots.get(slot)-1);

		executeOnQueryTracker(slot, queuedPlans.poll());
	}

	public Error executePlan(final CompilePlan plan) {
		logger.log(Level.INFO, "Got new compileplan: "+plan);

		final QueryTrackerPlan qtp = generateQueryTrackerPlan(plan);
		if(qtp == null) {
			return new Error(EnumError.TRACKER_PLAN_INVALID_GENERIC, null);
		}

		return executePlan(qtp);
	}

	public Error executePlan(final QueryTrackerPlan plan) {
		//possibly optimization: execute directly if possible
		final Error err = new Error();

		if(plan == null) {
			return new Error(EnumError.TRACKER_PLAN_INVALID_GENERIC, null);
		}

		logger.log(Level.INFO, "Queued new plan for execution: "+plan);

		queuedPlans.add(plan);
		exeutePlanIfPossible();

		return err;
	}

	/**
	 * Register a new QueryTrackerNode
	 * @param desc
	 * @return
	 */
	public Error registerQueryTrackerNode(final QueryTrackerNodeDesc desc) {
		final Error err = new Error();

		logger.log(Level.INFO, "Added QueryTrackerNode: " + desc);
		queryTrackerSlots.put(desc.getUrl(), desc.getSlots());

		return err;
	}

	public String getComputeSlot() {
		// TODO return ComputeSlot
		return null;
	}
}
