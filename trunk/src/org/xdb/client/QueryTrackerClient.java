package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.logging.XDBLog;
import org.xdb.server.QueryTrackerServer;
import org.xdb.tracker.QueryTrackerPlan;

/**
 * Client to talk to Query Tracker Server
 * 
 * @author Timo Jacobs
 */
public class QueryTrackerClient extends AbstractClient{

	// constructors
	public QueryTrackerClient(final String url) {
		this.port =  Config.QUERYTRACKER_PORT;
		this.url = url;
		
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	/**
	 * Execute query tracker plan on query tracker 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final QueryTrackerPlan plan) {
		Error err = new Error();

		try {
			server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(QueryTrackerServer.CMD_EXECUTE_PLAN);
			out.flush();
			out.writeObject(plan);
			out.flush();

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close(); 

		} catch (final Exception e) {
			err = createClientError(e);
		}

		return err;
	}

	/**
	 * Signal query tracker that a given operator is ready 
	 * 
	 * @param op
	 * @return
	 */
	public Error operatorReady(final AbstractExecuteOperator op) {
		Error err = new Error();
		try {
			server = new Socket(url, port);
			
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(QueryTrackerServer.CMD_OPERATOR_READY);
			out.flush();
			out.writeObject(op);
			out.flush();
			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();
			server.close();

		} catch (final Exception e) {
			err = createClientError(e);
		}
		return err;
	}
}