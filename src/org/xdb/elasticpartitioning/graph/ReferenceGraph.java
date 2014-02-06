package org.xdb.elasticpartitioning.graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xdb.elasticpartitioning.ForeignKey;
import org.xdb.elasticpartitioning.Table;



public class ReferenceGraph {
	private List<Node> nodes;
	private List<Edge> edges;
	private Map<String, Node> tablenameToNodeMap;
	

	public ReferenceGraph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		tablenameToNodeMap = new HashMap<String, Node>();
	}
	public void addNode(Node node){
		this.nodes.add(node);
		tablenameToNodeMap.put(node.getContent().getTableName(), node);
	}

	public Edge addEdge(ForeignKey fk, Node source, Node target, double weight){
		Edge e = new Edge(fk, source, target, weight);
		source.addOutEdge(e);
		target.addInEdge(e);
		edges.add(e);
		return e;	
	}

	public List<Node> getNodes() {
		return nodes;
	}

	@Override
	public String toString() {
		String output = new String();
		for (Node n : this.getNodes()){
			for (Edge e : n.getOutEdges()){
				output += n + " --> " + e.getDestination() + " [weight: " + e.getWeight() + "]";
				output += '\n';
			}
		}
		return output;
	}

	public ReferenceGraph findMASP(){
		Map<Table,Set<Table>> forest = new HashMap<Table,Set<Table>>();
		for(Node node: this.nodes){
			//Each set stores the known vertices reachable from this vertex
			//initialize with it self.
			Set<Table> vs = new HashSet<Table>();
			vs.add(node.getContent());
			forest.put(node.getContent(), vs);
		}
		
		// make a copy of edges. We don't want to mess with the real edges
		List<Edge> copyEdges = new ArrayList<Edge>();
		for (Edge edge : edges)
			copyEdges.add(new Edge(edge));
		
		// Creating an empty graph, and copying nodes (not edges) from the original ReferenceGraph
		ReferenceGraph MASP = new ReferenceGraph();
		for (Node node: nodes)
			MASP.addNode(new Node(node));
		
		//sorting the edges
		Collections.sort(copyEdges);
		
		
		while(MASP.edges.size() < this.nodes.size()-1) //while you haven't visited all the vertices at least once
		{
		    Edge edge = copyEdges.remove(0); // popping the edge with the largest weight

		    Set<Table> visited1 = forest.get(edge.getSource().getContent());
		    Set<Table> visited2 = forest.get(edge.getDestination().getContent());
		    if (visited1.equals(visited2)) continue;
		    
		    Node sourceInMASP = MASP.getNodeWithTableName(edge.getSource().getContent().getTableName());
		    Node targetInMASP = MASP.getNodeWithTableName(edge.getDestination().getContent().getTableName());
		    
		    MASP.addEdge(edge.getRelation(), sourceInMASP, targetInMASP, edge.getWeight());
		    visited1.addAll(visited2);
		    for(Table t : visited1)
		    	forest.put(t, visited1);
		}
		return MASP;
	}
	
	public Node getNodeWithTableName(String tableName) {
		if (tablenameToNodeMap.containsKey(tableName))
			return tablenameToNodeMap.get(tableName);
		else return null;
	}
	
	/*
	public static void main(String[] args) {
		Table tablea = new Table("a");
		Node a = new Node(tablea);
		
		Table tableb = new Table("b");
		Node b = new Node(tableb);
		
		Table tablec = new Table("c");
		Node c = new Node(tablec);
		
		Table tabled = new Table("d");
		Node d = new Node(tabled);
		
		Table tablee = new Table("e");
		Node e = new Node(tablee);
		
		Table tablef = new Table("f");
		Node f = new Node(tablef);
		
		ReferenceGraph graph = new ReferenceGraph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		
		graph.addEdge("atob", a, b, 200);
		graph.addEdge("btoc", b, c, 10);
		graph.addEdge("btod", b, d, 20);
		graph.addEdge("ctod", c, d, 30);
		graph.addEdge("dtoe", d, e, 20);
		graph.addEdge("dtof", d, f, 130);
		graph.addEdge("etof", e, f, 100);
		graph.addEdge("ftoa", f, a, 50);
		
		ReferenceGraph masp = graph.findMASP();
		System.out.println(masp);	
	}
	*/
}