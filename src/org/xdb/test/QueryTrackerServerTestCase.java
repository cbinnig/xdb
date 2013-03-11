package org.xdb.test;

import junit.framework.Assert;

import org.xdb.Config;
import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.server.QueryTrackerServer;

public class QueryTrackerServerTestCase extends TestCase {
	protected MasterTrackerServer mTrackerServer;
	protected ComputeServer computeServer;
	protected QueryTrackerServer qServer;
	
	
	public QueryTrackerServerTestCase() {
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
			
			computeServer = new ComputeServer(Config.COMPUTE_PORT);
			computeServer.startServer();
			assertNoError(computeServer.getError());
		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}
	
	@Override
	public void tearDown(){
		mTrackerServer.stopServer();
		qServer.stopServer();
		computeServer.stopServer();
	}
}
