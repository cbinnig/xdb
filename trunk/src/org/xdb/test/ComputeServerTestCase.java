package org.xdb.test;

import org.xdb.server.ComputeServer;

public class ComputeServerTestCase extends TestCase {
	public ComputeServerTestCase() {
		super();
	}
	
	@Override
	public void setUp(){
		ComputeServer.startServer();
	}
	
	@Override
	public void tearDown(){
		ComputeServer.stopServer();
	}
}
