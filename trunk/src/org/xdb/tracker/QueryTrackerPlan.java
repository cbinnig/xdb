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
import org.xdb.doomdb.DoomDBPlanStatus;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.operators.EnumOperatorStatus;
import org.xdb.logging.EnumXDBComponents;
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
	private final Map<AbstractExecuteOperator, OperatorDesc> executeOps = new HashMap<AbstractExecuteOperator, OperatorDesc>();
	private final Map<Identifier, Set<Identifier>> receivedReadySignals = Collections
			.synchronizedMap(new HashMap<Identifier, Set<Identifier>>());

	private Boolean isExecuted = false;
	private int maxAttempts = Config.QUERYTRACKER_MONITOR_ATTEMPTS;
	private Boolean monitorFailures = Config.QUERYTRACKER_MONITOR_ACTIVATED;

	// helper to measure execution time
	private final XDBExecuteTimeMeasurement timeMeasure;
	private long queryExecutionTime;

	// Reentrant-Lock to manage between monitoring and signaling
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
		this.computeServersMonitor = new ComputeServersMonitor(this);

		this.logger = XDBLog.getLogger(EnumXDBComponents.QUERY_TRACKER_SERVER);

		this.timeMeasure = XDBExecuteTimeMeasurement
				.getXDBExecuteTimeMeasurement("plan_time");
	}
	
	private void setError(Error err){
		if(!err.isError())
			return;
		
		this.err = err;
	}
	
	private boolean hasError(){
		return this.err.isError();
	}
	
	private Error getError(){
		return this.err;
	}

	public DoomDBPlanStatus getDoomDBPlanStatus() {
		DoomDBPlanStatus planStatus = new DoomDBPlanStatus(this.isExecuted(),
				this.getCurrentDeployment(), this.getError());
		return planStatus;
	}

	public void setExecuted() {
		this.isExecuted = true;
	}

	public boolean isExecuted() {
		return isExecuted;
	}

	public void setMonitoringInterval(int interval) {
		this.monitoringInterval = interval;
	}

	public Set<Identifier> getLeaves() {
		return Collections.unmodifiableSet(leaves);
	}

	public Set<Identifier> getRoots() {
		return Collections.unmodifiableSet(roots);
	}

	public void setRoots(Set<Identifier> roots) {
		this.roots.addAll(roots);
	}

	public void setLeaves(Set<Identifier> leaves) {
		this.leaves.addAll(leaves);
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

	public Set<Identifier> getSources(Identifier opId) {
		return this.sources.get(opId);
	}

	public Set<Identifier> getConsumers(Identifier opId) {
		return this.consumers.get(opId);
	}

	public long getQueryExecutionTime() {
		return queryExecutionTime;
	}

	public void setQueryExecutionTime(long queryExecutionTime) {
		this.queryExecutionTime = queryExecutionTime;
	}

	// methods
	/**
	 * Create a DoomDBPlan from QPLan
	 * 
	 * @param dplan
	 */
	public void createDoomDBFromQPlan(DoomDBPlan dplan) {
		for (Identifier node : this.trackerOpsOrder) {
			dplan.addOperator(node.toString());
		}

		for (Identifier from : this.consumers.keySet()) {
			for (Identifier to : this.consumers.get(from)) {
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
	private boolean isExecutedInternal() {
		for (Identifier rootId : this.roots) {
			if (!this.getTrackerOperator(rootId).isExecuted()) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Stops the execution and monitoring of the plan
	 */
	public void stopPlan() {
		String args[] = {"Query execution interrupted!"};
		Error err = new Error(EnumError.TRACKER_GENERIC, args);
		this.setError(err);
	}

	/**
	 * Closes all operators in the plan. Result tables are kept if configuration
	 * is set accordingly
	 * 
	 * 
	 */
	public Error cleanPlan() {
		if (!Config.COMPUTE_CLEAN_PLAN)
			return this.getError();

		// close operators which are no root operators
		for (final Entry<AbstractExecuteOperator, OperatorDesc> entry : this.executeOps
				.entrySet()) {
			final OperatorDesc operDesc = entry.getValue();
			final AbstractExecuteOperator execOp = entry.getKey();

			computeClient.closeOperator(operDesc, execOp);
		}
		return this.getError();
	}

	/**
	 * Executes a plan using a given deployment description
	 * 
	 * @param currentDeployment
	 */
	public Error executePlan() {

		if (this.hasError()) {
			return this.getError();
		}

		this.timeMeasure.start(this.getPlanId().toString());

		// start execution on leave operators
		for (final Identifier leaveId : leaves) {
			final OperatorDesc leaveOpDesc = currentDeployment.get(leaveId);
			if (leaveOpDesc.getOperatorStatus().isRepairableFailure()) {
				continue;
			} else if (leaveOpDesc.getOperatorStatus().isNonRepairableFailure()) {
				return this.getError();
			}

			// Set the operator status to RUNNING and execute operator
			this.storeReadySignals(Config.COMPUTE_NOOP_ID, leaveId);
			computeClient.executeOperator(leaveOpDesc);
		}

		// wait until plan is executed or error occurred
		int attempt = 0;
		execLoop: while (!this.isExecutedInternal() && !this.hasError()) {
			if (this.monitorFailures) {
				try {
					// Starting the compute servers monitoring.
					// Lock to prevent operator signaling.
					monitoringLock.lock();
					computeServersMonitor.monitorAllOperators();

					// Check if a failure is detected
					if (computeServersMonitor.hasDetectedFailure()) {
						logger.log(Level.INFO, "Monitoring detected a failure!");

						if (attempt >= this.maxAttempts) {
							String args[] = { "Maximal attempts reached for plan "
									+ this.getPlanId().toString() };
							this.setError(new Error(EnumError.TRACKER_GENERIC,
									args));
							monitoringLock.unlock();
							break execLoop;
						} else {
							// re-deploy the failed operators
							redeployAbortedOperators();
						}
						attempt++;
						logger.log(Level.INFO, "Monitor: start attempt "
								+ attempt);
					}

					// unlock to allow operators signaling.
					monitoringLock.unlock();

					// sleep interval
					if (!this.isExecutedInternal()) {
						this.logger.log(Level.INFO, "Monitor sleeping "
								+ monitoringInterval);
						Thread.sleep(monitoringInterval);
					}

				} catch (InterruptedException e) {

					if (monitoringLock.isLocked())
						monitoringLock.unlock();
				}
			} else {
				try {
					Thread.sleep(monitoringInterval);
				} catch (InterruptedException e) {

				}
			}
		}

		this.timeMeasure.stop(this.getPlanId().toString());
		this.setQueryExecutionTime(this.timeMeasure.getExecutionTime(this
				.getPlanId().toString()));

		this.setExecuted();

		return this.getError();
	}

	/**
	 * Deploys the query tracker plan using a given deployment
	 * 
	 * @return
	 */
	public Error deployPlan(Map<Identifier, OperatorDesc> currentDeployment) {
		// distribute plan to compute nodes
		this.currentDeployment.putAll(currentDeployment);
		deployAllOperators();
		if (this.hasError())
			return this.getError();

		// trace plan
		if (Config.TRACE_EXECUTE_PLAN) {
			this.traceDeployment(this.getClass().getCanonicalName()
					+ "_EXECUTE");
		}

		// error handling
		return this.getError();
	}

	/**
	 * Deploys the the query tracker plan by creating a deployment
	 * 
	 * @return
	 */
	public Error deployPlan() {
		// request compute nodes
		this.requestComputeNodes();
		if (this.hasError())
			return this.getError();

		// prepare deployment
		this.assignComputeNodes2AllOperators();
		if (this.hasError())
			return this.getError();

		// distribute plan to compute nodes
		this.deployAllOperators();
		if (this.hasError())
			return this.getError();

		// trace plan
		if (Config.TRACE_EXECUTE_PLAN) {
			this.traceDeployment(this.getClass().getCanonicalName()
					+ "_EXECUTE");
		}

		// error handling
		return this.getError();
	}

	/**
	 * Re-deploy the failed query operators.
	 * 
	 */
	private void redeployAbortedOperators() {
		// assign compute nodes to aborted operators
		this.assignComputeNodes2AbortedOperators();
		if (this.hasError())
			return;

		// deploy aborted operators
		this.deployAbortedOperators();
		if (this.hasError())
			return;

		// reset monitor
		this.computeServersMonitor.setFailureDetected(false);
	}

	/**
	 * Requests computation nodes from master tracker
	 */
	private void requestComputeNodes() {
		// create wish-list of compute nodes
		final Set<String> requiredNodes = resourceScheduler
				.createComputeNodesWishList();

		// ask master tracker for nodes
		final Tuple<Error, Map<String, ComputeNodeDesc>> resultRequest = tracker
				.requestComputeNodes(requiredNodes);

		// read result
		final Map<String, ComputeNodeDesc> allocatedNodes = resultRequest
				.getObject2();
		this.setError(resultRequest.getObject1());

		// assign nodes
		this.resourceScheduler.assignComputeNodes(allocatedNodes);
	}

	/**
	 * Assign new compute nodes to all operators
	 */
	private void assignComputeNodes2AllOperators() {
		for (final Identifier opId : this.trackerOpsOrder) {
			this.setError(this
					.assignComputeNode(opId, EnumOperatorStatus.DEPLOYED));
			if (this.hasError())
				return;
		}
	}

	/**
	 * Assign new compute nodes to aborted operators
	 */
	private void assignComputeNodes2AbortedOperators() {
		for (Identifier opId : trackerOpsOrder) {
			OperatorDesc operator = this.currentDeployment.get(opId);

			if (operator.getOperatorStatus().isRepairableFailure()) {
				this.setError(this.assignComputeNode(opId,
						EnumOperatorStatus.REDEPLOYED));
				
				if (this.hasError())
					return;
			}
		}
	}

	/**
	 * Prepares deployment for a given operator in plan and sets status in
	 * deployment
	 * 
	 * @param operId
	 */
	private Error assignComputeNode(final Identifier operId,
			EnumOperatorStatus status) {

		Error err = new Error();

		// identify all compute nodes for operator
		List<ComputeNodeDesc> allComputeNode = this.resourceScheduler
				.getAllComputeNodes(operId);

		// pick available compute node
		ComputeNodeDesc assignedNode = this
				.pickAvailableComputeNode(allComputeNode);
		if (assignedNode == null && Config.QUERYTRACKER_MONITOR_ACTIVATED) {
			return err;
		}
		else if (assignedNode == null){
			String args[] = { "No node could be assigned to tracker operator "
					+ operId.toString() };
			err = new Error(EnumError.TRACKER_GENERIC, args);
			return err;
		}

		// generate deployment description from operator
		final Identifier executeOpId = operId.clone();
		executeOpId.append(lastExecuteOpId++);
		final OperatorDesc executeOpDesc = new OperatorDesc(executeOpId,
				assignedNode);

		// set the status of the operator
		executeOpDesc.setOperatorStatus(status);
		currentDeployment.put(operId, executeOpDesc);

		logger.log(Level.INFO,
				"Current Deployment has been updated with the deployment of operator: "
						+ operId);
		return err;
	}

	// Ping the compute nodes and select the first one available.
	private ComputeNodeDesc pickAvailableComputeNode(
			List<ComputeNodeDesc> allComputeNode) {
		for (ComputeNodeDesc ComputeNodeDesc : allComputeNode) {
			Error err = this.computeClient.pingComputeServer(ComputeNodeDesc);
			if (!err.isError()) {
				return ComputeNodeDesc;
			}
		}
		return null;
	}

	/**
	 * Distributes plan to assigned compute nodes using a given deployment
	 * description
	 * 
	 * @param currentDeployment
	 */
	private void deployAllOperators() {
		// distribute all operators in deployment
		for (Identifier trackerOpId : this.trackerOpsOrder) {
			final OperatorDesc executeOpDesc = this.currentDeployment
					.get(trackerOpId);

			final AbstractTrackerOperator trackerOp = trackerOps
					.get(trackerOpId);

			// create executable operator and set query tracker URL
			final AbstractExecuteOperator execOp = trackerOp.genDeployOperator(
					executeOpDesc, currentDeployment);

			this.setError(trackerOp.getError());
			if (this.hasError())
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
			Tuple<Error, EnumOperatorStatus> result = computeClient
					.openOperator(executeOpDesc.getComputeNode(), execOp);
			Error opErr = result.getObject1();
			EnumOperatorStatus opStatus = result.getObject2();
			executeOpDesc.setOperatorStatus(opStatus);

			// if non-repairable error, then stop
			if (opStatus.isNonRepairableFailure()) {
				this.setError(opErr);
				return;
			}
			// if repairable error, then continue
			else if (opStatus.isRepairableFailure()) {
				continue;
			}

			this.executeOps.put(execOp, executeOpDesc);
		}
	}

	/**
	 * Distribute the failed operators
	 * 
	 */
	private void deployAbortedOperators() {
		// distribute all operators in deployment
		for (Identifier trackerOpId : this.trackerOpsOrder) {
			final OperatorDesc executeOpDesc = this.currentDeployment
					.get(trackerOpId);

			// only deploy operators with status REDEPLOY
			if (!executeOpDesc.getOperatorStatus().equals(
					EnumOperatorStatus.REDEPLOYED))
				continue;

			executeOpDesc.setOperatorStatus(EnumOperatorStatus.DEPLOYED);
			final AbstractTrackerOperator trackerOp = trackerOps
					.get(trackerOpId);

			// create executable operator and set query tracker URL
			final AbstractExecuteOperator execOp = trackerOp.genDeployOperator(
					executeOpDesc, currentDeployment);

			this.setError(trackerOp.getError());
			if (this.hasError())
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
			Tuple<Error, EnumOperatorStatus> result = computeClient
					.openOperator(executeOpDesc.getComputeNode(), execOp);
			Error opErr = result.getObject1();
			EnumOperatorStatus opStatus = result.getObject2();
			executeOpDesc.setOperatorStatus(opStatus);

			logger.log(Level.INFO, "Deployed " + trackerOpId + " with error "
					+ opErr.toString());

			// if non-repairable error, then stop
			if (opStatus.isNonRepairableFailure()) {
				this.setError(opErr);
				return;
			}// if repairable error, then stop
			else if (opStatus.isRepairableFailure()) {
				continue;
			}

			logger.log(Level.INFO,
					"The execute operator " + execOp.getOperatorId() + " has "
							+ "been redeployed on compute node "
							+ executeOpDesc.getComputeNode().getUrl());

			this.executeOps.put(execOp, executeOpDesc);

			// if the operator is a leave, then send a start signal
			if (this.leaves.contains(trackerOpId)) {
				opErr = computeClient.executeOperator(executeOpDesc);
				if(opErr.isError()){
					executeOpDesc.setOperatorStatus(EnumOperatorStatus.getRuntimeFailure());
				}
				else{
					this.storeReadySignals(Config.COMPUTE_NOOP_ID, trackerOpId);
				}
			}
			// else send the ready signal from all ready (finished resources).
			else {
				final Set<Identifier> sourceTrackerIds = execOp
						.getSourceTrackerIds();
				for (Identifier sourceTrackerId : sourceTrackerIds) {
					OperatorDesc sourceOpDesc = this.currentDeployment
							.get(sourceTrackerId);
					if (sourceOpDesc.getOperatorStatus().equals(
							EnumOperatorStatus.FINISHED)) {
						opErr = computeClient.executeOperator(
								sourceOpDesc.getOperatorID(), executeOpDesc);
						if(opErr.isError()){
							executeOpDesc.setOperatorStatus(EnumOperatorStatus.getRuntimeFailure());
						}
						else{
							this.storeReadySignals(sourceTrackerId, trackerOpId);
						}	
					}
				}
			}

			if (this.hasError())
				return;
		}
	}

	/**
	 * Collects signals from operators and send to consumer
	 * 
	 * @param execOp
	 * @return
	 */
	public Error operatorReady(AbstractExecuteOperator execOp) {
		Error opErr = new Error();

		// get lock
		if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
			monitoringLock.lock();

		Identifier execOpId = execOp.getOperatorId();
		Identifier trackerOpId = execOpId.getParentId(1);

		// check if execOpId is still in current deployment
		if (!this.currentDeployment.get(trackerOpId).getOperatorID()
				.equals(execOpId)) {
			if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
				monitoringLock.unlock();
			return opErr;
		}

		// set status
		OperatorDesc signallingOp = this.currentDeployment.get(trackerOpId);
		signallingOp.setOperatorStatus(execOp.getStatus());

		// check if error occurred and return
		if (execOp.getStatus().isNonRepairableFailure()) {
			opErr = execOp.getLastError();
			this.setError(opErr);
			
			if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
				monitoringLock.unlock();
			return opErr;
		}
		else if (execOp.getStatus().isRepairableFailure()){
			if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
				monitoringLock.unlock();
			
			return opErr;
		}

		// otherwise continue and send signals to consumers
		this.setTrackerOperatorExecuted(trackerOpId);
		final Set<Identifier> consumerTrackerIds = execOp
				.getConsumerTrackerIds();
		for (final Identifier consumerTrackerId : consumerTrackerIds) {

			logger.log(Level.INFO,
					"Send READY_SIGNAL from Query Tracker to consumer: "
							+ consumerTrackerId);

			OperatorDesc consumer = this.currentDeployment
					.get(consumerTrackerId);
			computeClient.executeOperator(execOp.getOperatorId(), consumer);

			this.storeReadySignals(trackerOpId, consumerTrackerId);
		}

		if (Config.QUERYTRACKER_MONITOR_ACTIVATED)
			monitoringLock.unlock();

		return this.getError();
	}

	/**
	 * Store READY_SIGNALs for consumer
	 * 
	 * @param sourceTrackerId
	 * @param consumerTrackerId
	 */
	private void storeReadySignals(Identifier sourceTrackerId,
			Identifier consumerTrackerId) {
		Set<Identifier> sourceIds = null;
		if (!this.receivedReadySignals.containsKey(consumerTrackerId)) {
			sourceIds = new HashSet<Identifier>();
			sourceIds.add(sourceTrackerId);
			this.receivedReadySignals.put(consumerTrackerId, sourceIds);
		} else {
			sourceIds = this.receivedReadySignals.get(consumerTrackerId);
		}
		sourceIds.add(sourceTrackerId);
		if (sourceIds.containsAll(this.sources.get(consumerTrackerId))) {

			this.currentDeployment.get(consumerTrackerId).setOperatorStatus(
					EnumOperatorStatus.RUNNING);
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
		for (Identifier opId : this.currentDeployment.keySet()) {
			GraphNode node = graph.addNode();
			OperatorDesc operDesc = this.currentDeployment.get(opId);
			node.getInfo().setCaption(
					opId.toString() + ":" + operDesc.toString());
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
						Config.TRACE_TRACKER_SHORT_CAPTIONS_CHARS) + " ... ";
			caption += " IN " + op.getTrackerOpConnections().toString();

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
}
