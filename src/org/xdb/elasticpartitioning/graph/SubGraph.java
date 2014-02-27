package org.xdb.elasticpartitioning.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xdb.elasticpartitioning.database.Query;
import org.xdb.elasticpartitioning.partition.PartitioningConfiguration;

public class SubGraph {
	private List<ReferenceGraph> connectedComponents;
	
	public SubGraph() {
		this.connectedComponents = new ArrayList<ReferenceGraph>();
	}
	
	public SubGraph(SubGraph original) throws Exception {
		this();
		for (ReferenceGraph connectedComponent: original.connectedComponents)
			this.connectedComponents.add(new ReferenceGraph(connectedComponent));
	}
	
	public List<ReferenceGraph> getConnectedComponents() {
		return connectedComponents;
	}
	
	public double getTotalCost() throws GraphNotConnectedException{
		double cost = 0;
		for (ReferenceGraph component: connectedComponents)
			cost += component.getPartitioningConfiguration().getDataSize();
		return cost;
	}

	public void addConnectedComponent(List<Query> querySet) throws Exception{
		ReferenceGraph refGraph = formReferenceGraph(querySet);
		PartitioningConfiguration partConfig = refGraph.findOptimalConfigurationInAConnectedComponent();
		refGraph.setPartitioningConfiguration(partConfig);
		connectedComponents.add(refGraph);
	}
	
	private static ReferenceGraph formReferenceGraph(List<Query> querySet) throws Exception {
		ReferenceGraph refGraph = new ReferenceGraph();
		for (Query query: querySet){
			for(Edge edge: query.getJoinPath()){
				if (refGraph.containsEdge(edge))continue;
				
				if (!refGraph.containsNode(edge.getSource()))
					refGraph.addNode(new Node(edge.getSource().getContent()));
				
				if (!refGraph.containsNode(edge.getDestination()))
					refGraph.addNode(new Node(edge.getDestination().getContent()));
				
				refGraph.addEdge(edge.getSource().getContent().getTableName(),
						edge.getDestination().getContent().getTableName(),
						edge.getRelation().getFirstSourceAttribute(),
						edge.getRelation().getFirstTargetAttribute());
			}
			refGraph.addQuery(query);
		}
		return refGraph;
	}

	public SubGraph mergeQuery(ReferenceGraph singleQueryReferenceGraph) throws Exception{
		Query query = singleQueryReferenceGraph.getQueries().get(0);
		double leastOverhead = Double.MAX_VALUE;
		ReferenceGraph chosenRefGraph = null;
		int bestIndex = -1;
		for (int i=0; i < connectedComponents.size(); i++){
			ReferenceGraph connectedComponent = connectedComponents.get(i);
			if (SubGraph.isMergible(connectedComponent, query)){
				ReferenceGraph temp = connectedComponent.mergeWith(query);
				temp.setPartitioningConfiguration(temp.findOptimalConfigurationInAConnectedComponent());
				double overhead = temp.getPartitioningConfiguration().getDataSize() - connectedComponents.get(i).getPartitioningConfiguration().getDataSize();
				if (bestIndex == -1 || overhead < leastOverhead){
					leastOverhead = overhead;
					bestIndex = i;
					chosenRefGraph = temp;
				}
			}
		}

		SubGraph bestMergedResult = new SubGraph(this);
		
		if (singleQueryReferenceGraph.getPartitioningConfiguration().getDataSize() < leastOverhead)
			bestMergedResult.connectedComponents.add(new ReferenceGraph(singleQueryReferenceGraph));
		else{
			bestMergedResult.connectedComponents.remove(bestIndex);
			bestMergedResult.connectedComponents.add(bestIndex, chosenRefGraph);
		}

		return bestMergedResult;	
	}

	private static boolean isMergible(ReferenceGraph connectedComponent, Query query) throws Exception {
		/**
		 *  Like directed graphs, we can use DFS to detect cycle in an undirected graph
		 *  in O(V+E) time. We do a DFS traversal of the given graph. For every visited
		 *  vertex ÔvÕ, if there is an adjacent ÔuÕ such that u is already visited and u
		 *  is not parent of v, then there is a cycle in graph. If we donÕt find such an
		 *  adjacent for any vertex, we say that there is no cycle. The assumption of
		 *  this approach is that there are no parallel edges between any two vertices.
		 */
		
		// First, we need to check whether they are connected at all or no.
		// Because if not, then they are mergible.
		boolean connected = false;
		for (Node node: query.getNodes()){
			if (connectedComponent.containsNode(node)) {
				connected = true;
				break;
			}
		}
		if (!connected) return false;
		
		// Now that we know they are connected, let's see if they are mergible.
		Node root = connectedComponent.getNodes().get(0);
		Set<Node> visited = new HashSet<Node>();
		return isMergibleHelper(connectedComponent, query, visited, root,  null);
		
		/*
		boolean connectedFlag = false;	// we're not sure if "connectedComponent" and "query" are actually connected.
		for (Edge edge: query.getJoinPath()){
			if (connectedComponent.containsNode(edge.getSource())
					|| connectedComponent.containsNode(edge.getDestination()))
				connectedFlag = true;
			if(!connectedComponent.containsEdge(edge)){
				if (connectedComponent.containsNode(edge.getSource()));
				if (connectedComponent.containsNode(edge.getDestination()));	
			}
		}
		return connectedFlag;
		*/
	}
	
	private static boolean isMergibleHelper(ReferenceGraph refGraph, Query query,
			Set<Node> visited, Node current, Node parent) throws Exception {
		
		visited.add(current);
		
		// building the set of neighbors which are either adjacent to the node in the refgraph or 
		// to the node in the query
		Set<Node> unionNeighbors = new HashSet<Node>();
		if (refGraph.containsNode(current))
			unionNeighbors.addAll(refGraph.getNeighbors(current.getContent().getTableName()));
		if (query.containsNode(current))
			unionNeighbors.addAll(query.getNeighbors(current));
		
		for (Node neighbor: unionNeighbors){
			
			// If an adjacent is not visited, then recur for that adjacent
			if (!visited.contains(neighbor)){
				if (!isMergibleHelper(refGraph, query, visited, neighbor, current)) return false;
			}
			
			// If an adjacent is visited and not parent of current vertex,
	        // then there is a cycle.
			else if (!neighbor.equals(parent)) return false;
		}
		return true;
	}

	public String getStringRepresentation(){
		List<Integer> flatList = new ArrayList<Integer>();
		for (ReferenceGraph connectedComponent: connectedComponents)
			for (Query query: connectedComponent.getQueries())
				flatList.add(query.getQueryID());
		Collections.sort(flatList);
		return flatList.toString();
	}
	
	@Override
	public String toString() {
		return getStringRepresentation();
	}
}
