package org.xdb.spotgres.optimizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.xdb.spotgres.AbstractClusterOptimizer;
import org.xdb.spotgres.ClusterCalculationTools;
import org.xdb.spotgres.ClusterPriceHelper;
import org.xdb.spotgres.pojos.ClusterConstraints;
import org.xdb.spotgres.pojos.ClusterConstraints.OPTIMIZERTYPE;
import org.xdb.spotgres.pojos.ClusterSetup;

public class MoneyClusterOptimizer extends AbstractClusterOptimizer {

	public MoneyClusterOptimizer(ClusterConstraints constraints, Collection<ClusterPriceHelper> availableNodeTypeHelpers) {
		super(constraints, availableNodeTypeHelpers);
		this.optimizerType=OPTIMIZERTYPE.MONEY;
	}

	@Override
	public ClusterSetup getClusterSetup() {
		return getClusterSetupList().size()>0?getClusterSetupList().get(0):null;
	}

	@Override
	public List<ClusterSetup> getClusterSetupList() {
		List<ClusterSetup> setups = generatePossibleSetups();
		Collections.sort(setups, new ClusterCalculationTools.ClusterPriceComparator());
		
		ArrayList<ClusterSetup> returnValue=new ArrayList<ClusterSetup>();
		float cheapestPrice = setups.get(0).getClusterPrice();
		Iterator<ClusterSetup> clusterIter = setups.iterator();
		while (clusterIter.hasNext()) {
			ClusterSetup clusterSetup = (ClusterSetup) clusterIter.next();
			if (clusterSetup.getClusterPrice() == cheapestPrice) {
				returnValue.add(clusterSetup);
			} else {
				break;
			}
			
		}
		return returnValue;
	}
	
	

}
