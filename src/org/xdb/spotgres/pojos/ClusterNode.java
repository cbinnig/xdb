package org.xdb.spotgres.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ClusterNode")
public class ClusterNode {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="clusterNodeId")
	int clusterNodeId;
	
	@Column(name = "nodeType")	
	String nodeType;
	
	@Column(name  = "nodeIdentifier")
	String nodeIdentifer;
	
	@Column(name = "validUntil")
	Date validUntil;
	   
	@Column(name = "price")
	float price;
	
	@ManyToOne
	@JoinColumn(name="constraintId")
	ClusterConstraints constraints;


	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeIdentifer() {
		return nodeIdentifer;
	}

	public void setNodeIdentifer(String nodeIdentifer) {
		this.nodeIdentifer = nodeIdentifer;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getClusterNodeId() {
		return clusterNodeId;
	}
	
	
}
