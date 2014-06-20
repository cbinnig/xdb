package org.xdb.doomdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Dotty;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphFactory;
import com.oy.shared.lm.graph.GraphNode;

/**
 * Plan info which is shipped to DoomDB client
 * 
 * @author cbinnig
 * 
 */
public class DoomDBPlan implements Serializable, IDoomDBPlan {

	private static final long serialVersionUID = -1963805350351443744L;
	private static final String TRACE_FILE_NAME = DoomDBPlan.class.getName();

	// plan IDs
	private DoomDBPlanDesc planDesc = null;

	/** execution plan info **/
	// operators
	private Set<String> ops = new HashSet<String>();

	// dependencies between operators: from -> [to]
	private Map<String, List<String>> dependencies = new HashMap<String, List<String>>();

	// deployment of operators on compute nodes: opId -> compute node
	private Map<String, OperatorDesc> deployment = new HashMap<String, OperatorDesc>();

	// compute nodes used in current deployment: name -> desc and desc2name
	private Map<String, ComputeNodeDesc> nodeName2Desc = new HashMap<String, ComputeNodeDesc>();
	private Map<ComputeNodeDesc, String> nodeDesc2Name = new HashMap<ComputeNodeDesc, String>();

	// compute nodes status: name -> status
	private Map<String, Boolean> nodeName2Status = Collections
			.synchronizedMap(new HashMap<String, Boolean>());
	
	/** compile plan info **/

	// materialized operators of cost model
	private List<Identifier> matCompileOpsIds = new Vector<Identifier>();

	// Runtime of each operator in the plan
	private Map<Identifier, Double> queryRuntimesStat = new HashMap<Identifier, Double>();
	private Map<Identifier, Double> queryMattimesStat = new HashMap<Identifier, Double>();

	// constructors
	public DoomDBPlan() {
	}

	public DoomDBPlan(Identifier compilePlanId, Identifier qtrackerPlanId) {
		this.planDesc = new DoomDBPlanDesc(compilePlanId, qtrackerPlanId);
	}

	// getters and setters
	public void setMatCompileOps(List<Identifier> matOpsIds) {
		this.matCompileOpsIds.clear();
		this.matCompileOpsIds.addAll(matOpsIds);
	}

	public DoomDBPlanDesc getPlanDesc() {
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

	public List<String> getDependencies(String from) {
		return this.dependencies.get(from);
	}

	public void setDeployment(Map<Identifier, OperatorDesc> deployment) {
		if (deployment == null)
			return;

		this.deployment.clear();
		this.nodeName2Desc.clear();
		this.nodeName2Status.clear();

		for (Entry<Identifier, OperatorDesc> entry : deployment.entrySet()) {
			OperatorDesc operDesc = entry.getValue();
			this.deployment.put(entry.getKey().toString(), operDesc);
			String nodeName = getNodeName(operDesc.getComputeNode());
			this.nodeName2Desc.put(nodeName, operDesc.getComputeNode());
			this.nodeName2Status.put(nodeName, operDesc.getOperatorStatus().isAlive());
		}
	}

	public ComputeNodeDesc getComputeNodeByOp(String opIdString) {
		Identifier opId = new Identifier(opIdString);
		if (this.deployment.containsKey(opId)) {
			return this.deployment.get(opId).getComputeNode();
		}
		return null;
	}

	public ComputeNodeDesc getComputeNode(String compNodeDesc) {
		if (this.nodeName2Desc.containsKey(compNodeDesc)) {
			return this.nodeName2Desc.get(compNodeDesc);
		}
		return null;
	}

	public Collection<ComputeNodeDesc> getComputeNodes() {
		return this.nodeName2Desc.values();
	}

	public int getComputeNodeCount() {
		return this.nodeName2Desc.size();
	}

	public void setQueryStats(Map<Identifier, Double> queryRuntimesStat,
			Map<Identifier, Double> queryMattimesStat) {
		this.queryRuntimesStat.clear();
		if (queryRuntimesStat != null) {
			this.queryRuntimesStat.putAll(queryRuntimesStat);
		}

		this.queryMattimesStat.clear();
		if (queryMattimesStat != null) {
			this.queryMattimesStat.putAll(queryMattimesStat);
		}
	}

	private String getNodeName(ComputeNodeDesc nodeDesc) {
		if (!this.nodeDesc2Name.containsKey(nodeDesc)) {
			String nodeName = nodeDesc.toString();
			this.nodeDesc2Name.put(nodeDesc, nodeName);
		}
		return this.nodeDesc2Name.get(nodeDesc);
	}
	
	// methods
	
	/**
	 * Returns estimated runtime for query
	 * @return
	 */
	public long getEstimatedTime() {
		long runtime = 0;

		// if Config.COMPILE_FT_ACTIVE is set to false, then we don't have stats
		// for queries
		// in this case, 0 is returned as the output of this function
		for (Identifier id : this.queryRuntimesStat.keySet()) {
			runtime += this.queryRuntimesStat.get(id) * 1000;
		}

		for (Identifier id : this.matCompileOpsIds) {
			runtime += this.queryMattimesStat.get(id) * 1000;
		}
		return runtime;
	}

	@Override
	public Set<String> getOperators() {
		return this.ops;
	}

	@Override
	public Collection<String> getNodes() {
		return this.nodeName2Desc.keySet();
	}

	@Override
	public String getNodeForOperator(String opId) {
		return this.getNodeName(this.getComputeNodeByOp(opId));
	}
	
	@Override
	public boolean nodeAlive(String nodeDesc) {
		if(this.nodeName2Status.containsKey(nodeDesc)){
			return this.nodeName2Status.get(nodeDesc);
		}
		return false;
	}
	
	public void killNode(String nodeDesc) {
		this.nodeName2Status.put(nodeDesc, false);
	}
	
	public void killNode(ComputeNodeDesc nodeDesc) {
		this.nodeName2Status.put(this.getNodeName(nodeDesc), false);
	}

	@Override
	public String tracePlan() {
		String fileName = TRACE_FILE_NAME
				+ this.planDesc.getCompilePlanId().toString();
		final Graph graph = GraphFactory.newGraph();

		final Map<String, GraphNode> dottyNodes = new HashMap<String, GraphNode>();

		// add nodes to plan
		for (String op : this.ops) {
			GraphNode node = graph.addNode();

			OperatorDesc operDesc = this.deployment.get(op);
			String nodeName = this.getNodeName(operDesc.getComputeNode());

			StringBuilder operText = new StringBuilder("Operator ");
			operText.append(op);
			operText.append("\n");
			operText.append(operDesc.getOperatorStatus().toString());
			operText.append(" on ");
			operText.append(nodeName);

			node.getInfo().setCaption(operText.toString());
			String color = operDesc.getOperatorStatus().getColor();
			node.getInfo().setFillColor(color);

			dottyNodes.put(op, node);
		}

		// add edges to plan
		for (Map.Entry<String, List<String>> entry : this.dependencies
				.entrySet()) {
			String fromId = entry.getKey();
			GraphNode from = dottyNodes.get(fromId);

			for (String toId : entry.getValue()) {
				GraphNode to = dottyNodes.get(toId);
				graph.addEdge(to, from);
			}
		}

		return Dotty.dot2Img(graph, fileName, "png");
	}
}
