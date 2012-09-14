package org.xdb.test;

import org.xdb.server.CompileServer;

public class CompileServerTestCase extends TestCase {
	private CompileServer server;
	
	public CompileServerTestCase() {
		super();
	}

	@Override
	public void setUp() {
		assertNoError(CompileServer.deleteCatalog());

		server = new CompileServer();
		server.startServer();
		assertNoError(server.getError());
	}
	
	@Override
	public void tearDown(){
		server.stopServer();
	}
}