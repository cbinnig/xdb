package org.xdb.tracker;

import java.net.InetAddress;
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
import org.xdb.logging.XDBExecuteTimeMeasurement;
import org.xdb.logging.XDBLog;
import org.xdb.utils.MutableInteger;
import org.xdb.utils.Tuple;

/**
 * Query tracker node which executes multiple query tracker plans
 * 
 * @author cbinnig
 * 
 */
public class QueryTrackerNode {
	private final ComputeClient computeClient;

	private final MasterTrackerClient masterTrackerClient;
	private final QueryTrackerNodeDesc description;
	private final XDBExecuteTimeMeasurement timeMeasure;

	// logger
	private final Logger logger;

	private QueryTrackerPlan plan;
	private CompilePlan cplan;
	
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
		//get timeMeasure Compoment for Query time Tracking
		this.timeMeasure = XDBExecuteTimeMeasurement
				.getXDBExecuteTimeMeasurement("planexecution");
	}

	// getters and setters
	/**
	 * Returns compute client of query tracker node
	 * 
	 * @return
	 */
	public ComputeClient getComputeClient() {
		return computeClient;
	}

	/**
	 * Returns URL of query tracker node
	 * 
	 * @return
	 */
	public QueryTrackerNodeDesc getDescription() {
		return this.description;
	}
	
	
	/**
	 * Transforms a CompilePlan to into multiple QueryTrackerPlans
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


	// methods

	/**
	 * Execute a given compile plan
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan cplan) {
		logger.log(Level.INFO, "Got new compileplan: " + plan);
		
		//initialize compile plan after receiving plan form master tracker server
		this.cplan = cplan;
		this.cplan.init();
		
		// TODO parallelize and optimize Plan
		
		//build query tracker plan from compile plan
		this.plan = generateQueryTrackerPlan(this.cplan);

		// tracing
		if (Config.TRACE_TRACKER_PLAN){
			plan.tracePlan(plan.getClass().getCanonicalName()+"MASTER_TRACKER");
		}
		// measure time of Plan execution

		this.timeMeasure.start(plan.getPlanId().toString());

		plan.assignTracker(this);
		//0. New Steps according to rebuild

		
		// 1. deploy plan on compute nodes
		Error err = plan.deployPlan();
		if (err.isError()) {
			plan.cleanPlanOnError();
			return err;
		}

		// 2. execute plan
		err = plan.executePlan();
		if (err.isError()) {
			plan.cleanPlanOnError();
			return err;
		}

		// 3. release compute node resources
		err = masterTrackerClient.noticeFreeSlots(plan.getSlots());
		if (err.isError()) {
			plan.cleanPlanOnError();
			return err;
		}

		// 4. clean result tables
		if (Config.COMPUTE_CLEAN_RESULTS) {
			err = plan.cleanPlan();
			if (err.isError()) {
				plan.cleanPlanOnError();
				return err;
			}
		}
		this.timeMeasure.stop(plan.getPlanId().toString());
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
	 * @param op
	 * @return
	 */
	public Error operatorReady(final AbstractExecuteOperator op) {
		Error err = new Error();
		// Send READY Signal to all Customers
		final Set<OperatorDesc> consumers = op.getConsumers();
		for (final OperatorDesc consumer : consumers) {
			if (consumer != null) {
				consumer.getOperatorNode();
				logger.log(Level.INFO, "Send READY_SIGNAL from Query Tracker "
						+ this.description.getUrl() + " to consumer: "
						+ consumer);
				err = computeClient.executeOperator(op.getOperatorId(),
						consumer);
				if (err.isError()) {
					return err;
				}

			}
		}
		return err;
	}
}
