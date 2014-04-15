package org.xdb.client;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.DoomDBPlanDesc;
import org.xdb.doomdb.DoomDBPlanStatus;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.EnumXDBComponents;
import org.xdb.server.QueryTrackerServer;
import org.xdb.utils.Tuple;

/**
 * Client to talk to Query Tracker Server
 * @author cbinnig
 *
 */
public class QueryTrackerClient extends AbstractClient {

	// constructors
	public QueryTrackerClient(final String url) {
		super(url, Config.QUERYTRACKER_PORT, EnumXDBComponents.QUERY_TRACKER_CLIENT);
	}

	/**
	 * Execute query tracker plan on query tracker
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan plan) {
		Object[] args = { plan };
		return this.executeCmd(QueryTrackerServer.CMD_EXECUTE_PLAN, args);
	}

	/**
	 * Executes query tracker plan for given DoomDBPlan
	 * 
	 * @param dplanDesc
	 * @return
	 */
	public Error executeDoomDBQPlan(final DoomDBPlanDesc dplanDesc) {
		Object[] args = { dplanDesc };
		return this
				.executeCmd(QueryTrackerServer.CMD_DOOMDB_EXECUTE_PLAN, args);
	}

	/**
	 * Executes query tracker plan for given DoomDBPlan
	 * 
	 * @param dplanDesc
	 * @return
	 */
	public Tuple<Error, DoomDBPlanStatus> finishedDoomDBQPlan(
			final DoomDBPlanDesc dplanDesc) {
		Object[] args = { dplanDesc };
		Tuple<Error, Object> result = this.executeCmdWithResult(
				QueryTrackerServer.CMD_DOOMDB_FINISHED_PLAN, args);

		return new Tuple<Error, DoomDBPlanStatus>(result.getObject1(),
				(DoomDBPlanStatus) result.getObject2());
	}

	/**
	 * Generate tracker plan for given compile plan and return DoomDBPlan with
	 * operator IDs
	 */
	public Tuple<Error, DoomDBPlan> generateDoomDBPlan(final CompilePlan plan) {
		Object[] args = { plan };
		Tuple<Error, Object> result = this.executeCmdWithResult(
				QueryTrackerServer.CMD_DOOMDB_GENERATE_PLAN, args);

		Tuple<Error, DoomDBPlan> resultDoomDBPlan = new Tuple<Error, DoomDBPlan>(
				result.getObject1(), (DoomDBPlan) result.getObject2());
		return resultDoomDBPlan;
	}

	/**
	 * Signal query tracker that a given operator is ready
	 * 
	 * @param op
	 * @return
	 */
	public Error operatorReady(final AbstractExecuteOperator op) {
		Object[] args = { op };
		return this.executeCmd(QueryTrackerServer.CMD_OPERATOR_READY, args);
	}

	/**
	 * Stop query tracker server
	 * 
	 * @return
	 */
	public Error stopQueryTrackerServer() {
		Object[] args = {};
		return this.executeCmd(QueryTrackerServer.CMD_STOP_SERVER, args);
	}

	/**
	 * Ping query tracker server
	 * 
	 * @return
	 */
	public Error pingQueryTrackerServer() {
		Object[] args = {};
		return this.executeCmd(QueryTrackerServer.CMD_PING_SERVER, args);
	}
}
