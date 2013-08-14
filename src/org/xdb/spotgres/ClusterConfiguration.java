package org.xdb.spotgres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.xdb.logging.XDBLog;
import org.xdb.spotgres.pojos.ClusterConstraints;
import org.xdb.spotgres.pojos.ClusterSetup;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodeType;

@SuppressWarnings("unused")
public class ClusterConfiguration {
	private Session session;
	private SessionFactory sessionFactory;
	protected Logger logger;

	private ClusterConstraints constraints;
	private ClusterCalculationTools toolkit;
	private Map<String, ClusterPriceHelper> helpers;

	private List<NodeType> availableNodeTypes;
	private Map<String, NodePrice> currentSpotPrices;
	private Map<String, NodePrice> onDemandPrices;

	private ArrayList<String> pricelistRamPerCUAscending;
	private ArrayList<String> pricelistPerNodeAscending;

	private Map<Float, ClusterSetup> setupResults;

	private void initHibernate() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
	}

	public void setUp() throws IOException {
		initHibernate();
		toolkit = new ClusterCalculationTools();
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
		logger = XDBLog.getLogger(this.getClass().getName());
	}

	public ClusterConstraints getConstraint() {
		return constraints;
	}

	public void setConstraint(ClusterConstraints constraint) {
		this.constraints = constraint;
	}

	private void initiateHelpers() {
		helpers = new HashMap<String, ClusterPriceHelper>();
		for (NodeType nodeType : availableNodeTypes) {
			ClusterPriceHelper helper = new ClusterPriceHelper();
			helper.setNodeType(nodeType);
			helper.setOnDemandPrice(onDemandPrices.get(nodeType.getTypeName()));
			helper.setCurrentSpotPrice(currentSpotPrices.get(nodeType.getTypeName()));
			helper.setConstraints(constraints);
			helpers.put(nodeType.getTypeName(), helper);
		}
	}

	private static float calcClusterPrice(ClusterSetup setup, Map<String, ClusterPriceHelper> helpers) {
		float returnValue = 0;
		for (String nodeTypeName : setup.getNodes().keySet()) {
			returnValue += setup.getNodes().get(nodeTypeName)
					* helpers.get(nodeTypeName).getCurrentSpotPrice().getPrice();
		}
		return returnValue;
	}

	private ClusterSetup buildCheapestSetup(String startingNodeType) {
		if (pricelistRamPerCUAscending == null) {
			createPricelistsAscending();
		}
		ClusterSetup returnValue = new ClusterSetup();
		ClusterPriceHelper startingNode = helpers.get(startingNodeType);
		int cusRemaining = constraints.getCuCountInclBuffer();
		if (startingNode != null) {
			cusRemaining = addNodeToClusterAutoCalcAmount(startingNode, returnValue, cusRemaining);
		}
		Iterator<String> typeIter = pricelistRamPerCUAscending.iterator();
		while (typeIter.hasNext() && cusRemaining > 0) {
			String typeName = (String) typeIter.next();
			ClusterPriceHelper helper = helpers.get(typeName);
			cusRemaining = addNodeToClusterAutoCalcAmount(helper, returnValue, cusRemaining);
		}

		typeIter = pricelistPerNodeAscending.iterator();
		while (typeIter.hasNext() && cusRemaining > 0) {
			String typeName = (String) typeIter.next();
			ClusterPriceHelper helper = helpers.get(typeName);
			if (cusRemaining < helper.getCuByRam()) {
				returnValue.addNodes(helper.getTypeName(), 1);
				cusRemaining -= helper.getCuByRam();
			}
		}

		return returnValue;
	}

	private String printClusterSetupWithPrice(ClusterSetup setup) {
		StringBuilder returnValue = new StringBuilder();
		float totalSum = 0;
		int totalCUs = 0;
		returnValue.append("Cluster Setup\n");
		returnValue.append("-------------\n");
		for (Entry<String, Integer> nodeType : setup.getNodes().entrySet()) {
			int nodeAmount = nodeType.getValue();
			if (nodeAmount > 0) {
				ClusterPriceHelper helper = helpers.get(nodeType.getKey());
				float rowPrice = nodeAmount * helper.getCurrentSpotPrice().getPrice();
				totalSum += rowPrice;
				totalCUs += nodeAmount * helper.getCuByRam();

				returnValue.append(nodeType.getValue().toString());
				returnValue.append("x ");
				returnValue.append(nodeType.getKey());
				returnValue.append(" --- Price: ");
				returnValue.append(helper.getCurrentSpotPrice().getPrice());
				returnValue.append("$ (each) / ");
				returnValue.append(rowPrice);
				returnValue.append("$ (sum)");
				returnValue.append("\n");

			}
		}
		returnValue.append("+ + + +\n");
		returnValue.append("Total CUs: ").append(totalCUs).append("\n");
		returnValue.append("Total Price: ").append(totalSum).append("$/h\n");
		return returnValue.toString();
	}

	private int addNodeToClusterAutoCalcAmount(ClusterPriceHelper clp, ClusterSetup clusterSetup, int cusRemaining) {
		int nodeCount = cusRemaining / clp.getCuByRam();
		int cuCovered = nodeCount * clp.getCuByRam();
		clusterSetup.addNodes(clp.getTypeName(), nodeCount);
		return cusRemaining - cuCovered;
	}

	private void createPricelistsAscending() {
		if (helpers != null && !helpers.isEmpty()) {
			pricelistRamPerCUAscending = new ArrayList<String>();
			pricelistPerNodeAscending = new ArrayList<String>();
			ArrayList<ClusterPriceHelper> helperList = new ArrayList<ClusterPriceHelper>(helpers.values());
			Collections.sort(helperList, new ClusterCalculationTools.SpotPricePerCUPerRamComparator());
			for (ClusterPriceHelper clusterPriceHelper : helperList) {
				pricelistRamPerCUAscending.add(clusterPriceHelper.getTypeName());
			}
			Collections.sort(helperList, new ClusterCalculationTools.SpotPriceComparator());
			for (ClusterPriceHelper clusterPriceHelper : helperList) {
				pricelistPerNodeAscending.add(clusterPriceHelper.getTypeName());
			}
		}
	}

	private void execute() throws IOException {
		setUp();
		System.out.println("Constraints:");
		System.out.println(constraints.toString());
		availableNodeTypes = toolkit.loadNodeTypes();
		currentSpotPrices = toolkit.loadCurrentSpotPrices();
		onDemandPrices = toolkit.loadOnDemandPrices();
		initiateHelpers();
		ArrayList<ClusterPriceHelper> helperList = new ArrayList<ClusterPriceHelper>(helpers.values());
		Collections.sort(helperList, new ClusterCalculationTools.SpotPricePerCUPerRamComparator());
		for (ClusterPriceHelper helper : helperList) {
			System.out.println(helper.toString());
		}
		setupResults = new HashMap<Float, ClusterSetup>();
		for (NodeType nodeType : availableNodeTypes) {
			ClusterSetup setup = buildCheapestSetup(nodeType.getTypeName());
			setupResults.put(calcClusterPrice(setup, helpers), setup);
		}
		ArrayList<Float> setupPrices = new ArrayList<Float>(setupResults.keySet());
		Collections.sort(setupPrices);
		for (Float price : setupPrices) {
			System.out.println(printClusterSetupWithPrice(setupResults.get(price)));
			System.out.println();
		}
	}

	public static void main(String[] args) {
		ClusterConfiguration conf = new ClusterConfiguration();
		ClusterConstraints constraint = new ClusterConstraints();
		constraint.setConnectivity(80f);
		constraint.setCuCount(100);
		constraint.setMoneyPerHour(6);
		constraint.setRamPerCu(1024);
		constraint.setSafetyBuffer(10);
		conf.setConstraint(constraint);
		try {
			conf.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
