package org.xdb.test;

import junit.framework.Assert;

import org.xdb.Config;
import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.server.QueryTrackerServer;

public class XDBTestCase extends TestCase {
	protected CompileServer compileServer;
	protected MasterTrackerServer mTrackerServer;
	protected QueryTrackerServer qTrackerServer;
	protected ComputeServer computeServer;
	
	public XDBTestCase() {
		super();
	}
	
	@Override
	public void setUp(){
		assertNoError(CompileServer.deleteCatalog());

		try {
			compileServer = new CompileServer();
			compileServer.startServer();
			assertNoError(compileServer.getError());
			
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());
			
			qTrackerServer = new QueryTrackerServer();
			qTrackerServer.startServer();
			assertNoError(qTrackerServer.getError());
		
			computeServer = new ComputeServer(Config.COMPUTE_PORT);
			computeServer.startServer();
			assertNoError(computeServer.getError());
			
		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}
	
	@Override
	public void tearDown(){
		compileServer.stopServer();
		mTrackerServer.stopServer();
	}
}