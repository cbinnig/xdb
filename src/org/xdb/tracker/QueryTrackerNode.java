package org.xdb.tracker;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeSlot;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.funsql.codegen.CodeGenerator;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;
import org.xdb.utils.Tuple;

/**
 * Query tracker node executes and monitors multiple 
 * query tracker plan
 * 
 * @author cbinnig
 * 
 */
public class QueryTrackerNode {

	// clients to talk to other nodes
	private final ComputeClient computeClient;
	private final MasterTrackerClient masterTrackerClient;

	// self-description of query tracker
	private final QueryTrackerNodeDesc description;

	// query tracker plans 
	private Map<Identifier, QueryTrackerPlan> qPlans = new HashMap<Identifier, QueryTrackerPlan>();
	
	// logger
	private final Logger logger;

	// constructors
	public QueryTrackerNode() throws Exception {
		this(InetAddress.getLocalHost().getHostAddress());
	}

	public QueryTrackerNode(final String address) throws Exception {

		this.computeClient = new ComputeClient();
		this.description = new QueryTrackerNodeDesc(address);

		this.masterTrackerClient = new MasterTrackerClient();
		final Error err = masterTrackerClient.registerNode(description);
		if (err.isError()) {
			throw new IllegalArgumentException(err.toString());
		}

		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	// getters and setters
	/**
	 * Adds plan to monitored plans
	 * @param plan
	 */
	public void addPlan(QueryTrackerPlan plan){
		this.qPlans.put(plan.getPlanId(), plan);
	}
	
	/**
	 * Returns compute client of query tracker node
	 * 
	 * @return
	 */
	public ComputeClient getComputeClient() {
		return computeClient;
	}

	/**
	 * Returns self-description of query tracker
	 * 
	 * @return
	 */
	public QueryTrackerNodeDesc getDescription() {
		return this.description;
	}

	// methods

	/**
	 * Generates query tracker plan from compile plan
	 * 
	 * @param plan
	 * @return
	 */
	public QueryTrackerPlan generateQueryTrackerPlan(
			final CompilePlan compilePlan) {
		Error err = new Error();
		CodeGenerator codeGen = new CodeGenerator(compilePlan);
		err = codeGen.generate();
		if (err.isError())
			return null;

		return codeGen.getQueryTrackerPlan();
	}
	
	/**
	 * Execute a given compile plan
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan cplan) {
		logger.log(Level.INFO, "Got new compileplan: " + cplan.getPlanId());

		// initialize compile plan after shipping plan 
		cplan.init();

		// build query tracker plan from compile plan
		QueryTrackerPlan qplan = generateQueryTrackerPlan(cplan);
		
		// trace query tracker plan
		if (Config.TRACE_TRACKER_PLAN) {
			qplan.tracePlan(qplan.getClass().getCanonicalName()
					+ "QUERY_TRACKER");
		}
		
		// 0. assign query tracker to query tracker plan
		qplan.assignTracker(this);
		
		// 1. deploy query tracker plan on compute nodes
		Error err = qplan.deployPlan();
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}

		// 2. execute query tracker plan
		err = qplan.executePlan();
		
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}

		// 3. release compute nodes
		err = masterTrackerClient.noticeFreeSlots(qplan.getSlots());
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}

		// 4. clean query tracker plan
		if (Config.COMPUTE_CLEAN_RESULTS) {
			err = qplan.cleanPlan();
		} else {
			err = qplan.cleanPlanWithoutRoots();
		}
		
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}
		
		return err;
	}

	/**
	 * Method used to request ComputeNode-Slots from MasterTracker
	 * 
	 * @param requiredSlots
	 * @return
	 */
	public Tuple<Map<ComputeNodeSlot, MutableInteger>, Error> requestComputeSlots(
			final Map<String, MutableInteger> requiredSlots) {

		final Tuple<Map<ComputeNodeSlot, MutableInteger>, Error> tuple = this.masterTrackerClient
				.requestComputeNodes(requiredSlots);

		return tuple;
	}

	/**
	 * Signal consumers of a given operator that their input sources are ready
	 * 
	 * @param execOp
	 * @return
	 */
	public Error operatorReady(final AbstractExecuteOperator execOp) {
		Error err = execOp.getLastError();
		
		// get trackerOpId and set status to executed if no error occurred
		Identifier execOpId = execOp.getOperatorId();
		Identifier planId = execOpId.getParentId(0);
		Identifier trackerOpId = execOpId.getParentId(1);
		
		QueryTrackerPlan qPlan = this.qPlans.get(planId);
		if(err.isError())
			return err;
		
		qPlan.setTrackerOperatorExecuted(trackerOpId);
		
		// send READY Signal to all consumers
		final Set<OperatorDesc> consumers = execOp.getConsumers();
		for (final OperatorDesc consumer : consumers) {
			if (consumer != null) {
				consumer.getComputeSlot();
				logger.log(Level.INFO, "Send READY_SIGNAL from Query Tracker "
						+ this.description.getUrl() + " to consumer: "
						+ consumer);
				err = computeClient.executeOperator(execOp.getOperatorId(),
						consumer);
				
				if (err.isError())
					return err;
			}
		}
		return err;
	}
}
