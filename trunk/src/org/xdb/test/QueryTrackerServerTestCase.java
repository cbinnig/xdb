package org.xdb.test;

import org.xdb.server.ComputeServer;

public class QueryTrackerServerTestCase extends TestCase {
	public QueryTrackerServerTestCase() {
		super();
	}
	
	@Override
	public void setUp(){
		ComputeServer server = new ComputeServer();
		ComputeServer.startServer(server);
		assertNoError(server.getError());
	}
	
	@Override
	public void tearDown(){
		ComputeServer.stopServer();
	}
}
