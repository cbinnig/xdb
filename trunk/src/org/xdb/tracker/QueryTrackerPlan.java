package org.xdb.tracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.operators.QueryOperatorStatus;
import org.xdb.logging.XDBExecuteTimeMeasurement;
import org.xdb.logging.XDBLog;
import org.xdb.monitor.ComputeServersMonitor;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.scheduler.AbstractResourceScheduler;
import org.xdb.utils.Dotty;
import org.xdb.utils.Identifier;
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

	// last plan id
	private static Integer lastPlanId = 1;

	// unique plan id
	private final Identifier planId;

	// last tracker operator id of plan
	private Integer lastTrackerOpId = 1;

	// last execute operator id of plan
	private Integer lastExecuteOpId = 1;

	// initialized when plan is assigned to query tracker
	private transient QueryTrackerNode tracker = null;
	private transient ComputeClient computeClient = null;
	private transient AbstractResourceScheduler resourceScheduler = null;
	private ComputeServersMonitor computeServersMonitor = null;

	// tracker plan
	private final List<Identifier> trackerOpsOrder = new ArrayList<Identifier>();
	private final Map<Identifier, AbstractTrackerOperator> trackerOps = new HashMap<Identifier, AbstractTrackerOperator>();
	private final Map<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();
	private final Map<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private final Set<Identifier> roots = new HashSet<Identifier>();
	private final Set<Identifier> leaves = new HashSet<Identifier>();

	// execution plan
	private final Map<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();
	private final Map<Identifier, AbstractExecuteOperator> executeOps = new HashMap<Identifier, AbstractExecuteOperator>();
	private final Map<Identifier, Set<Identifier>> receivedReadySignals = Collections
			.synchronizedMap(new HashMap<Identifier, Set<Identifier>>());

	// helper to measure execution time
	private final XDBExecuteTimeMeasurement timeMeasure;
	private long queryExecutionTime;

	// Reentrant-Lock to manage between monitoring and signaling operators
	// Stop signaling while monitoring (redeploying) operators, and signaling
	// once finishing the monitoring.
	private final ReentrantLock monitoringLock = new ReentrantLock();
	private int monitoringInterval = Config.QUERYTRACKER_MONITOR_INTERVAL;

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
	public void setMonitoringInterval(int interval){
		this.monitoringInterval = interval;
	}
	
	public Set<Identifier> getLeaves() {
		return Collections.unmodifiableSet(leaves);
	}

	public Map<Identifier, AbstractTrackerOperator> getOperatorMapping() {
		return Collections.unmodifiableMap(trackerOps);
	}

	public void setTrackerOperators(
			Map<Identifier, AbstractTrackerOperator> trackerOps) {
		this.trackerOps.putAll(Collections.unmodifiableMap(trackerOps));
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

	public void setRoots(Set<Identifier> roots) {
		this.roots.addAll(roots);
	}

	public void setLeaves(Set<Identifier> leaves) {
		this.leaves.addAll(leaves);
	}

	public Set<Identifier> getSources(Identifier opId) {
		return this.sources.get(opId);
	}

	public Set<Identifier> getConsumers(Identifier opId) {
		return this.consumers.get(opId);
	}

	public Map<Identifier, Set<Identifier>> getAllSourcesMap() {
		return this.sources;
	}

	public Map<Identifier, Set<Identifier>> getAllConsumersMap() {
		return this.consumers;
	}

	public long getQueryExecutionTime() {
		return queryExecutionTime;
	}

	public void setQueryExecutionTime(long queryExecutionTime) {
		this.queryExecutionTime = queryExecutionTime;
	}

	public Error getLastError() {
		return err;
	}

	public void setLastError(Error err) {
		if (err.isError())
			this.err = err;
	}

	// methods
	public void initDoomDBFromQPlan(DoomDBPlan dplan){
		for(Identifier node: this.trackerOpsOrder){
			dplan.addOperator(node.toString());
		}
		
		for(Identifier from: this.consumers.keySet()){
			for(Identifier to: this.consumers.get(from)){
				dplan.addDependency(from.toString(), to.toString());
			}
		}
	}
	
	/**
	 * Assigns query tracker to plan
	 * 
	 * @param tracker
	 */
	public void assignTracker(final QueryTrackerNode tracker) {
		this.tracker = tracker;
		this.tracker.addPlan(this);
		this.computeClient = tracker.getComputeClient();
		this.computeServersMonitor = tracker.getComputeServerMonitor();
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
		trackerOpsOrder.add(opId);
		
		// add operator as leave and root
		this.leaves.add(opId);
		this.roots.add(opId);
		op.setIsRoot(true);

		this.sources.put(opId, new HashSet<Identifier>());
		this.consumers.put(opId, new HashSet<Identifier>());
	}

	/**
	 * Reset the query tracker plan for multiple runs purposes clear the
	 * assigned compute nodes to remove duplicates and clear the current
	 * deployment of the plan, and reset the timer.
	 * 
	 */
	public void reset() {
		this.resourceScheduler.clearAssignedComputeNodes();
		this.currentDeployment.clear();

		Set<Identifier> trackerOpId = trackerOps.keySet();
		for (Identifier opId : trackerOpId) {
			this.trackerOps.get(opId).setExecuted(false);
		}
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
	 * Adds a new consumer for given tracker operator with operId
	 * 
	 * @param operId
	 * @param opConsumerID
	 */
	public void addConsumer(Identifier operId, Identifier opConsumerID) {
		Set<Identifier> consumersNew = null;
		if (!this.consumers.containsKey(operId)) {
			consumersNew = new HashSet<Identifier>();
			this.setConsumers(operId, consumersNew);
		} else {
			consumersNew = this.consumers.get(operId);
		}
		consumersNew.add(opConsumerID);
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
	 * Closes all operators in the plan. Result tables are kept if configuration
	 * is set accordingly
	 * 
	 * 
	 */
	public Error cleanPlan() {
		if (!Config.COMPUTE_CLEAN_PLAN)
			return err;

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
		if (!Config.COMPUTE_CLEAN_PLAN)
			return err;

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
			// Set the operator status to RUNNING
			leaveOpDesc.setOperatorStatus(QueryOperatorStatus.RUNNING);
			err = computeClient.executeOperator(leaveOpDesc);
			if (err.isError()) {
				return err;
			}
		}

		// wait until plan is executed or error occurred
		while (!this.isExecuted() && !this.err.isError()) {
			if (Config.QUERYTRACKER_MONITOR_ACTIVATED) {
				try {
					Thread.sleep(monitoringInterval);
					
					// Starting the compute servers monitoring.
					// Lock to prevent operator signaling.
					monitoringLock.lock();
					computeServersMonitor.setQueryTrackerPlan(this);
					computeServersMonitor.startComputeServersMonitor();
					// Check if there is a failure detected, in order
					// to re-deploy the failed operators.
					if (computeServersMonitor.isFailureDetected()) {
						logger.log(Level.INFO, "Monitoring detected a failure!");
						// re-deploy the failed operators
						redeployFailedOperators();
					}

					// unlock to allow operators signaling.
					monitoringLock.unlock();

					// Wait to the next monitor
				} catch (InterruptedException e) {

					if (monitoringLock.isLocked())
						monitoringLock.unlock();
				}
			} else
				try {
					Thread.sleep(Config.QUERYTRACKER_MONITOR_INTERVAL);
				} catch (InterruptedException e) {

				}
		}

		this.timeMeasure.stop(this.getPlanId().toString());
		this.setQueryExecutionTime(this.timeMeasure.getExecutionTime(this
				.getPlanId().toString()));
		return err;
	}

	/**
	 * Deploys the query tracker plan using a given deployment
	 * 
	 * @return
	 */
	public Error deployPlan(Map<Identifier, OperatorDesc> currentDeployment) {
		// distribute plan to compute nodes
		this.currentDeployment.putAll(currentDeployment);
		distributePlan();
		if (err.isError())
			return this.err;

		// trace plan
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
		// request compute nodes
		requestComputeNodes();
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

		// trace plan
		if (Config.TRACE_EXECUTE_PLAN) {
			this.err = this.traceDeployment(this.getClass().getCanonicalName()
					+ "_EXECUTE");
		}

		// error handling
		return this.err;
	}

	/**
	 * Re-deploy the failed query operators.
	 * 
	 */
	private void redeployFailedOperators() {

		// starting traverse the query plan from the roots.
		traverseQueryTrackerPlan(this.roots);
		handleUndeployedFailedOperators();
		distributePlanRobustness();
		this.computeServersMonitor.setFailureDetected(false);
	}

	/**
	 * annotate the aborted operators that will not be executed to "neglected"
	 * so they will be neglected in the next monitoring rounds.
	 * 
	 */
	private void handleUndeployedFailedOperators() {
		for (Identifier identifier : this.currentDeployment.keySet()) {
			OperatorDesc opDesc = this.currentDeployment.get(identifier);
			if (opDesc.getOperatorStatus() == QueryOperatorStatus.ABORTED) {
				opDesc.setOperatorStatus(QueryOperatorStatus.NEGLECTED);
			}
		}

	}

	/**
	 * traverse the query tracker plan, from the roots operators until leaves.
	 * It starts checking the roots to search the failed nodes and decide which
	 * one has to be re-deployed (not all failed operators need to be
	 * re-deployed) It works as follow: once the operator is running or finished
	 * execution, then it skips checking its sources, if it is aborted, then
	 * prepares the deployment of the failed operator and continue traversing it
	 * sources. In case of leaves failure, we just re-deploy them.
	 * 
	 */
	private void traverseQueryTrackerPlan(Set<Identifier> identifiers) {
		for (Identifier opId : identifiers) {
			OperatorDesc operator = this.currentDeployment.get(opId);
			// If the operator is finished or still running, then skip checking
			// its sources.
			if (operator.getOperatorStatus() == QueryOperatorStatus.FINISHED
					|| operator.getOperatorStatus() == QueryOperatorStatus.RUNNING)
				continue;
			// if the operator is not leave and aborted, then
			// re-deploy it and check all sources.
			// In the re-deploy traverse we check the operator up to bottom.
			// That means once we met a failed operator in the way, this
			// operator
			// has to be indeed a stuck point for the whole plan.
			if (!this.leaves.contains(opId)
					&& operator.getOperatorStatus() == QueryOperatorStatus.ABORTED) {
				prepareDeployment(opId, QueryOperatorStatus.REDEPLOYED);
				traverseQueryTrackerPlan(this.getSources(opId));

			} else if (!this.leaves.contains(opId)) {
				traverseQueryTrackerPlan(this.getSources(opId));
			} else if (this.leaves.contains(opId)
					&& operator.getOperatorStatus() // if it is leave, re-deploy
													// it and continue iteration
					== QueryOperatorStatus.ABORTED) { // among the siblings.
				prepareDeployment(opId, QueryOperatorStatus.REDEPLOYED);

			}
		}

	}

	/**
	 * Requests computation nodes from master tracker
	 */
	private void requestComputeNodes() {
		// create wish-list of compute nodes
		final Set<String> requiredNodes = resourceScheduler
				.createComputeNodesWishList();

		// ask master tracker for nodes
		final Tuple<Map<String, ComputeNodeDesc>, Error> resultRequest = tracker
				.requestComputeNodes(requiredNodes);

		// read result
		final Map<String, ComputeNodeDesc> allocatedNodes = resultRequest
				.getObject1();
		this.err = resultRequest.getObject2();

		// assign nodes
		this.resourceScheduler.assignComputeNodes(allocatedNodes);
	}

	/**
	 * Prepare deployment of plan by assigning operators to compute nodes using
	 * a resource scheduler
	 */
	private void prepareDeployment() {
		for (final Identifier leave : leaves) {
			prepareDeployment(leave, QueryOperatorStatus.DEPLOYED);
			if (err.isError())
				return;
		}
	}

	/**
	 * Prepares deployment for a given operator in plan
	 * 
	 * @param operId
	 */
	private void prepareDeployment(final Identifier operId,
			QueryOperatorStatus status) {

		// operator already deployed
		if (status == QueryOperatorStatus.DEPLOYED
				&& this.currentDeployment.containsKey(operId)) {
			return;
		}

		// identify best node using a resource scheduler
		ComputeNodeDesc assignedNode = this.resourceScheduler
				.getComputeNode(operId);
		if (assignedNode == null) {
			String args[] = { "No node could be assigned to tracker operator "
					+ operId.toString() };
			this.err = new Error(EnumError.TRACKER_GENERIC, args);
			return;
		}

		// generate deployment description from operator
		final Identifier executeOpId = operId.clone();
		executeOpId.append(lastExecuteOpId++);
		final OperatorDesc executeOpDesc = new OperatorDesc(executeOpId,
				assignedNode);
		// set the status of the operator to DEPLOYED
		executeOpDesc.setOperatorStatus(status);

		if (status == QueryOperatorStatus.REDEPLOYED) {
			logger.log(Level.INFO,
					"Current Deployment has been updated with the new "
							+ "deployment of the failed operator: " + operId);

			// Kill the failed operator, and replace it with new operator in te
			// current deployment.
			OperatorDesc failedOp = this.currentDeployment.get(operId);
			// send signal to kill the operator if it is running.
			Identifier failedExecuteId = failedOp.getOperatorID();
			this.err = this.computeClient.killFailedOperator(
					failedOp.getComputeNode(), failedExecuteId);
			currentDeployment.remove(operId);
			currentDeployment.put(operId, executeOpDesc);
			return;
		}
		// add to operator and deployment description to current deployment
		currentDeployment.put(operId, executeOpDesc);

		// prepare deployment of all consumers
		for (final Identifier consumerId : consumers.get(operId)) {
			prepareDeployment(consumerId, status);
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
		for (Identifier trackerOpId: this.trackerOpsOrder) {
			final OperatorDesc executeOpDesc = this.currentDeployment.get(trackerOpId);
			
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
					executeOpDesc.getComputeNode(), execOp);
			if (this.err.isError())
				return;

			this.executeOps.put(trackerOpId, execOp);
		}
	}

	/**
	 * Distribute the failed operators
	 * 
	 */
	private void distributePlanRobustness() {
		// distribute all operators in deployment
		for (Identifier trackerOpId: this.trackerOpsOrder) {
			final OperatorDesc executeOpDesc = this.currentDeployment.get(trackerOpId);
			// Do not distribute, the deployed, running, finished operators.

			if (executeOpDesc.getOperatorStatus() != QueryOperatorStatus.REDEPLOYED)
				continue;

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
					executeOpDesc.getComputeNode(), execOp);

			if (this.err.isError())
				return;

			logger.log(Level.INFO,
					"The execute operator " + execOp.getOperatorId() + " has "
							+ "been redeployed on compute node "
							+ executeOpDesc.getComputeNode().getUrl());

			this.executeOps.put(trackerOpId, execOp);

			// If the operator is a leave, then send a start signal (execute
			// it).
			// If the operator is not a leave, send the ready signal from all
			// ready (finished resources).
			if (this.leaves.contains(trackerOpId)) {
				err = computeClient.executeOperator(executeOpDesc);
			} else {
				// Send ready signal to the operator from
				// its finished sources
				final Set<Identifier> sourceTrackerIds = execOp
						.getSourceTrackerIds();
				for (Identifier sourceTrackerId : sourceTrackerIds) {
					OperatorDesc operatorDesc = this.currentDeployment
							.get(sourceTrackerId);
					if (operatorDesc.getOperatorStatus() == QueryOperatorStatus.FINISHED) {
						err = computeClient.executeOperator(
								operatorDesc.getOperatorID(), executeOpDesc);
					}
				}
			}

			if (this.err.isError())
				return;
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

		final Map<Identifier, GraphNode> nodes = new HashMap<Identifier, GraphNode>();

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
				if (from != null && to != null)
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
			String caption = op.toString();
			if (Config.TRACE_TRACKER_SHORT_CAPTIONS)
				caption = caption.substring(0,
						Config.TRACE_TRACKER_SHORT_CAPTIONS_CHARS);
			node.getInfo().setCaption(caption);

			if (Config.TRACE_TRACKER_PLAN_HEADER)
				node.getInfo().setHeader(op.getOutTables().toString());

			if (Config.TRACE_TRACKER_PLAN_FOOTER)
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

	public Error operatorReady(AbstractExecuteOperator execOp) {
		// send READY Signal to all consumers
		if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
			monitoringLock.lock();

		Identifier execOpId = execOp.getOperatorId();
		Identifier trackerOpId = execOpId.getParentId(1);

		// check if execOpId is still in current deployment
		if (!this.currentDeployment.get(trackerOpId).getOperatorID()
				.equals(execOpId)) {
			if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
				monitoringLock.unlock();
			return err;
		}

		// check if error occured and return
		Error err = execOp.getLastError();
		if (err.isError()) {
			this.setLastError(err);
			if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
				monitoringLock.unlock();
			return err;
		}

		// otherwise continue and send signals
		this.setTrackerOperatorExecuted(trackerOpId);

		OperatorDesc readyOperator = this.currentDeployment.get(trackerOpId);
		readyOperator.setOperatorStatus(QueryOperatorStatus.FINISHED);

		final Set<Identifier> consumerTrackerIds = execOp
				.getConsumerTrackerIds();
		// Update the status of the ready operator (finished) to FINISHED
		// needed in case of failure (robustness).

		for (final Identifier consumerTrackerId : consumerTrackerIds) {

			if (consumerTrackerId != null) {
				logger.log(Level.INFO,
						"Send READY_SIGNAL from Query Tracker to consumer: "
								+ consumerTrackerId);

				OperatorDesc consumer = this.currentDeployment
						.get(consumerTrackerId);
				err = computeClient.executeOperator(execOp.getOperatorId(),
						consumer);

				setOperatorStatus(trackerOpId, consumerTrackerId);

				if (err.isError()) {
					if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
						monitoringLock.unlock();
					return err;
				}
			}
		}

		if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
			monitoringLock.unlock();
		return err;
	}

	private void setOperatorStatus(Identifier sourceId, Identifier consumerId) {
		Set<Identifier> sourceIds = null;
		if (!this.receivedReadySignals.containsKey(consumerId)) {
			sourceIds = new HashSet<Identifier>();
			sourceIds.add(sourceId);
			this.receivedReadySignals.put(consumerId, sourceIds);
		} else {
			sourceIds = this.receivedReadySignals.get(consumerId);
		}
		sourceIds.add(sourceId);
		if (sourceIds.containsAll(this.sources.get(consumerId))) {
			this.currentDeployment.get(consumerId).setOperatorStatus(
					QueryOperatorStatus.RUNNING);
		}
	}

	/**
	 * @return the receivedReadySignals
	 */
	public Map<Identifier, Set<Identifier>> getReceivedReadySignals() {
		return receivedReadySignals;
	}
}
