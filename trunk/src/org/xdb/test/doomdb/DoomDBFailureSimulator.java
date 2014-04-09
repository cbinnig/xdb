package org.xdb.test.doomdb;

import java.util.Random;
import java.util.Vector;

import org.xdb.doomdb.DoomDBClient;
import org.xdb.execute.ComputeNodeDesc;

public class DoomDBFailureSimulator extends Thread {

	private DoomDBClient dbClient;
	private Vector<ComputeNodeDesc> failedNodesDesc;
	private int mtbf;
	
	public DoomDBFailureSimulator(DoomDBClient dClient) {
		this.dbClient = dClient;
		this.mtbf = this.dbClient.getMTBF()*1000 / this.dbClient.getNodeCount();
		this.failedNodesDesc = new Vector<ComputeNodeDesc>(this.dbClient
				.getPlan().getComputeNodes());
	}

	@Override
	public void run() {
		int failedNodesNumber = this.failedNodesDesc.size();
		// Note: change the condition later
		Random randomGenarator = new Random();
		
		//Initial sleep
		try {
			long initSleep = (long)(Math.random()*this.mtbf);
			System.err.println("Initial sleep "+ initSleep + " ms before first failure!");
			Thread.sleep(initSleep);
		} catch (InterruptedException e) {
			// do nothing
		}
		
		// Sleep specified time depends on the runtime of a query.
		try {
			while (!this.dbClient.isQueryFinished()) {

				int failedNodeIndex = randomGenarator
						.nextInt(failedNodesNumber);
				ComputeNodeDesc node2Kill = this.failedNodesDesc
						.get(failedNodeIndex);

				System.err.println("Kill node " + node2Kill + " and sleep "+ this.mtbf + " ms");
				this.dbClient.killNode(node2Kill);

				try {
					Thread.sleep(mtbf);
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		} catch (Exception e) {
			// do nothing
		} finally {
			this.interrupt();
		}
	}
}
