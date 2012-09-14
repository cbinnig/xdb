package org.xdb.server;

import java.net.Socket;

import org.xdb.Config;

public class QueryTrackerServer extends AbstractServer {

	public QueryTrackerServer() {
		super();
		
		this.port = Config.QUERYTRACKER_PORT;
	}

	@Override
	protected void handle(Socket client) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueryTrackerServer server = new QueryTrackerServer();
		server.startServer();
	}

}
