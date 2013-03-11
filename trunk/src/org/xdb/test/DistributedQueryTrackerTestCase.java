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
	private boolean runLocal;

	public DistributedQueryTrackerTestCase(int numberOfComputeServer,
			boolean runLocal) {
		this(numberOfComputeServer, 1, runLocal);
	}

	public DistributedQueryTrackerTestCase(int numberOfComputeServer,
			int numberOfSlots, boolean runLocal) {
		super();
		this.computeServers = new ComputeServer[numberOfComputeServer];
		this.numberOfSlots = numberOfSlots;
		this.runLocal = runLocal;
	}

	@Override
	public void setUp() {

		assertNoError(CompileServer.deleteCatalog());

		try {
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());

			qServer = new QueryTrackerServer();
			qServer.startServer();
			assertNoError(qServer.getError());

			if (this.runLocal) {
				for (int i = 0; i < this.computeServers.length; ++i) {
					computeServers[i] = new ComputeServer(Config.COMPUTE_PORT
							+ i, numberOfSlots);
					computeServers[i].startServer();
					assertNoError(computeServers[i].getError());
				}
			}
			else{
				System.out.print("Waiting for query tracker server ...");
				while(this.mTrackerServer.getNoFreeQueryTrackerSlots()<computeServers.length){
					System.out.print(".");
					Thread.sleep(1000);
				}
				System.out.println();
			}

		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}

	@Override
	public void tearDown() {
		mTrackerServer.stopServer();
		qServer.stopServer();
		if (this.runLocal) {
			for (int i = 0; i < this.computeServers.length; ++i) {
				this.computeServers[i].stopServer();
			}
		}
	}
}
