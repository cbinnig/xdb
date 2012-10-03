package org.xdb.test;

import junit.framework.Assert;

import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;

public class ComputeServerTestCase extends TestCase {
	
	private MasterTrackerServer mTrackerServer;
	private ComputeServer computeServer;
	
	public ComputeServerTestCase() {
		super();
	}
	
	@Override
	public void setUp(){
		//TODO: truncate tables in xdb_temp on local node
		assertNoError(CompileServer.deleteCatalog());

		try {
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());
			
			computeServer = new ComputeServer();
			computeServer.startServer();
			assertNoError(computeServer.getError());
		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}
	
	@Override
	public void tearDown(){
		mTrackerServer.stopServer();
		computeServer.stopServer();
	}
}
