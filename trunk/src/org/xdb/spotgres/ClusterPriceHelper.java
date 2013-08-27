package org.xdb.spotgres;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.xdb.spotgres.pojos.ClusterConstraints;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodePrice.PRICETYPE;
import org.xdb.spotgres.pojos.NodeType;
import org.xdb.spotgres.pojos.SpotPriceHelper;

public class ClusterPriceHelper {
	private NodeType nodeType;
	private NodePrice currentSpotPrice;
	private NodePrice onDemandPrice;
	private Map<Float, SpotPriceHelper> percentages;
	private Collection<NodePrice> spotPriceHistory;
	private ClusterConstraints constraints;

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
		if (currentSpotPrice.getPriceType() != PRICETYPE.SPOT) {
			throw new RuntimeException("Wrong pricetype! Expected " + PRICETYPE.SPOT + " but got "
					+ onDemandPrice.getPriceType());
		}
		this.currentSpotPrice = currentSpotPrice;
	}

	public NodePrice getOnDemandPrice() {
		return onDemandPrice;
	}

	public void setOnDemandPrice(NodePrice onDemandPrice) {
		if (onDemandPrice.getPriceType() != PRICETYPE.ONDEMAND) {
			throw new RuntimeException("Wrong pricetype! Expected " + PRICETYPE.ONDEMAND + " but got "
					+ onDemandPrice.getPriceType());
		}
		this.onDemandPrice = onDemandPrice;
	}

	public Map<Float, SpotPriceHelper> getPrecentages() {
		return percentages;
	}

	public void setPrecentages(Map<Float, SpotPriceHelper> precentages) {
		this.percentages = precentages;
	}
	
	public void clearPercentages(){
		percentages = null;
	}
	
	public void calcPercentageNodePrice(float bidPrice){
		if (spotPriceHistory != null){
			SpotPriceHelper priceHelper = new SpotPriceHelper(getTypeName(),"*");
			for (NodePrice currentPrice:spotPriceHistory){
				priceHelper.addNodePriceData(currentPrice, bidPrice);
			}
			if (percentages == null){
				percentages = new HashMap<Float, SpotPriceHelper>();
			}
			percentages.put(bidPrice, priceHelper);
		}
	}

	public void calcPercentageCUPrice(float bidPrice){
		if (spotPriceHistory != null){
			float nodePrice = bidPrice * this.getCuByRam();
			SpotPriceHelper priceHelper = new SpotPriceHelper(getTypeName(),"*");
			for (NodePrice currentPrice:spotPriceHistory){
				priceHelper.addNodePriceData(currentPrice, nodePrice);
			}
			if (percentages == null){
				percentages = new HashMap<Float, SpotPriceHelper>();
			}
			percentages.put(bidPrice, priceHelper);
		}
	}


	public float getOnDemandPricePerCU() {
		return onDemandPrice.getPrice() / nodeType.getCuCount();
	}

	public float getSpotPrice() {
		return currentSpotPrice.getPrice();
	}

	public float getSpotPricePerCU() {
		return currentSpotPrice.getPrice() / nodeType.getCuCount();
	}

	public float getOnDemandPricePerCUPerRam() {
		return onDemandPrice.getPrice() / nodeType.getCuByRam(constraints.getRamPerCu());
	}
	
	public float getOnDemandPricePerCUPerRam(int ram) {
		return onDemandPrice.getPrice() / nodeType.getCuByRam(ram);
	}

	public float getSpotPricePerCUPerRam() {
		return currentSpotPrice.getPrice() / nodeType.getCuByRam(constraints.getRamPerCu());
	}

	public float getSpotPricePerCUPerRam(int ram) {
		return currentSpotPrice.getPrice() / nodeType.getCuByRam(ram);
	}

	public ClusterConstraints getConstraints() {
		return constraints;
	}

	public void setConstraints(ClusterConstraints constraints) {
		this.constraints = constraints;
	}

	@Override
	public String toString() {
		StringBuilder returnValue = new StringBuilder();
		returnValue.append("Nodetype: ").append(getTypeName()).append("\n");
		returnValue.append("CUs: ").append(nodeType.getCuCount()).append("\n");
		returnValue.append("RAM: ").append(nodeType.getRam()).append("\n");
		returnValue.append("CUs with ").append(constraints.getRamPerCu())
				.append("MB RAM: " + nodeType.getCuByRam(constraints.getRamPerCu())).append("\n");
		returnValue.append("Current Spot Price: ").append(currentSpotPrice.getPrice()).append("\n");
		returnValue.append("Current Spot Price per CU: ").append(getSpotPricePerCU()).append("\n");
		returnValue.append("Current Spot Price per CU&RAM: ")
				.append(getSpotPricePerCUPerRam(constraints.getRamPerCu())).append("\n");
		returnValue.append("Current OnDemand Price: ").append(onDemandPrice.getPrice()).append("\n");
		returnValue.append("Current OnDemand Price per CU: ").append(getOnDemandPricePerCU()).append("\n");
		returnValue.append(
				"Current OnDemand Price per CU&RAM: " + getOnDemandPricePerCUPerRam(constraints.getRamPerCu())).append(
				"\n");
		int nodeCount = (int) Math.ceil(1d * constraints.getCuCount() / nodeType.getCuByRam(constraints.getRamPerCu()));
		returnValue.append("Nodes of this type neded to match CU/Ram contraints: ").append(nodeCount).append("\n");
		returnValue.append("Total Price per Hour using Spot Instances: ")
				.append(nodeCount * currentSpotPrice.getPrice()).append("\n");
		returnValue.append("Total Price per Hour using OnDemand Instances: ")
				.append(nodeCount * onDemandPrice.getPrice()).append("\n");
		returnValue.append("--------------").append("\n");
		return returnValue.toString();
	}

	public String getTypeName() {
		String typeName = "Type Not Set!";
		if (nodeType != null) {
			typeName = nodeType.getTypeName();
		}
		return typeName;
	}

	public int getCuByRam() {
		return nodeType.getCuByRam(constraints.getRamPerCu());
	}

	public Collection<NodePrice> getSpotPriceHistory() {
		return spotPriceHistory;
	}

	public void setSpotPriceHistory(Collection<NodePrice> spotPriceHistory) {
		this.spotPriceHistory = spotPriceHistory;
	}
	
	
}
