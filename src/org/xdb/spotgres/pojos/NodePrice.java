package org.xdb.spotgres.pojos;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.amazonaws.services.ec2.model.SpotPrice;

@Entity(name = "NodePrice")
public class NodePrice {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	int priceID;

	@Column(name = "priceTime")
	Timestamp priceTime;

	@Column(name = "price")
	float price;

	public enum PRICETYPE {
		SPOT, ONDEMAND, RESERVED
	}

	@Column(name = "priceType")
	PRICETYPE priceType;

	@Column(name = "nodeType")
	String nodeType;

	@Column(name = "clusterZone")
	String clusterZone;

	@Column(name = "duration")
	long duration;

	public Timestamp getPriceTime() {
		return priceTime;
	}

	public void setPriceTime(Timestamp priceTime) {
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

	public String getClusterZone() {
		return clusterZone;
	}

	public void setClusterZone(String clusterZone) {
		this.clusterZone = clusterZone;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public NodePrice(SpotPrice spotPrice) {
		super();
		this.priceTime = new Timestamp(spotPrice.getTimestamp().getTime());
		this.priceType = PRICETYPE.SPOT;
		this.price = Float.valueOf(spotPrice.getSpotPrice());
		this.nodeType = spotPrice.getInstanceType();
		this.clusterZone = spotPrice.getAvailabilityZone();
		this.duration = -1;
	}

	public NodePrice(Timestamp priceTime, float price, PRICETYPE priceType, String nodeType, String clusterZone,
			long duration) {
		super();
		this.priceTime = priceTime;
		this.price = price;
		this.priceType = priceType;
		this.nodeType = nodeType;
		this.clusterZone = clusterZone;
		this.duration = duration;
	}

	public NodePrice() {
		super();
	}

}
