package org.xdb.spotgres.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="NodePrice")
public class NodePrice {
	@Id
	int priceID;
	
	@Column(name="priceTime")
	Date priceTime;
	
	@Column(name="price")
	float price;
	
	public enum PRICETYPE{SPOT, ONDEMAND, RESERVED}

	@Column(name="priceType")
	PRICETYPE priceType;
	
	
}
