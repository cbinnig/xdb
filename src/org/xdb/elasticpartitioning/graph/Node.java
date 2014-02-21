package org.xdb.elasticpartitioning.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xdb.elasticpartitioning.database.Table;


public class Node{
	private List<Edge> outEdges;
	private List<Edge> inEdges;
	private Table content;
	private Set<Node> neighbors;
	
	public Node(Table content) {
		this.content = content;
		outEdges = new ArrayList<Edge>();
		inEdges = new ArrayList<Edge>();
		neighbors = new HashSet<Node>();
	}
	
	public Node(Node original){
		this(original.content);
	}
	
	
	public void setContent(Table content) {
		this.content = content;
	}
	
	public Table getContent() {
		return content;
	}
	
	public Set<Node> getNeighbors(){
		return neighbors;
	}
	
	public List<Edge> getOutEdges() {
		return outEdges;
	}

	public List<Edge> getInEdges() {
		return inEdges;
	}
	protected void addOutEdge(Edge e){
		this.outEdges.add(e);
		this.neighbors.add(e.getDestination());
		
	}
	protected void addInEdge(Edge e){
		this.inEdges.add(e);
		this.neighbors.add(e.getSource());
	}
	
	@Override
	/**
	 * We care only about the content, not the edges.
	 */
	public boolean equals(Object obj) {
		Node toBeCompared = (Node)obj;
		return this.content.equals(toBeCompared.content);
	}
	
	@Override
	public int hashCode() {
		return this.content.hashCode();
	}
	
	@Override
	public String toString() {
		return this.content.getTableName();
	}
}

