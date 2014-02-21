package org.xdb.elasticpartitioning.graph;


import org.xdb.elasticpartitioning.database.ForeignKey;

public class Edge implements Comparable<Edge>{
	private Node source;
	private Node destination;
	private double weight;
	private ForeignKey relation;
	private String normalizedStringRepresentation;
	
	
	public Edge(ForeignKey fk, Node source, Node destination, double weight) {
		this.relation = fk;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		
		String sourceTable = this.getSource().getContent().getTableName();
		String targetTable = this.getDestination().getContent().getTableName();
		String sourceAttribute = this.getRelation().getFirstSourceAttribute();
		String targetAttribute = this.getRelation().getFirstTargetAttribute();
		
		this.normalizedStringRepresentation = normalizedStringRepresentation(sourceTable,
				targetTable,
				sourceAttribute,
				targetAttribute);
	}
	
	public Edge copyEdge(){
		Edge edge = new Edge(relation, source, destination, weight);
		return edge;
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
		return normalizedStringRepresentation;
	}

	public ForeignKey getRelation() {
		return relation;
	}
	
	@Override
	public int hashCode() {
		return normalizedStringRepresentation.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Edge toBeCompared = (Edge)obj;
		return this.getNormalizedStringRepresentation()
				.equals(toBeCompared.getNormalizedStringRepresentation());
		
	}
	
	public String getNormalizedStringRepresentation() {
		return normalizedStringRepresentation;
	}
	
	public static String normalizedStringRepresentation(String sourceTable, String targetTable, String sourceAttribute, String targetAttribute){
		StringBuilder sb = new StringBuilder();
		
		/*
		String sourceTable = this.getSource().getContent().getTableName();
		String targetTable = this.getDestination().getContent().getTableName();
		String sourceAttribute = this.getRelation().getFirstSourceAttribute();
		String targetAttribute = this.getRelation().getFirstTargetAttribute();
		 */
		if (sourceTable.compareTo(targetTable) < 0){
			sb.append(sourceTable);
			sb.append(".");
			sb.append(sourceAttribute);
			sb.append("|");
			sb.append(targetTable);
			sb.append(".");
			sb.append(targetAttribute);		
		}
		else{
			sb.append(targetTable);
			sb.append(".");
			sb.append(targetAttribute);
			sb.append("|");
			sb.append(sourceTable);
			sb.append(".");
			sb.append(sourceAttribute);
		}
		return sb.toString();
	}
}
