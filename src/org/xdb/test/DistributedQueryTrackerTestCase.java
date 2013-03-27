package org.xdb.test;

import junit.framework.Assert;

import org.xdb.Config;
import org.xdb.execute.ComputeNodeSlot;
import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.server.QueryTrackerServer;

/**
 * Test case which starts - a master tracker server and a query tracker server -
 * multiple compute servers (or waits for them)
 * 
 * @author cbinnig
 * 
 */
public class DistributedQueryTrackerTestCase extends TestCase {
	private MasterTrackerServer mTrackerServer;
	private ComputeServer[] computeServers;
	private QueryTrackerServer qTrackerServer;
	private ComputeNodeSlot[] computeNodeSlots;
	private int numberOfComputeServer;
	private int numberOfSlots;
	private boolean runLocal;

	// constructors
	public DistributedQueryTrackerTestCase(int numberOfComputeServer) {
		this(numberOfComputeServer, Config.TEST_SLOTS_PER_NODE);
	}

	public DistributedQueryTrackerTestCase(int numberOfComputeServer,
			int numberOfSlots) {
		super();
		this.numberOfComputeServer = numberOfComputeServer;
		this.numberOfSlots = numberOfSlots;
		this.runLocal = Config.TEST_RUN_LOCAL;
	}

	// getters and setters
	public QueryTrackerServer getQueryTrackerServer() {
		return qTrackerServer;
	}

	public MasterTrackerServer getMasterTrackerServer() {
		return mTrackerServer;
	}

	public ComputeNodeSlot getComputeSlot(int i) {
		return computeNodeSlots[i];
	}

	public boolean isRunLocal() {
		return runLocal;
	}

	//methods
	@Override
	public void setUp() {

		assertNoError(CompileServer.deleteCatalog());

		try {
			// start master tracker server
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());

			// start query tracker server
			qTrackerServer = new QueryTrackerServer();
			qTrackerServer.startServer();
			assertNoError(qTrackerServer.getError());

			// start or wait for compute servers
			this.computeServers = new ComputeServer[this.numberOfComputeServer];
			if (this.runLocal) {
				for (int i = 0; i < this.computeServers.length; ++i) {
					computeServers[i] = new ComputeServer(Config.COMPUTE_PORT
							+ i, numberOfSlots);
					computeServers[i].startServer();
					assertNoError(computeServers[i].getError());
				}
			} else {
				System.out.print("Waiting for " + computeServers.length
						+ " computer server: ");
				int noComputeServers = 0;
				while (noComputeServers < computeServers.length) {
					noComputeServers = this.mTrackerServer
							.getNoComputeServers();
					System.out.print(noComputeServers);
					Thread.sleep(1000);
				}
				System.out.println();
			}

			// initialize compute slot info
			this.computeNodeSlots = this.mTrackerServer.getComputeSlots()
					.keySet()
					.toArray(new ComputeNodeSlot[this.computeServers.length]);

		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}

	@Override
	public void tearDown() {
		mTrackerServer.stopServer();
		qTrackerServer.stopServer();
		if (this.runLocal) {
			for (int i = 0; i < this.computeServers.length; ++i) {
				this.computeServers[i].stopServer();
			}
		}
	}
}
