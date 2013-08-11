package org.xdb.spotgres;

import java.util.Map;

import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodePrice.PRICETYPE;
import org.xdb.spotgres.pojos.NodeType;
import org.xdb.spotgres.pojos.PriceHelper;

public class ClusterPriceHelper {
	private NodeType nodeType;
	private NodePrice currentSpotPrice;
	private NodePrice onDemandPrice;
	private Map<Float, PriceHelper> precentages;
	
	public NodeType getNodeType() {
		return nodeType;
	}
	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
	public NodePrice getCurrentSpotPrice() {
		return currentSpotPrice;
	}
	public void setCurrentSpotPrice(NodePrice currentSpotPrice) {
		if (currentSpotPrice.getPriceType() != PRICETYPE.SPOT){
			throw new RuntimeException("Wrong pricetype! Expected "+PRICETYPE.SPOT+" but got "+onDemandPrice.getPriceType());
		}
		this.currentSpotPrice = currentSpotPrice;
	}
	public NodePrice getOnDemandPrice() {
		return onDemandPrice;
	}
	public void setOnDemandPrice(NodePrice onDemandPrice) {
		if (onDemandPrice.getPriceType() != PRICETYPE.ONDEMAND){
			throw new RuntimeException("Wrong pricetype! Expected "+PRICETYPE.ONDEMAND+" but got "+onDemandPrice.getPriceType());
		}
		this.onDemandPrice = onDemandPrice;
	}
	public Map<Float, PriceHelper> getPrecentages() {
		return precentages;
	}
	public void setPrecentages(Map<Float, PriceHelper> precentages) {
		this.precentages = precentages;
	}
	
	public float getOnDemandPricePerCU(){
		return onDemandPrice.getPrice() / nodeType.getCuCount();
	}

	public float getSpotPricePerCU(){
		return currentSpotPrice.getPrice() / nodeType.getCuCount();
	}

	public float getOnDemandPricePerCUPerRam(float ram){
		return onDemandPrice.getPrice() / nodeType.getCuByRam(ram);
	}

	public float getSpotPricePerCUPerRam(float ram){
		return currentSpotPrice.getPrice() / nodeType.getCuByRam(ram);
	}
}
