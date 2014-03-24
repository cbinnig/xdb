package org.xdb.client;

import java.util.Map;
import java.util.Set;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBClusterDesc;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.DoomDBPlanDesc;
import org.xdb.doomdb.DoomDBPlanStatus;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.server.MasterTrackerServer;
import org.xdb.tracker.QueryTrackerNodeDesc;
import org.xdb.tracker.signals.RegisterSignal;
import org.xdb.utils.Tuple;

/**
 * Client to talk to Master Tracker Server.
 */
public class MasterTrackerClient extends AbstractClient {
	// constructor
	public MasterTrackerClient() {
		super(Config.MASTERTRACKER_URL, Config.MASTERTRACKER_PORT);
		logger = XDBLog.getLogger(this.getClass().getName());
	}

	/**
	 * Register a compute node at the master tracker server
	 * 
	 * @param desc
	 * @return
	 */
	public Error registerNode(final ComputeNodeDesc desc) {
		final RegisterSignal<ComputeNodeDesc> signal = new RegisterSignal<ComputeNodeDesc>(
				desc);
		Object[] args = { signal };
		return this.executeCmd(MasterTrackerServer.CMD_REGISTER_COMPUTE_NODE,
				args);
	}

	/**
	 * Register new query tracker node at the master tracker server
	 * 
	 * @param desc
	 * @return
	 */
	public Error registerNode(final QueryTrackerNodeDesc desc) {
		final RegisterSignal<QueryTrackerNodeDesc> signal = new RegisterSignal<QueryTrackerNodeDesc>(
				desc);
		Object[] args = { signal };
		return this.executeCmd(
				MasterTrackerServer.CMD_REGISTER_QUERYTRACKER_NODE, args);
	}

	/**
	 * Request compute nodes from master tracker for execution
	 * 
	 * @param requiredNodes
	 * @return
	 */
	public Tuple<Error, Map<String, ComputeNodeDesc>> requestComputeNodes(
			final Set<String> wishList) {
		Error err = new Error();
		Object[] args = { wishList };
		Tuple<Error, Object> result = this.executeCmdWithResult(
				MasterTrackerServer.CMD_REQUEST_COMPUTE_NODE, args);
		err = result.getObject1();

		@SuppressWarnings("unchecked")
		Map<String, ComputeNodeDesc> computeNodes = (Map<String, ComputeNodeDesc>) result
				.getObject2();

		return new Tuple<Error, Map<String, ComputeNodeDesc>>(err, computeNodes);
	}

	/**
	 * Execute given compile plan using master tracker
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan plan) {
		Object[] args = { plan };
		return this.executeCmd(MasterTrackerServer.CMD_EXECUTE_PLAN, args);
	}

	/**
	 * Start compute server with given description
	 * 
	 * @param compNodeDesc
	 * @return
	 */
	public Error startComputeServer(final ComputeNodeDesc compNodeDesc) {
		Object[] args = { compNodeDesc };
		return this.executeCmd(MasterTrackerServer.CMD_START_COMPUTE_SERVER,
				args);
	}
	
	/**
	 * Starts a DoomDB cluster using a running master tracker server
	 * @param clusterDesc
	 * @return
	 */
	public Error startDoomDBCluster(final DoomDBClusterDesc clusterDesc) {
		Object[] args = { clusterDesc };
		return this.executeCmd(MasterTrackerServer.CMD_DOOMDB_START_CLUSTER,
				args);
	}

	/**
	 * Generate tracker plan for given compile plan and return DoomDBPlan with
	 * operator IDs
	 */
	public Tuple<Error, DoomDBPlan> generateDoomDBPlan(final CompilePlan plan) {
		Object[] args = { plan };
		Tuple<Error, Object> result = this.executeCmdWithResult(
				MasterTrackerServer.CMD_DOOMDB_GENERATE_PLAN, args);

		Tuple<Error, DoomDBPlan> resultDoomDBPlan = new Tuple<Error, DoomDBPlan>(
				result.getObject1(), (DoomDBPlan) result.getObject2());
		return resultDoomDBPlan;
	}

	/**
	 * Execute query tracker plan for DoomDBPlan
	 * 
	 * @param dplanDesc
	 * @return
	 */
	public Error executeDoomDBPlan(final DoomDBPlanDesc dplanDesc) {
		Object[] args = { dplanDesc };
		Error err = this.executeCmd(
				MasterTrackerServer.CMD_DOOMDB_EXECUTE_PLAN, args);
		return err;
	}

	/**
	 * Checks if query tracker plan for DoomDBPlan is finished
	 * and returns current deployment
	 * 
	 * @param dplanDesc
	 * @return
	 */
	public Tuple<Error, DoomDBPlanStatus> isDoomDBPlanFinished(
			final DoomDBPlanDesc dplanDesc) {
		Object[] args = { dplanDesc };
		Tuple<Error, Object> result = this.executeCmdWithResult(
				MasterTrackerServer.CMD_DOOMDB_FINISHED_PLAN, args);
		
		return new Tuple<Error, DoomDBPlanStatus>(result.getObject1(),
				(DoomDBPlanStatus) result.getObject2());
	}
}
