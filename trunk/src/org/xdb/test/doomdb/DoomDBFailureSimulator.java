package org.xdb.test.doomdb;

import java.util.Random;
import java.util.Vector;

import org.xdb.doomdb.IDoomDBClient;
import org.xdb.execute.ComputeNodeDesc;

public class DoomDBFailureSimulator extends Thread {

	private IDoomDBClient dbClient; 
	private Vector<ComputeNodeDesc> failedNodesDesc;


	public DoomDBFailureSimulator(IDoomDBClient dClient) {  
		this.dbClient = dClient;  
		this.failedNodesDesc = new Vector<ComputeNodeDesc>(this.dbClient.getPlan().getNodes());			
	}		


	@Override
	public void run() { 
		int failedNodesNumber = this.failedNodesDesc.size();
		// Note: change the condition later  
		Random randomGenarator = new Random();  
		// Sleep specified time depends on the runtime of a query.  
		while(!this.dbClient.isQueryFinished()){ 
			int failedNodeIndex = randomGenarator.nextInt(failedNodesNumber);  
		    this.dbClient.killNode(this.failedNodesDesc.get(failedNodeIndex)); 
		    try {
				Thread.sleep(this.dbClient.getMTBF()*1000);
			} catch (InterruptedException e) {
				//do nothing
			}
		}
	}
}
