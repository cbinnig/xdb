package org.xdb.test;

import org.xdb.server.ComputeServer;

public class ComputeServerTestCase extends TestCase {
	public ComputeServerTestCase() {
		super();
	}
	
	@Override
	public void setUp(){
		//TODO: truncate tables in xdb_temp on local node
		
		ComputeServer server = new ComputeServer();
		ComputeServer.startServer(server);
		assertNoError(server.getError());
	}
	
	@Override
	public void tearDown(){
		ComputeServer.stopServer();
	}
}
