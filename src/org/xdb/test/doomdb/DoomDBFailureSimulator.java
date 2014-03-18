package org.xdb.test.doomdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xdb.Config;
import org.xdb.doomdb.IDoomDBClient;
import org.xdb.execute.ComputeNodeDesc;

public class DoomDBFailureSimulator extends Thread {

	private IDoomDBClient dbClient; 
	private List<ComputeNodeDesc> failedNodesDesc = new ArrayList<ComputeNodeDesc>();


	public DoomDBFailureSimulator(IDoomDBClient dClient) { 
		this.setDbClient(dClient);  
		// read the list of "supposed" failed nodes from the config file  
		String failedNodes = Config.SHOOTED_COMPUTE_NODES; 
		this.setFailedNodesDesc(failedNodes);
			
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


	/**
	 * @return the dbClient
	 */
	public IDoomDBClient getDbClient() {
		return dbClient;
	}


	/**
	 * @param dbClient the dbClient to set
	 */
	public void setDbClient(IDoomDBClient dbClient) {
		this.dbClient = dbClient;
	}


	/**
	 * @return the failedNodesDesc
	 */
	public List<ComputeNodeDesc> getFailedNodesDesc() {
		return failedNodesDesc;
	}


	/**
	 * @param failedNodesDesc the failedNodesDesc to set
	 */
	public void setFailedNodesDesc(String failedNodes) {
		String[] failedNodesArray = failedNodes.split(",");  
		for (String computeNodeHostandPort : failedNodesArray) { 
			String host = computeNodeHostandPort.split(":")[0].trim();
			int port = Integer.parseInt(computeNodeHostandPort.split(":")[1].trim());
			
			ComputeNodeDesc computeNodeDesc = new ComputeNodeDesc(host, port); 
			this.failedNodesDesc.add(computeNodeDesc);
		}
		
	} 

}
