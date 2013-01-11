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
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
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
	private final ComputeClient computeClient = new ComputeClient();

	private final MasterTrackerClient masterTrackerClient;
	private final QueryTrackerNodeDesc description;
	private final XDBExecuteTimeMeasurement timeMeasure;

	// logger
	private final Logger logger;

	public QueryTrackerNode() throws Exception {
		final InetAddress addr = InetAddress.getLocalHost();
		this.description = new QueryTrackerNodeDesc(addr.getHostAddress());

		this.masterTrackerClient = new MasterTrackerClient();
		final Error err = masterTrackerClient.registerNode(description);

		if (err.isError()) {
			throw new IllegalArgumentException(err.toString());
		}

		this.logger = XDBLog.getLogger(this.getClass().getName());
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

	// methods

	/**
	 * Execute a given tracker plan
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final QueryTrackerPlan plan) {

		// measure time of Plan execution

		this.timeMeasure.start(plan.getPlanId().toString());

		plan.assignTracker(this);
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
	public Tuple<Map<String, MutableInteger>, Error> requestComputeSlots(
			final Map<String, MutableInteger> requiredSlots) {

		final Tuple<Map<String, MutableInteger>, Error> tuple = this.masterTrackerClient
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
