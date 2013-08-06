package org.xdb.spotgres.pojos;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceHelper {
	private String nodeType;
	private String zone;
	private long active;
	private long inactive;
	private long totalTime;
	private float averageTemp;
	private float minPrice;
	private float maxPrice;

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public long getActive() {
		return active;
	}

	public void setActive(long active) {
		this.active = active;
	}

	public long getInactive() {
		return inactive;
	}

	public void setInactive(long inactive) {
		this.inactive = inactive;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public float getAverageTemp() {
		return averageTemp;
	}

	public void setAverageTemp(float averageTemp) {
		this.averageTemp = averageTemp;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public PriceHelper(String nodeType, String zone) {
		super();
		this.nodeType = nodeType;
		this.zone = zone;
		this.active = 0;
		this.inactive = 0;
		this.averageTemp = 0.0f;
		this.totalTime = 0;
		this.maxPrice = -1;
		this.minPrice = -1;

	}

	public void addNodePriceData(NodePrice nodePrice, float bidPrice) {
		long duration = nodePrice.getDuration();
		float price = nodePrice.getPrice();
		if (duration > 0) {
			if (nodePrice.getPrice() > bidPrice) {
				this.inactive += duration;
			} else {
				this.active += duration;
			}
			this.totalTime += duration;
			this.averageTemp += duration * price;
		}
		if (price > this.maxPrice || this.maxPrice == -1) {
			this.maxPrice = price;
		}
		if (price < this.minPrice || this.minPrice == -1) {
			this.minPrice = price;
		}
	}

	@Override
	public String toString() {
		NumberFormat currenyFormat = NumberFormat.getCurrencyInstance(Locale.US);
		currenyFormat.setMaximumFractionDigits(4);
		NumberFormat percentageFormat = NumberFormat.getPercentInstance(Locale.US);
		percentageFormat.setMaximumFractionDigits(3);
		StringBuilder returnValue = new StringBuilder();
		returnValue.append(nodeType);
		returnValue.append(" - Active: ");
		returnValue.append(active);
		returnValue.append(" +++ Inactive: ");
		returnValue.append(inactive);
		returnValue.append(" +++ Percentage: ");
		long total = active + inactive;
		float percentage = active * 1.0f / total;
		returnValue.append(percentageFormat.format(percentage));
		returnValue.append(" +++ Average Price:");
		float avgPrice = averageTemp / totalTime;
		returnValue.append(currenyFormat.format(avgPrice));
		returnValue.append("/h");
		returnValue.append(" +++ min Price: ");
		returnValue.append(currenyFormat.format(minPrice));
		returnValue.append("/h +++ max Price: ");
		returnValue.append(currenyFormat.format(maxPrice));
		returnValue.append("/h");

		return returnValue.toString();
	}

}
