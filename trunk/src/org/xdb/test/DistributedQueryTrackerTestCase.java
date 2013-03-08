package org.xdb.test;

import junit.framework.Assert;

import org.xdb.Config;
import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.server.QueryTrackerServer;

public class DistributedQueryTrackerTestCase extends TestCase {
	protected MasterTrackerServer mTrackerServer;
	protected ComputeServer[] computeServers;
	protected QueryTrackerServer qServer;
	private int numberOfSlots;
	
	public DistributedQueryTrackerTestCase(int numberOfComputeServer) {
		this(numberOfComputeServer, 1);
	}
	
	public DistributedQueryTrackerTestCase(int numberOfComputeServer, int numberOfSlots) {
		super();
		this.computeServers = new ComputeServer[numberOfComputeServer];
		this.numberOfSlots = numberOfSlots;
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
			
			for(int i=0; i<this.computeServers.length; ++i){
				computeServers[i] = new ComputeServer(Config.COMPUTE_PORT+i, numberOfSlots);
				computeServers[i].startServer();
				assertNoError(computeServers[i].getError());
			}

		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}
	
	@Override
	public void tearDown(){
		mTrackerServer.stopServer();
		qServer.stopServer();
		for(int i=0; i<this.computeServers.length; ++i){
			this.computeServers[i].stopServer();
		}
	}
}
