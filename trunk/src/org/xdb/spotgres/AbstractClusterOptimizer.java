package org.xdb.spotgres;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xdb.spotgres.pojos.ClusterConstraints;
import org.xdb.spotgres.pojos.ClusterConstraints.OPTIMIZERTYPE;
import org.xdb.spotgres.pojos.ClusterSetup;
import org.xdb.spotgres.pojos.NodePrice.PRICETYPE;

public abstract class AbstractClusterOptimizer {
	protected ClusterConstraints constraints;
	protected OPTIMIZERTYPE optimizerType;
	protected Collection<ClusterPriceHelper> availableNodeTypeHelpers;
	protected static Map<ClusterConstraints, List<ClusterSetup>> possibleSetups;
	
	protected static void addToPossibleSetups(ClusterConstraints constraints, ClusterSetup newSetup){
		boolean addEntry = true;
		List<ClusterSetup> setups = possibleSetups.get(constraints);
		if (setups==null){
			setups = new ArrayList<ClusterSetup>();
		}
		Iterator<ClusterSetup> setupIter = setups.iterator();
		while (setupIter.hasNext() && addEntry) {
			ClusterSetup clusterSetup = (ClusterSetup) setupIter.next();
			addEntry = !clusterSetup.isTheSameSetup(newSetup);
		}
		if (addEntry){
			setups.add(newSetup);
			possibleSetups.put(constraints,setups);
			if (setups.size() % 1000 == 0){
				System.out.println(setups.size());
			}
		} else {
		}
	}
	
	protected void addNode(ClusterSetup setup, int cusNeeded){
		for(ClusterPriceHelper helper:availableNodeTypeHelpers){
			try {
				ClusterSetup newSetup=setup.clone();
				newSetup.addNodes(helper.getTypeName(), 1);
				switch (optimizerType) {
				case MONEY:
					newSetup.setBidPrice(helper.getTypeName(), helper.getSpotPrice(), PRICETYPE.SPOT);
					break;

				case AVAILABILITY:
					newSetup.setBidPrice(helper.getTypeName(), helper.getSpotPricePerCUPerRam(), PRICETYPE.SPOT);					
					break;

				case PERFORMANCE:
					newSetup.setBidPrice(helper.getTypeName(), helper.getSpotPrice(), PRICETYPE.SPOT);
					break;

				default:
					newSetup.setBidPrice(helper.getTypeName(), helper.getSpotPrice(), PRICETYPE.SPOT);
					break;
				}
				int cusRemaining = cusNeeded - helper.getCuByRam();
				if (newSetup.getClusterPrice() < constraints.getMoneyPerHour()){
					if (cusRemaining > 0){
						addNode(newSetup, cusRemaining);
					} else {
						AbstractClusterOptimizer.addToPossibleSetups(constraints, newSetup);
					}
				} else {
				}
			} catch (CloneNotSupportedException e) {
			}
		}
	}
	
	protected List<ClusterSetup> generatePossibleSetups(){
		if (possibleSetups == null){
			possibleSetups = new HashMap<ClusterConstraints, List<ClusterSetup>>();
		}
		List<ClusterSetup> returnValue=possibleSetups.get(constraints);
		if (returnValue == null){
			addNode(new ClusterSetup(),constraints.getCuCountInclBuffer());
			returnValue=possibleSetups.get(constraints);
		}
		return returnValue;
	}
	
	public ClusterConstraints getConstraints() {
		return constraints;
	}
	public void setConstraints(ClusterConstraints constraints) {
		this.constraints = constraints;
	}
	public Collection<ClusterPriceHelper> getAvailableNodeTypeHelpers() {
		return availableNodeTypeHelpers;
	}
	public void setAvailableNodeTypeHelpers(Collection<ClusterPriceHelper> availableNodeTypeHelpers) {
		this.availableNodeTypeHelpers = availableNodeTypeHelpers;		
	}
	public AbstractClusterOptimizer(ClusterConstraints constraints, Collection<ClusterPriceHelper> availableNodeTypeHelpers) {
		super();
		this.constraints = constraints;
		this.availableNodeTypeHelpers=availableNodeTypeHelpers;		
	}
	
	public abstract ClusterSetup getClusterSetup();
	public abstract List<ClusterSetup> getClusterSetupList();
}
