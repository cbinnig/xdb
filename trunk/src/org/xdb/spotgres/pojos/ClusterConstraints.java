package org.xdb.spotgres.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "ClusterConstraints")
public class ClusterConstraints {
	

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="constraintsId")
	int constraintsId;
	
	@Column(name="cuCount")
	int cuCount;
	@Column(name="ramPerCu")
	int ramPerCu;
	// int nodeCount;
	// float availability; //?
	// int dailyOperationalMinutes;
	
	@Column(name="connectivity")
	float connectivity;
	
	@Column(name="moneyPerHour")
	float moneyPerHour;
	
	@Column(name="replicationFactor")
	long replicationFactor;
	
	@Column(name="safetyBuffer")
	int safetyBuffer = 10;
	
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
	
	public float getMoneyPerCUPerHour(){
		return this.moneyPerHour/getCuCountInclBuffer();
	}
	
	public long getReplicationFactor() {
		return replicationFactor;
	}
	public void setReplicationFactor(long replicationFactor) {
		this.replicationFactor = replicationFactor;
	}
	
	public int getSafetyBuffer() {
		return safetyBuffer;
	}
	public void setSafetyBuffer(int safetyBuffer) {
		this.safetyBuffer = safetyBuffer;
	}
	
	public int getCuCountInclBuffer(){
		return Double.valueOf(Math.ceil(cuCount * (1 + safetyBuffer / 100))).intValue();
	}
	
	@Override
	public String toString() {
		return "ClusterConstraints [cuCount=" + cuCount + ", ramPerCu=" + ramPerCu + ", connectivity=" + connectivity
				+ ", moneyPerHour=" + moneyPerHour + ", replicationFactor=" + replicationFactor + ", safetyBuffer="
				+ safetyBuffer + "]";
	}
	
}
