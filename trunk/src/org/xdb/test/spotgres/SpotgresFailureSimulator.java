/**
 * 
 */
package org.xdb.test.spotgres;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBClient;
import org.xdb.execute.ComputeNodeDesc;

/**
 * @author asalama
 *
 */
public class SpotgresFailureSimulator extends Thread{

	// client to kill nodes
	private DoomDBClient dbClient;

	//list of compute nodes
	private Vector<ComputeNodeDesc> nodeDescs = new Vector<ComputeNodeDesc>(); 

	private List<NodeTrace> nodesTraceList = new ArrayList<NodeTrace>(); 
	
	private List<FailedNodesSet> failedNodesList = new ArrayList<FailedNodesSet>();

	/**
	 * 
	 */
	public SpotgresFailureSimulator(DoomDBClient dbClient, List<NodeTrace> nodesTraceList) { 
		this.dbClient = dbClient;
		this.nodeDescs = (Vector<ComputeNodeDesc>) this.dbClient.getPlan().getComputeNodes(); 
		this.nodesTraceList = nodesTraceList; 
		initNodesMTBF();
	} 
	
	/**
	 * 
	 */
	private void initNodesMTBF() {
		
		for(int i=0; i<this.nodesTraceList.size(); i+=2*Config.DOOMDB_CLUSTER_SIZE - 1) 
			generateFailedNodeSet(this.nodesTraceList.subList(i, i+ Config.DOOMDB_CLUSTER_SIZE - 1), (int) Math.ceil(i/2));  
	}
    
	/**
	 * 
	 * @param subList
	 */
	private void generateFailedNodeSet(List<NodeTrace> subList, int setNumber) {
		FailedNodesSet failedNodesObj = new FailedNodesSet();  
		List<Integer> failedNodeIds = new ArrayList<Integer>(); 
		int i=0;  
		for(; i<subList.size()/2; i++){
			failedNodeIds.add(i+setNumber);
		}
		failedNodesObj.setFailureTime(subList.get(0).getFailureTime());
		failedNodesObj.setRepairTime(subList.get(i).getFailureTime()); 
		failedNodesList.add(failedNodesObj);

	}

	@Override
	public void run() { 
		System.out.println("Spot Instances Failure Simulator started"); 
		// Sleep specified time depends on the runtime of a query.
		long failureTime = 0;
		try { 
			killLoop: for (FailedNodesSet nodesSet : this.failedNodesList) {
				
				List<Integer> failedNodeIndexes = nodesSet.getNodeIds();
				failureTime = nodesSet.getFailureTime() - failureTime;

				try {
					Thread.sleep(failureTime);
				} catch (InterruptedException e) {
					break killLoop;
				} 
				
				ComputeNodeDesc node2Kill = null;
				for(int i=0; i <failedNodeIndexes.size(); i++) {
					node2Kill = this.nodeDescs.get(failedNodeIndexes.get(i));

					SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
					Date now = new java.util.Date();
					String strDate = sdfDate.format(now);

					System.err.println("Kill node " + node2Kill + " [" + strDate + "]");

					this.dbClient.killNodeSpotInstance(node2Kill, nodesSet.getRepairTime() - nodesSet.getFailureTime()); 
					if (this.dbClient.isQueryFinished())
						break killLoop;
				}

			}
		} catch (Exception e) {
			// do nothing
		} finally {
			this.interrupt();
		}
	}

}
