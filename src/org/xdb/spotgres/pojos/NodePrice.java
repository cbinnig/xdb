package org.xdb.spotgres.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.amazonaws.services.ec2.model.SpotPrice;

@Entity(name="NodePrice")
public class NodePrice {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	int priceID;
	
	@Column(name="priceTime")
	Date priceTime;
	
	@Column(name="price")
	float price;
	
	public enum PRICETYPE{SPOT, ONDEMAND, RESERVED}

	@Column(name="priceType")
	PRICETYPE priceType;

	@Column(name="nodeType")
	String nodeType;
	
	public Date getPriceTime() {
		return priceTime;
	}

	public void setPriceTime(Date priceTime) {
		this.priceTime = priceTime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public PRICETYPE getPriceType() {
		return priceType;
	}

	public void setPriceType(PRICETYPE priceType) {
		this.priceType = priceType;
	}

	public int getPriceID() {
		return priceID;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public NodePrice(SpotPrice spotPrice) {
		super();
		this.priceTime = new Date(spotPrice.getTimestamp().getTime());
		this.priceType=PRICETYPE.SPOT;
		this.price=Float.valueOf(spotPrice.getSpotPrice());
		this.nodeType=spotPrice.getInstanceType();
	}
	
	
}
