package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.logging.XDBLog;
import org.xdb.server.QueryTrackerServer;
import org.xdb.tracker.QueryTrackerPlan;

/**
 * Client for QueryTrackerServer
 * 
 * @author Timo Jacobs
 */
public class QueryTrackerClient extends AbstractClient{

	public QueryTrackerClient(final String url) {
		this(url, Config.QUERYTRACKER_PORT);
	}

	public QueryTrackerClient(final String url, final int port) {
		logger = XDBLog.getLogger(this.getClass().getName());
		this.port = port;
		this.url = url;
	}

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
}
