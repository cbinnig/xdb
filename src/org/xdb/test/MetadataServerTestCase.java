package org.xdb.test;

import org.xdb.server.MetadataServer;

public class MetadataServerTestCase extends TestCase {
	public MetadataServerTestCase() {
		super();
	}

	@Override
	public void setUp() {
		assertNoError(MetadataServer.delete());
		assertNoError(MetadataServer.start());
	}
}