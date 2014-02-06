package org.xdb.elasticpartitioning;

import java.util.HashMap;
import java.util.Map;

import org.xdb.elasticpartitioning.graph.Node;

public class PartitioningConfiguration {
	private Map<Node, PartitioningType> partConfig;
	private double dataSize;
	
	public PartitioningConfiguration() {
		partConfig = new HashMap<Node, PartitioningType>(); 
	}
	
	public void addPartitionedNode(Node node, PartitioningType type){
		partConfig.put(node, type);
	}
	
	public Map<Node, PartitioningType> getPartitioningConfig() {
		return partConfig;
	}
	
	public double getDataSize() {
		return dataSize;
	}
	
	public void setDataSize(double dataSize) {
		this.dataSize = dataSize;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node node: partConfig.keySet()){
			sb.append(node.getContent() + ": " + partConfig.get(node));
			sb.append("\n");
		}
		sb.append("Total data size: " + Math.round(dataSize *100.0)/100.0);
		return sb.toString();
	}
}
