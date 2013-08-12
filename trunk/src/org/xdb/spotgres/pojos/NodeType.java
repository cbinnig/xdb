package org.xdb.spotgres.pojos;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * INSERT INTO `nodetype` (`typeId`, `cpuCount`, `cuCount`, `hdd`, `ram`, `typeName`) VALUES
	(1, 2, 4, 840, 7680, 'm1.large'),
	(2, 4, 8, 1680, 15360, 'm1.xlarge'),
	(3, 4, 13, -1, 15360, 'm3.xlarge'),
	(4, 8, 26, -1, 30720, 'm3.2xlarge'),
	(5, 4, 13, 420, 35020, 'm2.2xlarge'),
	(6, 8, 26, 1680, 70041, 'm2.4xlarge'),
	(7, 8, 20, 1680, 7168, 'c1.xlarge');
 *
 */

@Entity
@Table(name = "NodeType")
public class NodeType {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	int typeId;
	
	@Column(name = "typeName",unique=true)
	String typeName;

	@Column(name = "cpuCount")
	int cpuCount;

	@Column(name = "cuCount")
	int cuCount;

	@Column(name = "ram")
	int ram;

	@Column(name = "hdd")
	int hdd;

	@Transient
	private Collection<NodePrice> currentSpotPrice;
	
	@Transient
	private Collection<NodePrice> currentOnDemandPrice;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(int cpuCount) {
		this.cpuCount = cpuCount;
	}

	public int getRam() {
		return ram;
	}

	/**
	 * RAM is set in MB
	 * @param ram
	 */
	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getHdd() {
		return hdd;
	}

	public void setHdd(int hdd) {
		this.hdd = hdd;
	}

	public int getTypeId() {
		return typeId;
	}

	public int getCuCount() {
		return cuCount;
	}

	public void setCuCount(int cuCount) {
		this.cuCount = cuCount;
	}

	public float getRamPerCu(){
		return 1.0f * this.ram / this.cuCount;
	}

	/**
	 * This method calculates the amount of computing units this node type 
	 * provides based on the RAM per CU constraint provided by the customer
	 * @param ramPerCu
	 * @return
	 */
	public int getCuByRam(int ramPerCu){
		int cuBasedOnRam = Double.valueOf(Math.floor(this.ram / ramPerCu)).intValue(); 
		return cuBasedOnRam>this.cuCount?this.cuCount:cuBasedOnRam;
	}

	@Override
	public String toString() {
		return "NodeType [typeId=" + typeId + ", typeName=" + typeName + ", cpuCount=" + cpuCount + ", cuCount="
				+ cuCount + ", ram=" + ram + ", hdd=" + hdd + "]";
	}
	
	
}
