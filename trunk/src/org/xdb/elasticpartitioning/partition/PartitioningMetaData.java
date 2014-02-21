package org.xdb.elasticpartitioning.partition;

public class PartitioningMetaData {
	private boolean workloadDrivenPartitioning;
	private int numberOfPartitions;
	private double sampleSizeRaio;
	
	public PartitioningMetaData(boolean workloadDrivenPartitioning, int numberOfPartitions, double sampleSizeRaio) {
		this.workloadDrivenPartitioning = workloadDrivenPartitioning;
		this.numberOfPartitions = numberOfPartitions;
		this.sampleSizeRaio = sampleSizeRaio;
	}
	
	public boolean getWorkloadDrivenPartitioning() {
		return workloadDrivenPartitioning;
	}
	public int getNumberOfPartitions() {
		return numberOfPartitions;
	}
	public double getSampleSizeRaio() {
		return sampleSizeRaio;
	}
}
