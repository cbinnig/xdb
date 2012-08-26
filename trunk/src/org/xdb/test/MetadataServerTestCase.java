package org.xdb.test;

import org.xdb.server.MetadataServer;

public class MetadataServerTestCase extends TestCase {
	public MetadataServerTestCase() {
		super();
	}

	@Override
	public void setUp() {
		assertNoError(MetadataServer.delete());
		
		MetadataServer server = new MetadataServer();
		MetadataServer.startServer(server);
		assertNoError(server.getError());
	}
	
	@Override
	public void tearDown(){
		MetadataServer.stopServer();
	}
}