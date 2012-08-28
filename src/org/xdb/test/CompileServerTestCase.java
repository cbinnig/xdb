package org.xdb.test;

import org.xdb.server.CompileServer;

public class CompileServerTestCase extends TestCase {
	public CompileServerTestCase() {
		super();
	}

	@Override
	public void setUp() {
		assertNoError(CompileServer.delete());
		
		CompileServer server = new CompileServer();
		CompileServer.startServer(server);
		assertNoError(server.getError());
	}
	
	@Override
	public void tearDown(){
		CompileServer.stopServer();
	}
}