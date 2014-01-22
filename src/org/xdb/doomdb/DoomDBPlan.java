package org.xdb.doomdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Dotty;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphFactory;
import com.oy.shared.lm.graph.GraphNode;


public class DoomDBPlan implements Serializable, IDoomDBPlan {

	private static final long serialVersionUID = -1963805350351443744L;
	private static final String TRACE_FILE_NAME = DoomDBPlan.class.getName();
	
	//plan IDs
	private DoomDBPlanDesc planDesc = null;
	
	//operators
	private Set<String> ops = new HashSet<String>();
	
	//dependencies between operators: from -> [to]
	private Map<String, List<String>> dependencies = new HashMap<String, List<String>>();
	
	// deployment of operators on compute nodes: opId -> compute node
	private Map<Identifier, OperatorDesc> deployment = new HashMap<Identifier, OperatorDesc>();
	
	// compute nodes used in current deployment
	private Map<String, ComputeNodeDesc> nodes = new HashMap<String, ComputeNodeDesc>();
	
	public DoomDBPlan() {
	}

	public DoomDBPlan(Identifier compilePlanId, Identifier qtrackerPlanId) {
		this.planDesc = new DoomDBPlanDesc(compilePlanId, qtrackerPlanId);
	}

	public DoomDBPlanDesc getPlanDesc(){
		return this.planDesc;
	}
	
	public void addOperator(String op) {
		this.ops.add(op);
	}

	public void addDependency(String from, String to) {
		List<String> tos = null;
		if (!this.dependencies.containsKey(from)) {
			tos = new ArrayList<String>();
			this.dependencies.put(from, tos);
		} else {
			tos = this.dependencies.get(from);
		}

		tos.add(to);
	}

	public Set<String> getOperators() {
		return this.ops;
	}

	public List<String> getDependencies(String from) {
		return this.dependencies.get(from);
	}
	
	public void setDeployment(Map<Identifier, OperatorDesc> deployment){
		this.deployment.clear();
		this.deployment.putAll(deployment);
		
		for(OperatorDesc operDesc: deployment.values()){
			this.nodes.put(operDesc.getComputeNode().toString(), operDesc.getComputeNode());
		}
	}
	
	public ComputeNodeDesc getComputeNodeByOp(String opIdString){
		Identifier opId = new Identifier(opIdString);
		if(this.deployment.containsKey(opId)){
			return this.deployment.get(opId).getComputeNode();
		}
		return null;
	}
	
	public ComputeNodeDesc getComputeNode(String compNodeDesc){
		if(this.nodes.containsKey(compNodeDesc)){
			return this.nodes.get(compNodeDesc);
		}
		return null;
	}
	
	public int getComputeNodeCount(){
		return this.nodes.size();
	}
	
	public long getEstimatedTime(){
		//TODO: change hard coded estimate!!!
		return this.ops.size() * 250;
	}
	
	public boolean isAlive(String opIdString){
		Identifier opId = new Identifier(opIdString);
		
		if(!this.deployment.containsKey(opId)){
			return false;
		}
		
		OperatorDesc operDesc = this.deployment.get(opId);
		return operDesc.isAlive();
	}
	
	public String tracePlan() {
		String fileName = TRACE_FILE_NAME + this.planDesc.getCompilePlanId().toString();
		final Graph graph = GraphFactory.newGraph();

		final Map<String, GraphNode> nodes = new HashMap<String, GraphNode>();

		// add nodes to plan
		for (String opId : this.ops) {
			GraphNode node = graph.addNode();
			node.getInfo().setCaption(opId.toString());
			nodes.put(opId, node);
		}

		// add edges to plan
		for (Map.Entry<String, List<String>> entry : this.dependencies
				.entrySet()) {
			String fromId = entry.getKey();
			GraphNode from = nodes.get(fromId);

			for (String toId : entry.getValue()) {
				GraphNode to = nodes.get(toId);
				graph.addEdge(to, from);
			}
		}

		return Dotty.dot2Img(graph, fileName);
	}
}
