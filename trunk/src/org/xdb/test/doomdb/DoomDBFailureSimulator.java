package org.xdb.test.doomdb;

import java.util.Random;
import java.util.Vector;

import org.xdb.doomdb.DoomDBClient;
import org.xdb.execute.ComputeNodeDesc;

public class DoomDBFailureSimulator extends Thread {

	private DoomDBClient dbClient;
	private Vector<ComputeNodeDesc> failedNodesDesc;

	public DoomDBFailureSimulator(DoomDBClient dClient) {
		this.dbClient = dClient;
		this.failedNodesDesc = new Vector<ComputeNodeDesc>(this.dbClient
				.getPlan().getComputeNodes());
	}

	@Override
	public void run() {
		int failedNodesNumber = this.failedNodesDesc.size();
		// Note: change the condition later
		Random randomGenarator = new Random();
		// Sleep specified time depends on the runtime of a query.
		try {
			while (!this.dbClient.isQueryFinished()) {

				int failedNodeIndex = randomGenarator
						.nextInt(failedNodesNumber);
				ComputeNodeDesc node2Kill = this.failedNodesDesc
						.get(failedNodeIndex);

				int mtbf = this.dbClient.getMTBF();
				System.err.println("Kill " + node2Kill+ " "+mtbf);
				this.dbClient.killNode(node2Kill);

				try {
					Thread.sleep(mtbf * 1000);
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
