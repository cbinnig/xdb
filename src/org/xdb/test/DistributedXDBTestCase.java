package org.xdb.test;

import junit.framework.Assert;

import org.xdb.Config;
import org.xdb.execute.ComputeNodeDesc;
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
public class DistributedXDBTestCase extends TestCase {
	protected CompileServer compileServer;
	private MasterTrackerServer mTrackerServer;
	private ComputeServer[] computeServers;
	private QueryTrackerServer qTrackerServer;
	private ComputeNodeDesc[] computeNodes;
	private int numberOfComputeServers;
	private int numberOfParts;
	private boolean runLocal;

	// constructors
	public DistributedXDBTestCase(int numberOfComputeServer) {
		this(numberOfComputeServer, Config.TEST_PARTS_PER_NODE);
	}

	public DistributedXDBTestCase(int numberOfComputeServers,
			int numberOfParts) {
		super();
		this.numberOfComputeServers = numberOfComputeServers;
		this.numberOfParts = numberOfParts;
		this.runLocal = Config.TEST_RUN_LOCAL;
	}

	// getters and setters
	public QueryTrackerServer getQueryTrackerServer() {
		return qTrackerServer;
	}

	public MasterTrackerServer getMasterTrackerServer() {
		return mTrackerServer;
	}

	public ComputeNodeDesc getComputeNode(int i) {
		return computeNodes[i];
	} 
	
	public ComputeNodeDesc[] getComputeNodes(){
		return computeNodes;
	}
	
	
	public ComputeNodeDesc[] getComputeNodes() {
		return computeNodes;
	}
	public int getNumberOfComputeNodes(){
		return computeNodes.length;
	}

	public boolean isRunLocal() {
		return runLocal;
	}

	//methods
	@Override
	public void setUp() {

		assertNoError(CompileServer.deleteCatalog());

		try {
			compileServer = new CompileServer();
			compileServer.startServer();
			assertNoError(compileServer.getError());
			
			// start master tracker server
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());

			// start query tracker server
			qTrackerServer = new QueryTrackerServer();
			qTrackerServer.startServer();
			assertNoError(qTrackerServer.getError());

			// start or wait for compute servers
			this.computeServers = new ComputeServer[this.numberOfComputeServers];
			if (this.runLocal) {
				for (int i = 0; i < this.computeServers.length; ++i) {
					computeServers[i] = new ComputeServer(Config.COMPUTE_PORT
							+ i, numberOfParts);
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
	
			}

			// initialize compute slot info
			this.computeNodes = this.mTrackerServer.getComputeSlots()
					.toArray(new ComputeNodeDesc[this.computeServers.length]); 

		} catch (Exception e) {
			Assert.assertTrue(e.toString(), false);
		}
	}

	@Override
	public void tearDown() {
		compileServer.stopServer();
		mTrackerServer.stopServer();
	} 
	
	
}
