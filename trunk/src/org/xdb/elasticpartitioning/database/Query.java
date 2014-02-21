package org.xdb.elasticpartitioning.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xdb.elasticpartitioning.graph.Edge;
import org.xdb.elasticpartitioning.graph.Node;

public class Query {
	private int queryID;
	private List<Edge> joinPath;
	private Map<Node, Set<Node>> nodeNeighbors;
	
	public Query(int queryID) {
		this.queryID = queryID;
		joinPath = new ArrayList<Edge>();
		nodeNeighbors = new HashMap<Node, Set<Node>>();
	}
	
	public int getQueryID() {
		return queryID;
	}
	
	public List<Edge> getJoinPath() {
		return joinPath;
	}
	
	@Override
	public String toString() {
		return Integer.toString(queryID);
	}

	public void addEquiJoinPredicate(Edge edge) {
		joinPath.add(edge);
		
		
		Set<Node> newNeighbors;
		if (! nodeNeighbors.containsKey(edge.getSource()))
			newNeighbors = new HashSet<Node>();
		else 
			newNeighbors = nodeNeighbors.get(edge.getSource());
		
		newNeighbors.add(edge.getDestination());
		nodeNeighbors.put(edge.getSource(), newNeighbors);
	
		newNeighbors = null;
		if (! nodeNeighbors.containsKey(edge.getDestination()))
			newNeighbors = new HashSet<Node>();
		else 
			newNeighbors = nodeNeighbors.get(edge.getDestination());
		
		newNeighbors.add(edge.getSource());
		nodeNeighbors.put(edge.getDestination(), newNeighbors);
	}
	
	public Set<Node> getNeighbors(Node node){
		if (nodeNeighbors.containsKey(node)) return nodeNeighbors.get(node);
		else return null;
	}
	
	public Set<Node> getNodes(){
		return nodeNeighbors.keySet();
	}
	public boolean containsNode(Node node){
		return nodeNeighbors.containsKey(node);
	}

	public boolean isSuperSetOf(Query query){
		// If all nodes of the input query are also nodes of the current object,
		// then the current query is a superset of the input query 
		return this.nodeNeighbors.keySet().containsAll(query.nodeNeighbors.keySet());
	}
}
