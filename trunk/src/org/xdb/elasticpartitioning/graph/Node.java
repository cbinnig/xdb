package org.xdb.elasticpartitioning.graph;

import java.util.ArrayList;
import java.util.List;

import org.xdb.elasticpartitioning.Table;


public class Node{
	private List<Edge> outEdges;
	private List<Edge> inEdges;
	private Table content;
	
	public Node(Table content) {
		this.content = content;
		outEdges = new ArrayList<Edge>();
		inEdges = new ArrayList<Edge>();
	}
	public Node(Node node){
		this(node.content);
	}
	
	public void setContent(Table content) {
		this.content = content;
	}
	
	public Table getContent() {
		return content;
	}
	
	public List<Edge> getOutEdges() {
		return outEdges;
	}

	public List<Edge> getInEdges() {
		return inEdges;
	}
	protected void addOutEdge(Edge e){
		this.outEdges.add(e);
		
	}
	protected void addInEdge(Edge e){
		this.inEdges.add(e);
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

