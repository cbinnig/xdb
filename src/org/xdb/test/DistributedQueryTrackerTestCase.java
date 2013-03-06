package org.xdb.test;

import junit.framework.Assert;

import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.server.QueryTrackerServer;

public class DistributedQueryTrackerTestCase extends TestCase {
	private MasterTrackerServer mTrackerServer;
	private ComputeServer computeServer1;
	private ComputeServer computeServer2;
	private QueryTrackerServer qServer;
	
	
	public DistributedQueryTrackerTestCase() {
		super();
	}
	
	@Override
	public void setUp(){
		assertNoError(CompileServer.deleteCatalog());

		try {
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());
			
			qServer = new QueryTrackerServer();
			qServer.startServer();
			assertNoError(qServer.getError());
			
			computeServer1 = new ComputeServer(55620, 1);
			computeServer1.startServer();
			assertNoError(computeServer1.getError());
			
			computeServer2 = new ComputeServer(55621, 1);
			computeServer2.startServer();
			assertNoError(computeServer2.getError());
			
		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}
	
	@Override
	public void tearDown(){
		mTrackerServer.stopServer();
		qServer.stopServer();
		computeServer1.stopServer();
		computeServer2.stopServer();
	}
}
