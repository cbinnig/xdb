package org.xdb.client;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.server.QueryTrackerServer;

/**
 * Client to talk to Query Tracker Server
 * 
 * @author Timo Jacobs
 */
public class QueryTrackerClient extends AbstractClient {

	// constructors
	public QueryTrackerClient(final String url) {
		this.port = Config.QUERYTRACKER_PORT;
		this.url = url;

		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	/**
	 * Execute query tracker plan on query tracker
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan plan) {
		Object[] args = { plan };
		return this.executeCmd(this.url, this.port,
				QueryTrackerServer.CMD_EXECUTE_PLAN, args);
	}

	/**
	 * Signal query tracker that a given operator is ready
	 * 
	 * @param op
	 * @return
	 */
	public Error operatorReady(final AbstractExecuteOperator op) {
		Object[] args = { op };
		return this.executeCmd(this.url, this.port,
				QueryTrackerServer.CMD_OPERATOR_READY, args);
	}

	/**
	 * Stop query tracker server
	 * 
	 * @return
	 */
	public Error stopQueryTrackerServer() {
		Object[] args = {};
		return this.executeCmd(this.url, this.port,
				QueryTrackerServer.CMD_STOP_SERVER, args);
	}
	
	/**
	 * Ping query tracker server
	 * @return
	 */
	public Error pingQueryTrackerServer() {
		Object[] args = {};
		return this.executeCmd(this.url, this.port,
				QueryTrackerServer.CMD_PING_SERVER, args);
	}
}
