package org.xdb.elasticpartitioning.graph;


import org.xdb.elasticpartitioning.ForeignKey;

public class Edge implements Comparable<Edge>{
	private Node source;
	private Node destination;
	private double weight;
	//private String name;	// usually the concatenation of attribute name in the source node
	private ForeignKey relation;
	
	
	public Edge(ForeignKey fk, Node source, Node destination, double weight) {
		this.relation = fk;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public Edge(Edge edge) {
		this.relation = edge.relation;
		this.source = edge.source;
		this.destination = edge.destination;
		this.weight = edge.weight;
	}
	
	public Node getSource() {
		return source;
	}
	public Node getDestination() {
		return destination;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge edge) {
		if (this.weight == edge.weight) return 0;
		return this.weight < edge.weight ? 1 : -1;
	}
	
	@Override
	public String toString() {
		return source.getContent() + " --> " + destination.getContent();
	}

	public ForeignKey getRelation() {
		return relation;
	}
}
