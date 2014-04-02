package org.xdb.spotgres;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.spotgres.pojos.ClusterConstraints;
import org.xdb.spotgres.pojos.ClusterSetup;
import org.xdb.spotgres.pojos.ClusterSetup.NodeMap.NodeEntry;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodeType;
import org.xdb.spotgres.pojos.SpotPriceHelper;

public class ClusterCalculationTools {
	public static class SpotPriceComparator implements Comparator<ClusterPriceHelper> {
		public int compare(ClusterPriceHelper a, ClusterPriceHelper b) {
			return Float.valueOf(a.getCurrentSpotPrice().getPrice()).compareTo(
					b.getCurrentSpotPrice().getPrice());
		}
	}
	
	public static class SpotPricePerCUPerRamComparator implements Comparator<ClusterPriceHelper> {
		public int compare(ClusterPriceHelper a, ClusterPriceHelper b) {
			return Float.valueOf(a.getOnDemandPricePerCUPerRam()).compareTo(
					b.getOnDemandPricePerCUPerRam());
		}
	}
	
	public static class ClusterPriceComparator implements Comparator<ClusterSetup> {
		public int compare(ClusterSetup a, ClusterSetup b) {
			return Float.valueOf(a.getClusterPrice()).compareTo(
					b.getClusterPrice());
		}
	}
	
	public static class ClusterAvailabilityComparator implements Comparator<ClusterSetup> {
		public int compare(ClusterSetup a, ClusterSetup b) {
			return Float.valueOf(a.getAvailability()).compareTo(
					b.getAvailability());
		}
	}
	
	
	private Session session;
	private SessionFactory sessionFactory;
	private List<NodeType> availableNodeTypes;
	private Map<String, NodePrice> currentSpotPrices;
	private Map<String, NodePrice> onDemandPrices;
	private ClusterConstraints constraints;
	
	protected Logger logger;
	private Map<String, ClusterPriceHelper> helpers;
	
	public ClusterCalculationTools(ClusterConstraints constraints) {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
		logger = XDBLog.getLogger(EnumXDBComponents.SPOTGRES);
		availableNodeTypes = loadNodeTypes();
		currentSpotPrices = loadCurrentSpotPrices();
		onDemandPrices = loadOnDemandPrices();
		this.constraints=constraints;
		initiateHelpers();
	}

	@SuppressWarnings("unchecked")
	public List<NodeType> loadNodeTypes() {
		logger.log(Level.INFO, "Calculating availability per NodeType & Zone");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<NodeType> availableNodeTypes = session.createQuery("from NodeType").list();
		tx.commit();
		return availableNodeTypes;
	}

	@SuppressWarnings("unchecked")
	public Map<String, NodePrice> loadOnDemandPrices() {
		logger.log(Level.INFO, "Loading onDemand prices");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<NodePrice> nodePriceList = session.createQuery(
				"from NodePrice where priceType=" + NodePrice.PRICETYPE.ONDEMAND.ordinal()).list();
		tx.commit();
		Map<String, NodePrice> returnValue = new HashMap<String, NodePrice>();
		for (NodePrice nodePrice : nodePriceList) {
			returnValue.put(nodePrice.getNodeType(), nodePrice);
		}
		return returnValue;
	}
	
	public void updateHelpersWithSpotPriceHistory(Map<String, Collection<NodePrice>> spotPriceHistory){
		if (helpers != null && spotPriceHistory != null){
			for (String nodeType:spotPriceHistory.keySet()){
				ClusterPriceHelper helper = helpers.get(nodeType);
				if (helper != null && helper.getSpotPriceHistory() == null){
					helper.setSpotPriceHistory(spotPriceHistory.get(nodeType));
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, NodePrice> loadCurrentSpotPrices() {
		logger.log(Level.INFO, "Loading current spot prices");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<NodePrice> nodePriceList = session.createQuery(
				"from NodePrice where duration=-1 and priceType=" + NodePrice.PRICETYPE.SPOT.ordinal()).list();
		tx.commit();
		Map<String, NodePrice> returnValue = new HashMap<String, NodePrice>();
		for (NodePrice nodePrice : nodePriceList) {
			NodePrice oldPrice = returnValue.get(nodePrice.getNodeType());
			if (oldPrice == null || oldPrice.getPrice() > nodePrice.getPrice()) {
				returnValue.put(nodePrice.getNodeType(), nodePrice);
			}
		}
		return returnValue;
	}

	public Map<String, Collection<NodePrice>> loadSpotPriceHistory() {
		return loadSpotPriceHistory(new ArrayList<String>());
	}
	
	public Map<String, Collection<NodePrice>> loadSpotPriceHistory(ClusterSetup clusterSetup) {
		return loadSpotPriceHistory(clusterSetup.getNodes().keySet());
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
	
	@SuppressWarnings("unchecked")
	public Map<String, Collection<NodePrice>> loadSpotPriceHistory(Collection<String> nodeTypes) {
		logger.log(Level.INFO, "Loading spot price History");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		String queryString = createSpotPriceHistoryQueryString(nodeTypes);
		Query spotPriceQuery =  session.createQuery(queryString.toString());
		List<NodePrice> nodePriceList =spotPriceQuery.list();
		tx.commit();
		
		Map<String, Collection<NodePrice>> returnValue = new HashMap<String, Collection<NodePrice>>();		
		for (NodePrice nodePrice : nodePriceList) {
			Collection<NodePrice> spotPriceList = returnValue.get(nodePrice.getNodeType());
			if (spotPriceList == null) {
				spotPriceList = new ArrayList<NodePrice>();
				returnValue.put(nodePrice.getNodeType(), spotPriceList);
			}
			spotPriceList.add(nodePrice);
		}
		return returnValue;
	}

	private String createSpotPriceHistoryQueryString(Collection<String> nodeTypes) {
		StringBuilder returnValue = new StringBuilder();
		returnValue.append("from NodePrice where duration<>-1 and priceType=");
		returnValue.append(NodePrice.PRICETYPE.SPOT.ordinal());
		if (nodeTypes != null && nodeTypes.size() > 0){
			returnValue.append(" and nodeType in ('");
			Iterator<String> nodeTypeIter = nodeTypes.iterator();
			while (nodeTypeIter.hasNext()) {
				String nodeType = (String) nodeTypeIter.next();
				returnValue.append(nodeType);
				if (nodeTypeIter.hasNext()) {
					returnValue.append("', '");
				}
			}
			returnValue.append("')");
		}
		return returnValue.toString();
	}
	

	public float calcAvailablityCurrentSpotPrice(ClusterSetup clusterSetup){
		updateHelpersWithSpotPriceHistory(loadSpotPriceHistory(clusterSetup));
		for (String nodeType:clusterSetup.getNodes().keySet()){
			ClusterPriceHelper helper = helpers.get(nodeType);
			helper.calcPercentageNodePrice(helper.getSpotPrice());
		}
		int cuCount = 0;
		float probability=0;
		for (NodeEntry clusterPiece:clusterSetup.getNodes().values()){
			ClusterPriceHelper helper = helpers.get(clusterPiece.getType());
			SpotPriceHelper sph = helper.getPrecentages().get(helper.getSpotPrice());
			probability += sph.getAvailability() * helper.getCuByRam() * clusterPiece.getCount();
			cuCount += helper.getCuByRam() * clusterPiece.getCount();
		}
		return probability/cuCount;
	}

	public float calcAvailablityCUPrice(ClusterSetup clusterSetup, float cuPrice){
		updateHelpersWithSpotPriceHistory(loadSpotPriceHistory(clusterSetup));
		for (String nodeType:clusterSetup.getNodes().keySet()){
			ClusterPriceHelper helper = helpers.get(nodeType);
			helper.calcPercentageCUPrice(cuPrice);
		}
		int cuCount = 0;
		float probability=0;
		for (NodeEntry clusterPiece:clusterSetup.getNodes().values()){
			ClusterPriceHelper helper = helpers.get(clusterPiece.getType());
			SpotPriceHelper sph = helper.getPrecentages().get(cuPrice * helper.getCuByRam(constraints.getRamPerCu()));
			probability += sph.getAvailability() * helper.getCuByRam() * clusterPiece.getCount();
			cuCount += helper.getCuByRam() * clusterPiece.getCount();
		}
		return probability/cuCount;
	}	
	
	public ClusterPriceHelper getHelper(String nodeType){
		return helpers.get(nodeType);
	}
	
	public Map<String, ClusterPriceHelper> getHelpers(){
		return helpers;
	}
	
//	public Map<Float,Float> calcAvailablity(ClusterSetup clusterSetup){
//		updateHelpersWithSpotPriceHistory(loadSpotPriceHistory(clusterSetup));
//		Map<Float, Float> returnValue = new HashMap<Float, Float>();
//		for (String nodeType:clusterSetup.getNodes().keySet()){
//			ClusterPriceHelper helper = helpers.get(nodeType);
//			helper.clearPercentages();
//			float currentPrice = minPrice;
//			while (currentPrice<=maxPrice && priceSteps > 0) {
//				helper.calcPercentageCUPrice(currentPrice);
//				currentPrice += priceSteps;
//			}
//		}
//		float currentPrice = minPrice;
//		while (currentPrice<=maxPrice && priceSteps > 0) {
//			int cuCount = 0;
//			float probability=0;
//			for (NodeEntry clusterPiece:clusterSetup.getNodes().values()){
//				ClusterPriceHelper helper = helpers.get(clusterPiece.getType());
//				SpotPriceHelper sph = helper.getPrecentages().get(currentPrice);
//				probability += sph.getAvailability() * helper.getCuByRam() * clusterPiece.getCount();
//				cuCount += helper.getCuByRam() * clusterPiece.getCount();
//			}
//			returnValue.put(currentPrice, probability/cuCount);
//			currentPrice += priceSteps;
//		}
//		return returnValue;
//	}
}
