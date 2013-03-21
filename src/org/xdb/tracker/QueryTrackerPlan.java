package org.xdb.tracker;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeSlot;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.logging.XDBExecuteTimeMeasurement;
import org.xdb.logging.XDBLog;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.scheduler.AbstractResourceScheduler;
import org.xdb.utils.Dotty;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;
import org.xdb.utils.Tuple;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphFactory;
import com.oy.shared.lm.graph.GraphNode;

/**
 * Plan implementation for query tracker. This plan is used to compute its
 * deployment using a given resource scheduler. Using this deployment the plan
 * can be executed on the deployed resources (i.e., compute nodes)
 * 
 * @author cbinnig
 * 
 */
public class QueryTrackerPlan implements Serializable {

	private static final long serialVersionUID = -5521482252707107847L;

	// empty operator set
	private static final Set<Identifier> EMPTY_OP_SET = new HashSet<Identifier>();

	// last plan id
	private static Integer lastPlanId = 1;

	// unique plan id
	private final Identifier planId;

	// last tracker operator id of plan
	private Integer lastTrackerOpId = 1;

	// last execute operator id of plan
	private Integer lastExecuteOpId = 1;

	// assigned when plan is handed over to query tracker
	private transient QueryTrackerNode tracker = null;
	private transient ComputeClient computeClient = null;
	private transient AbstractResourceScheduler resourceScheduler = null;

	// tracker plan
	private final Map<Identifier, AbstractTrackerOperator> trackerOps = new HashMap<Identifier, AbstractTrackerOperator>();
	private final Map<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();
	private final Map<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private final Set<Identifier> roots = new HashSet<Identifier>();
	private final Set<Identifier> leaves = new HashSet<Identifier>();

	// execution plan
	private final Map<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();
	private final Map<ComputeNodeSlot, MutableInteger> slots = new HashMap<ComputeNodeSlot, MutableInteger>();
	private final Map<Identifier, AbstractExecuteOperator> executeOps = new HashMap<Identifier, AbstractExecuteOperator>();

	// helper to measure execution time
	private final XDBExecuteTimeMeasurement timeMeasure;


	// logger
	private transient Logger logger;

	// last error
	private Error err = new Error();

	// constructor
	public QueryTrackerPlan() {
		this.planId = new Identifier(lastPlanId++);
		this.resourceScheduler = AbstractResourceScheduler
				.createScheduler(this);
		this.logger = XDBLog.getLogger(this.getClass().getName());

		this.timeMeasure = XDBExecuteTimeMeasurement
				.getXDBExecuteTimeMeasurement("plan_time");
	}

	// getter and setter
	public Set<Identifier> getLeaves() {
		return Collections.unmodifiableSet(leaves);
	}

	public Map<Identifier, AbstractTrackerOperator> getOperatorMapping() {
		return Collections.unmodifiableMap(trackerOps);
	}

	public Map<ComputeNodeSlot, MutableInteger> getSlots() {
		return slots;
	}

	public Map<Identifier, OperatorDesc> getCurrentDeployment() {
		return currentDeployment;
	}

	public Identifier getPlanId() {
		return planId;
	}

	public Collection<AbstractTrackerOperator> getTrackerOperators() {
		return trackerOps.values();
	}

	public AbstractTrackerOperator getTrackerOperator(Identifier opId) {
		return trackerOps.get(opId);
	}

	public AbstractExecuteOperator getExecuteOperator(Identifier opId) {
		return this.executeOps.get(opId);
	}

	public Set<Identifier> getRoots() {
		return roots;
	}

	public Set<Identifier> getSources(Identifier opId) {
		return this.sources.get(opId);
	}

	public Set<Identifier> getConsumers(Identifier opId) {
		return this.consumers.get(opId);
	}

	public Error getLastError() {
		return err;
	}

	public void setLastError(Error err) {
		this.err = err;
	}

	// methods

	/**
	 * Assigns query tracker to plan
	 * 
	 * @param tracker
	 */
	public void assignTracker(final QueryTrackerNode tracker) {
		this.tracker = tracker;
		this.tracker.addPlan(this);
		this.computeClient = tracker.getComputeClient();
	}

	/**
	 * Adds operator to plan and marks it as new root and leaf without sources
	 * and consumers
	 * 
	 * @param op
	 */
	public void addOperator(final AbstractTrackerOperator op) {
		Identifier opId = this.planId.clone().append(lastTrackerOpId++);
		op.setOperatorId(opId);
		trackerOps.put(opId, op);

		// add operator as leave and root
		this.leaves.add(opId);
		this.roots.add(opId);
		op.setIsRoot(true);

		this.sources.put(opId, EMPTY_OP_SET);
		this.consumers.put(opId, EMPTY_OP_SET);
	}

	/**
	 * Sets sources of an operator and changes status to be no leaf anymore if
	 * sources is not empty
	 * 
	 * @param operId
	 * @param opSources
	 */
	public void setSources(Identifier operId, final Set<Identifier> opSources) {
		this.sources.put(operId, opSources);

		if (!opSources.isEmpty()) {
			leaves.remove(operId);
		}
	}

	/**
	 * Sets consumers of an operator and changes status to be no root anymore if
	 * consumers is not empty
	 * 
	 * @param operId
	 * @param opConsumers
	 */
	public void setConsumers(Identifier operId,
			final Set<Identifier> opConsumers) {
		this.consumers.put(operId, opConsumers);

		if (!opConsumers.isEmpty()) {
			roots.remove(operId);
			AbstractTrackerOperator operator = this.trackerOps.get(operId);
			operator.setIsRoot(false);
		}
	}

	/**
	 * Set tracker operator to status executed
	 * 
	 * @param trackerOpId
	 */
	public void setTrackerOperatorExecuted(Identifier trackerOpId) {
		this.trackerOps.get(trackerOpId).setExecuted(true);
	}

	/**
	 * Check if all root operators are executed
	 * 
	 * @return
	 */
	public boolean isExecuted() {
		for (Identifier rootId : this.roots) {
			if (!this.getTrackerOperator(rootId).isExecuted()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Closes all operators in the plan. Result
	 * tables are kept if configuration is set accordingly
	 * 
	 * 
	 */
	public Error cleanPlan() {
		// close operators which are no root operators
		for (final Entry<Identifier, OperatorDesc> entry : currentDeployment
				.entrySet()) {
			final AbstractTrackerOperator trackerOp = trackerOps.get(entry
					.getKey());
			if (!trackerOp.isRoot() || Config.COMPUTE_CLEAN_RESULTS) {
				final OperatorDesc operDesc = entry.getValue();
				err = computeClient.closeOperator(operDesc);

				if (err.isError()) {
					return err;
				}
			}
		}
		return err;
	}

	/**
	 * Closes all operators on error and ignore errors (i.e., all intermediate
	 * results are dropped)
	 * 
	 * @return
	 */
	public Error cleanPlanOnError() {
		// close all operators
		for (final Entry<Identifier, OperatorDesc> entry : currentDeployment
				.entrySet()) {
			final OperatorDesc operDesc = entry.getValue();
			computeClient.closeOperator(operDesc);
		}

		return err;
	}

	/**
	 * Executes a plan using a given deployment description
	 * 
	 * @param currentDeployment
	 */
	public Error executePlan() {
		this.timeMeasure.start(this.getPlanId().toString());
		
		if (err.isError()) {
			return err;
		}

		// start execution on leave operators
		for (final Identifier leaveId : leaves) {
			final OperatorDesc leaveOpDesc = currentDeployment.get(leaveId);
			err = computeClient.executeOperator(leaveOpDesc);
			if (err.isError()) {
				return err;
			}
		}

		// wait until plan is executed
		while (!this.isExecuted() && !this.err.isError()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}

		this.timeMeasure.stop(this.getPlanId().toString());
		
		return err;
	}

	/**
	 * Deploys the query tracker plan using a given deployment 
	 * 
	 * @return
	 */
	public Error deployPlan(Map<Identifier, OperatorDesc> currentDeployment) {
		this.currentDeployment.putAll(currentDeployment);

		// distribute plan to compute nodes
		distributePlan();
		if (err.isError())
			return this.err;

		// trace execution plan
		if (Config.TRACE_EXECUTE_PLAN) {
			this.err = this.traceDeployment(this.getClass().getCanonicalName()
					+ "_EXECUTE");
		}

		// error handling
		return this.err;
	}

	/**
	 * Deploys the the query tracker plan by creating a deployment 
	 * 
	 * @return
	 */
	public Error deployPlan() {

		// request compute slots
		requestSlots();
		if (err.isError())
			return this.err;

		// prepare deployment
		prepareDeployment();
		if (err.isError())
			return this.err;

		// distribute plan to compute nodes
		distributePlan();
		if (err.isError())
			return this.err;

		// trace execution plan
		if (Config.TRACE_EXECUTE_PLAN) {
			this.err = this.traceDeployment(this.getClass().getCanonicalName()
					+ "_EXECUTE");
		}

		// error handling
		return this.err;
	}

	/**
	 * Requests computation slots from master tracker
	 */
	private void requestSlots() {
		// create wish-list of compute slots
		final Map<String, MutableInteger> requiredSlots = resourceScheduler
				.calcRequiredSlots();

		// as master tracker for slots
		final Tuple<Map<ComputeNodeSlot, MutableInteger>, Error> resultRequest = tracker
				.requestComputeSlots(requiredSlots);

		// read result
		final Map<ComputeNodeSlot, MutableInteger> allocatedSlots = resultRequest
				.getObject1();
		err = resultRequest.getObject2();

		// assign slots
		this.slots.putAll(allocatedSlots);
		this.resourceScheduler.assignSlots(slots);
	}

	/**
	 * Prepare deployment of plan by assigning operators to compute slots using
	 * a resource scheduler
	 */
	private void prepareDeployment() {
		for (final Identifier leave : leaves) {
			prepareDeployment(leave);
			if (err.isError())
				return;
		}
	}

	/**
	 * Prepares deployment for a given operator in plan
	 * 
	 * @param operId
	 */
	private void prepareDeployment(final Identifier operId) {

		// operator already deployed
		if (this.currentDeployment.containsKey(operId)) {
			return;
		}

		// identify best slot using a resource scheduler
		ComputeNodeSlot assignedSlot = this.resourceScheduler.getSlot(operId);
		if (assignedSlot == null) {
			String args[] = { "No slot could be assigned to tracker operator "
					+ operId.toString() };
			this.err = new Error(EnumError.TRACKER_GENERIC, args);
			return;
		}

		// generate deployment description from operator
		final Identifier executeOpId = operId.clone();
		executeOpId.append(lastExecuteOpId++);
		final OperatorDesc executeOpDesc = new OperatorDesc(executeOpId,
				assignedSlot);

		// add to operator and deployment description to current deployment
		currentDeployment.put(operId, executeOpDesc);

		// prepare deployment of all consumers
		for (final Identifier consumerId : consumers.get(operId)) {
			prepareDeployment(consumerId);
		}
	}

	/**
	 * Distributes plan to assigned compute nodes using a given deployment
	 * description
	 * 
	 * @param currentDeployment
	 */
	private void distributePlan() {
		// distribute all operators in deployment
		for (final Entry<Identifier, OperatorDesc> entry : this.currentDeployment
				.entrySet()) {
			final Identifier trackerOpId = entry.getKey();
			final OperatorDesc executeOpDesc = entry.getValue();
			final AbstractTrackerOperator trackerOp = trackerOps
					.get(trackerOpId);

			// create executable operator and set query tracker URL
			final AbstractExecuteOperator execOp = trackerOp.genDeployOperator(
					executeOpDesc, currentDeployment);
			this.err = trackerOp.getError();
			if (this.err.isError())
				return;

			// assign query tracker to execute operator
			execOp.setQueryTracker(this.tracker.getDescription());

			// set consumers of operator
			for (final Identifier consumerId : consumers.get(trackerOpId)) {
				final OperatorDesc consumerDesc = currentDeployment
						.get(consumerId);
				execOp.addConsumer(consumerDesc);
			}

			// set sources of operator
			for (final Identifier sourceId : sources.get(trackerOpId)) {
				final OperatorDesc sourceDesc = currentDeployment.get(sourceId);
				execOp.addSource(sourceDesc);
			}

			// deploy operator to compute node
			this.err = computeClient.openOperator(
					executeOpDesc.getComputeSlot(), execOp);
			this.executeOps.put(trackerOpId, execOp);
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public Error traceDeployment(String fileName) {
		fileName += planId;
		final Error error = new Error();
		final Graph graph = GraphFactory.newGraph();

		final HashMap<Identifier, GraphNode> nodes = new HashMap<Identifier, GraphNode>();

		// add nodes to plan
		for (Identifier opId : this.executeOps.keySet()) {
			GraphNode node = graph.addNode();
			AbstractExecuteOperator op = this.executeOps.get(opId);
			String sql = op.toString();
			node.getInfo().setCaption(sql);
			logger.log(Level.INFO, sql);
			nodes.put(opId, node);
		}

		// add edges to plan
		for (Map.Entry<Identifier, Set<Identifier>> entry : this.sources
				.entrySet()) {
			Identifier fromId = entry.getKey();
			GraphNode from = nodes.get(fromId);

			for (Identifier toId : entry.getValue()) {
				GraphNode to = nodes.get(toId);
				graph.addEdge(from, to);
			}
		}

		Dotty.dot2Img(graph, fileName);
		return error;
	}

	/**
	 * Generates a visual graph representation of the query tracker plan
	 */
	public Error tracePlan(String fileName) {
		fileName += planId;
		final Error error = new Error();
		final Graph graph = GraphFactory.newGraph();

		final HashMap<Identifier, GraphNode> nodes = new HashMap<Identifier, GraphNode>();

		// add nodes to plan
		for (Identifier opId : this.trackerOps.keySet()) {
			GraphNode node = graph.addNode();
			AbstractTrackerOperator op = this.trackerOps.get(opId);
			node.getInfo().setCaption(op.toString());
			node.getInfo().setHeader(op.getOutTables().toString());
			node.getInfo().setFooter(op.getInTables().toString());
			nodes.put(opId, node);
		}

		// add edges to plan
		for (Map.Entry<Identifier, Set<Identifier>> entry : this.sources
				.entrySet()) {
			Identifier fromId = entry.getKey();
			GraphNode from = nodes.get(fromId);

			for (Identifier toId : entry.getValue()) {
				GraphNode to = nodes.get(toId);
				graph.addEdge(from, to);
			}
		}

		Dotty.dot2Img(graph, fileName);
		return error;
	}
}
