package org.xdb.server;

import java.net.Socket;

import org.xdb.Config;

public class QueryTrackerServer extends AbstractServer {

	public QueryTrackerServer() {
		super();

		port = Config.QUERYTRACKER_PORT;
	}

	@Override
	protected void handle(final Socket client) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final QueryTrackerServer server = new QueryTrackerServer();
		server.startServer();
	}

}
