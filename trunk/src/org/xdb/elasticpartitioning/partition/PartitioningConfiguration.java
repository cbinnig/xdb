package org.xdb.elasticpartitioning.partition;

import java.util.HashMap;
import java.util.Map;

import org.xdb.elasticpartitioning.database.Table;

public class PartitioningConfiguration {
	private Map<Table, PartitioningType> partConfig;
	private double dataSize;
	
	public PartitioningConfiguration() {
		partConfig = new HashMap<Table, PartitioningType>(); 
	}
	
	public void addPartitionedTable(Table table, PartitioningType type){
		partConfig.put(table, type);
	}
	
	public Map<Table, PartitioningType> getPartitioningConfig() {
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
		for (Table table: partConfig.keySet()){
			sb.append(table + ": " + partConfig.get(table));
			sb.append("\n");
		}
		sb.append("Total data size: " + Math.round(dataSize *100.0)/100.0);
		return sb.toString();
	}
}
