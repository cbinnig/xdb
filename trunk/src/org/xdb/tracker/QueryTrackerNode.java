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
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.DoomDBPlanDesc;
import org.xdb.doomdb.DoomDBPlanStatus;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.funsql.codegen.CodeGenerator;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.MaterializationAnnotationVisitor;
import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;
import org.xdb.utils.Tuple;

/**
 * Query tracker node executes and monitors multiple query tracker plan
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
		this.logger = XDBLog.getLogger(EnumXDBComponents.QUERY_TRACKER_SERVER);
	}

	// getters and setters
	/**
	 * Adds plan to monitored plans
	 * 
	 * @param plan
	 */
	public void addPlan(QueryTrackerPlan plan) {
		this.qPlans.put(plan.getPlanId(), plan);
	}

	/**
	 * Add plan with manually given id to monitored plans
	 * 
	 * @param planId
	 * @param plan
	 */
	public void addPlan(Identifier planId, QueryTrackerPlan plan) {
		this.qPlans.put(planId, plan);
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

	public Error startup() {
		return masterTrackerClient.registerNode(description);
	}

	/**
	 * Generates query tracker plan from compile plan
	 * 
	 * @param plan
	 * @return
	 */
	public Tuple<QueryTrackerPlan, Error> generateQueryTrackerPlan(
			final CompilePlan compilePlan) {
		Error err = new Error();
		CodeGenerator codeGen = new CodeGenerator(compilePlan);
		err = codeGen.generate();
		if (err.isError())
			return new Tuple<QueryTrackerPlan, Error>(null, err);

		QueryTrackerPlan qplan = codeGen.getQueryTrackerPlan();

		// trace query tracker plan
		if (Config.TRACE_TRACKER_PLAN) {
			qplan.tracePlan(qplan.getClass().getCanonicalName()
					+ "QUERY_TRACKER");
		}

		// assign query tracker to query tracker plan
		qplan.assignTracker(this);

		return new Tuple<QueryTrackerPlan, Error>(qplan, err);
	}

	/**
	 * Phase 1 of execution: prepare query tracker plan for execution
	 * 
	 * @param cplan
	 * @return
	 */
	private Tuple<Error, QueryTrackerPlan> executePlanPhase1(
			final CompilePlan cplan) {
		// initialize compile plan: get logger back
		cplan.init();
		Error err = new Error();

		// 1. annotate compile plan with materialize flags
		MaterializationAnnotationVisitor visitor = new MaterializationAnnotationVisitor();
		err = cplan.applyVisitor(visitor);
		if (err.isError()) {
			return new Tuple<Error, QueryTrackerPlan>(err, null);
		}

		// 2. generate query tracker plan
		Tuple<QueryTrackerPlan, Error> qPlanErr = generateQueryTrackerPlan(cplan);
		QueryTrackerPlan qplan = qPlanErr.getObject1();
		err = qPlanErr.getObject2();
		if (err.isError()) {
			return new Tuple<Error, QueryTrackerPlan>(err, null);
		}

		// 3. deploy query tracker plan
		err = qplan.deployPlan();
		if (err.isError()) {
			qplan.cleanPlan();
			return new Tuple<Error, QueryTrackerPlan>(err, null);
		}

		return new Tuple<Error, QueryTrackerPlan>(err, qplan);
	}

	/**
	 * Phase 2 of execution: actually execute prepared query tracker plan
	 * 
	 * @param qplan
	 * @return
	 */
	private Error executePlanPhase2(QueryTrackerPlan qplan) {
		Error err = new Error();

		// 4. Execute query tracker plan
		err = qplan.executePlan();
		if (err.isError()) {
			qplan.cleanPlan();
			return err;
		}

		// 5. Clean query tracker plan
		err = qplan.cleanPlan();
		if (err.isError()) {
			qplan.cleanPlan();
			return err;
		}
		return err;
	}

	/**
	 * Execute a given compile plan
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan cplan) {
		logger.log(Level.INFO, "Query tracker " + this.description.getUrl()
				+ " received compileplan: " + cplan.getPlanId());
		Error err = new Error();
		QueryTrackerPlan qplan = null;

		// 1. prepare execution
		Tuple<Error, QueryTrackerPlan> qPLanResult = executePlanPhase1(cplan);
		err = qPLanResult.getObject1();
		qplan = qPLanResult.getObject2();
		if (err.isError()) {
			if(qplan != null)
				qplan.cleanPlan();
			return err;
		}

		// 2. execute prepared plan
		err = this.executePlanPhase2(qplan);
		if (err.isError()) {
			qplan.cleanPlan();
			return err;
		}

		return err;
	}

	/**
	 * Generate DoomDBPlan from compile plan
	 * 
	 * @param cplan
	 * @return
	 */
	public Tuple<Error, DoomDBPlan> generateDoomDBQPlan(final CompilePlan cplan) {
		Error err = new Error();
		QueryTrackerPlan qplan = null;

		// 1. prepare execution
		Tuple<Error, QueryTrackerPlan> qPLanResult = this.executePlanPhase1(cplan);
		err = qPLanResult.getObject1();
		qplan = qPLanResult.getObject2();
		if (err.isError()) {
			qplan.cleanPlan();
			return new Tuple<Error, DoomDBPlan>(err, new DoomDBPlan());
		}

		// 2. create DoomDBPlan
		DoomDBPlan dplan = new DoomDBPlan(cplan.getPlanId(), qplan.getPlanId());
		dplan.setDeployment(qplan.getCurrentDeployment());
		dplan.setMatCompileOps(cplan.getMatOps());
		qplan.createDoomDBFromQPlan(dplan);
		return new Tuple<Error, DoomDBPlan>(err, dplan);
	}

	/**
	 * Execute a prepared query tracker plan for a given DoomDBPlan
	 * 
	 * @param dplanDesc
	 * @return
	 */
	public Error executeDoomDBQPlan(DoomDBPlanDesc dplanDesc) {
		Error err = new Error();
		if (!this.qPlans.containsKey(dplanDesc.getQtrackerPlanId())) {
			String[] args = { "Plan with id " + dplanDesc.getQtrackerPlanId()
					+ " not found in " + this.qPlans.keySet() };
			this.logger.log(Level.SEVERE, args[0]);
			return new Error(EnumError.TRACKER_GENERIC, args);
		}
		QueryTrackerPlan qplan = this.qPlans.get(dplanDesc.getQtrackerPlanId());
		err = this.executePlanPhase2(qplan);
		return err;
	}
	
	
	/**
	 * Execute a prepared query tracker plan for a given DoomDBPlan
	 * 
	 * @param dplanDesc
	 * @return
	 */
	public Error stopDoomDBQPlan(DoomDBPlanDesc dplanDesc) {
		Error err = new Error();
		if (!this.qPlans.containsKey(dplanDesc.getQtrackerPlanId())) {
			String[] args = { "Plan with id " + dplanDesc.getQtrackerPlanId()
					+ " not found in " + this.qPlans.keySet() };
			this.logger.log(Level.SEVERE, args[0]);
			return new Error(EnumError.TRACKER_GENERIC, args);
		}
		QueryTrackerPlan qplan = this.qPlans.get(dplanDesc.getQtrackerPlanId());
		qplan.stopPlan();
		
		return err;
	}

	/**
	 * Check if a query tracker plan for a given DoomDBPlan has finished running
	 * 
	 * @param dplanDesc
	 * @return
	 */
	public Tuple<Error, DoomDBPlanStatus> finishedDoomDBQPlan(DoomDBPlanDesc dplanDesc) {
		Error err = new Error();
		if (!this.qPlans.containsKey(dplanDesc.getQtrackerPlanId())) {
			String[] args = { "Plan with id " + dplanDesc.getQtrackerPlanId()
					+ " not found in " + this.qPlans.keySet() };
			this.logger.log(Level.SEVERE, args[0]);
			err = new Error(EnumError.TRACKER_GENERIC, args);
			return new Tuple<Error, DoomDBPlanStatus>(err, new DoomDBPlanStatus(false, null, err));
		}
		
		QueryTrackerPlan qplan = this.qPlans.get(dplanDesc.getQtrackerPlanId());
		DoomDBPlanStatus planStatus = qplan.getDoomDBPlanStatus();
		
		return new Tuple<Error, DoomDBPlanStatus>(err, planStatus);
	}


	/**
	 * Method used to request ComputeNodes from MasterTracker
	 * 
	 * @param wishList
	 * @return
	 */
	public Tuple<Error, Map<String, ComputeNodeDesc>> requestComputeNodes(
			final Set<String> wishList) {

		final Tuple<Error, Map<String, ComputeNodeDesc>> tuple = this.masterTrackerClient
				.requestComputeNodes(wishList);

		return tuple;
	}

	/**
	 * Signal consumers of a given operator that their input sources are ready
	 * 
	 * @param execOp
	 * @return
	 */
	public Error operatorReady(final AbstractExecuteOperator execOp) {
		//System.out.println("Start operatorReady "+execOp.getOperatorId());
		Identifier execOpId = execOp.getOperatorId();
		Identifier planId = execOpId.getParentId(0);
		QueryTrackerPlan qPlan = this.qPlans.get(planId);
		if (qPlan == null) {
			String[] args = { "Plan with id " + planId + " not found in "
					+ this.qPlans.keySet() };
			this.logger.log(Level.SEVERE, args[0]);
			return new Error(EnumError.TRACKER_GENERIC, args);
		}
		Error err =  qPlan.operatorReady(execOp);
		//System.out.println("Stop operatorReady "+execOp.getOperatorId());
		return err;
	}
}
