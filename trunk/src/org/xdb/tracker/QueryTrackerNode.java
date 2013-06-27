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
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.funsql.codegen.CodeGenerator;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractAnnotationVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.logging.XDBLog;
import org.xdb.monitor.ComputeServersMonitor;
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
	
	// Compute servers monitor 
	private ComputeServersMonitor computeServerMonitor;

	// logger
	private final Logger logger;

	// constructors
	public QueryTrackerNode() throws Exception {
		this(InetAddress.getLocalHost().getHostAddress());
	}

	public QueryTrackerNode(final String address) throws Exception {
		this.computeClient = new ComputeClient(); 
		this.computeServerMonitor = new ComputeServersMonitor();
		this.description = new QueryTrackerNodeDesc(address);
		this.masterTrackerClient = new MasterTrackerClient();
		this.logger = XDBLog.getLogger(this.getClass().getName());
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
	 * Execute a given compile plan
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan cplan) {
		logger.log(Level.INFO, "Query tracker " + this.description.getUrl()
				+ " received compileplan: " + cplan.getPlanId());

		// initialize compile plan: get logger back
		cplan.init();
		Error err = new Error();

		// 1. Parallelize compile plan
		// Parallelizer parallelizer = new Parallelizer(cplan);
		// parallelizer.parallelize();

		// 2. annotate compile plan (materialize flags, connections)
		err = annotateCompilePlan(cplan);
		if (err.isError()) {
			return err;
		}

		// 3. Generate query tracker plan
		Tuple<QueryTrackerPlan, Error> qPlanErr = generateQueryTrackerPlan(cplan);
		QueryTrackerPlan qplan = qPlanErr.getObject1();
		err = qPlanErr.getObject2();
		if (err.isError()) {
			return err;
		}

		// 4. Deploy query tracker plan
		err = qplan.deployPlan();
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}

		// 3. Execute query tracker plan
		err = qplan.executePlan();
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}

		// 4. Clean query tracker plan
		err = qplan.cleanPlan();
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}

		return err;
	}  
	
	public static Error annotateCompilePlan(CompilePlan cplan) {
		Error err = new Error();

		// Annotate Connection
		for (Identifier rootId : cplan.getRootIds()) {
			AbstractCompileOperator root = cplan.getOperators(rootId);
			AbstractAnnotationVisitor annotationVisitor = AbstractAnnotationVisitor.createAnnotationVisitor(root);
			
			err = annotationVisitor.visit();
			if (err.isError()) {
				return err;
			}
		}

		return err;

	} 
	
	
	/**
	 * Method used to request ComputeNode-Slots from MasterTracker
	 * 
	 * @param wishList
	 * @return
	 */
	public Tuple<Map<String, ComputeNodeDesc>, Error> requestComputeSlots(
			final Set<String> wishList) {

		final Tuple<Map<String, ComputeNodeDesc>, Error> tuple = this.masterTrackerClient
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
		Identifier execOpId = execOp.getOperatorId();
		Identifier planId = execOpId.getParentId(0);  
		QueryTrackerPlan qPlan = this.qPlans.get(planId); 
		if(qPlan==null){
			String[] args = {"Plan with id "+planId+" not found in "+this.qPlans.keySet()};
			this.logger.log(Level.SEVERE, args[0]);
			return new Error(EnumError.TRACKER_GENERIC, args);
		}
		return qPlan.operatorReady(execOp);
	}

	/**
	 * @return the computeServerMonitor
	 */
	public ComputeServersMonitor getComputeServerMonitor() {
		return this.computeServerMonitor;
	}

	/**
	 * @param computeServerMonitor the computeServerMonitor to set
	 */
	public void setComputeServerMonitor(ComputeServersMonitor computeServerMonitor) {
		this.computeServerMonitor = computeServerMonitor;
	}
}
