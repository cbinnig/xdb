package org.xdb.tracker.scheduler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xdb.Config;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;

public class SimulationResourceScheduler extends AbstractResourceScheduler {
	private static int PORT = Config.COMPUTE_PORT;
	private static String HOST = "127.0.0.1";
	private List<ComputeNodeDesc> allNodes = new ArrayList<ComputeNodeDesc>(10);
	
	public SimulationResourceScheduler(QueryTrackerPlan plan) {
		super(plan);
		allNodes.add(new ComputeNodeDesc(HOST, PORT));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+1));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+2));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+3));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+4));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+5));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+6));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+7));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+8));
		allNodes.add(new ComputeNodeDesc(HOST, PORT+9));
	}

	@Override
	public Set<String> createComputeNodesWishList() {
		Set<String> urls = new HashSet<String>();
		urls.add(HOST);
		return urls;
	}

	@Override
	public ComputeNodeDesc getComputeNode(Identifier operId) {
		if(operId.toString().endsWith("1"))
			return allNodes.get(0);
		else if(operId.toString().endsWith("2"))
			return allNodes.get(1);
		else if(operId.toString().endsWith("3"))
			return allNodes.get(2);
		else if(operId.toString().endsWith("4"))
			return allNodes.get(3);
		else if(operId.toString().endsWith("5"))
			return allNodes.get(4);
		else if(operId.toString().endsWith("6"))
			return allNodes.get(5);
		else if(operId.toString().endsWith("7"))
			return allNodes.get(6);
		else if(operId.toString().endsWith("8"))
			return allNodes.get(7);
		else if(operId.toString().endsWith("9"))
			return allNodes.get(8);
		else if(operId.toString().endsWith("0"))
			return allNodes.get(9);
		
		return allNodes.get(0);
	}

	@Override
	public List<ComputeNodeDesc> getAllComputeNodes(Identifier opId) {
		List<ComputeNodeDesc> operNodes = new ArrayList<ComputeNodeDesc>(10);
		operNodes.add(this.getComputeNode(opId)); 
		return operNodes;
	}

}
