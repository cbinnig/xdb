package org.xdb.spotgres.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "ClusterConstraints")
public class ClusterConstraints {
	
	/**
	 * If a given constraint allows more then one possible cluster solution,
	 * the optimizer type tells what configuration to prefer:
	 * AVAILABILITY = select the highest possible availability with a given max price
	 * MONEY = with a given availability rate, find the cheapest configuration
	 * PERFORMANCE = with fixed min availability and max price, find the configuration with the most CUs
	 * @author svenlisting
	 *
	 */
	public enum OPTIMIZERTYPE {
		AVAILABILITY, MONEY, PERFORMANCE
	}

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
	
	@Column(name="optimizer")
	OPTIMIZERTYPE optimizer = OPTIMIZERTYPE.MONEY;
	
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
	
	public float getMoneyPerCu(){
		return moneyPerHour/cuCount;
	}
	
	public float getMoneyPerCuInclBuffer(){
		return moneyPerHour/getCuCountInclBuffer();
	}
	
	public OPTIMIZERTYPE getOptimizer() {
		return optimizer;
	}
	public void setOptimizer(OPTIMIZERTYPE optimizer) {
		this.optimizer = optimizer;
	}
	@Override
	public String toString() {
		return "ClusterConstraints [cuCount=" + cuCount + ", ramPerCu=" + ramPerCu + ", connectivity=" + connectivity
				+ ", moneyPerHour=" + moneyPerHour + ", replicationFactor=" + replicationFactor + ", safetyBuffer="
				+ safetyBuffer + " optimizer=" + optimizer +"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(connectivity);
		result = prime * result + cuCount;
		result = prime * result + Float.floatToIntBits(moneyPerHour);
		result = prime * result + ramPerCu;
		result = prime * result + safetyBuffer;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClusterConstraints other = (ClusterConstraints) obj;
		if (Float.floatToIntBits(connectivity) != Float.floatToIntBits(other.connectivity))
			return false;
		if (cuCount != other.cuCount)
			return false;
		if (Float.floatToIntBits(moneyPerHour) != Float.floatToIntBits(other.moneyPerHour))
			return false;
		if (ramPerCu != other.ramPerCu)
			return false;
		if (safetyBuffer != other.safetyBuffer)
			return false;
		return true;
	}

	
	
}
