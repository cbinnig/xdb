package org.xdb.spotgres.pojos;

public class ClusterConstraints {
	int cuCount;
	int ramPerCu;
	// int nodeCount;
	// float availability; //?
	// int dailyOperationalMinutes;
	float connectivity;
	float moneyPerHour;
	long replicationFactor;
	public int getCuCount() {
		return cuCount;
	}
	public void setCuCount(int cuCount) {
		this.cuCount = cuCount;
	}
	public int getRamPerCu() {
		return ramPerCu;
	}
	public void setRamPerCu(int ramPerCu) {
		this.ramPerCu = ramPerCu;
	}
	public float getConnectivity() {
		return connectivity;
	}
	public void setConnectivity(float connectivity) {
		this.connectivity = connectivity;
	}
	public float getMoneyPerHour() {
		return moneyPerHour;
	}
	public void setMoneyPerHour(float moneyPerHour) {
		this.moneyPerHour = moneyPerHour;
	}
	public long getReplicationFactor() {
		return replicationFactor;
	}
	public void setReplicationFactor(long replicationFactor) {
		this.replicationFactor = replicationFactor;
	}
	@Override
	public String toString() {
		return "ClusterConstraints [cuCount=" + cuCount + ", ramPerCu=" + ramPerCu + ", connectivity=" + connectivity
				+ ", moneyPerHour=" + moneyPerHour + ", replicationFactor=" + replicationFactor + "]";
	}
	
	
}
