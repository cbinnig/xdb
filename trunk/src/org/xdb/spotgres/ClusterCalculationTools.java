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
import org.xdb.logging.XDBLog;
import org.xdb.spotgres.pojos.ClusterSetup;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodeType;

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
	
	private Session session;
	private SessionFactory sessionFactory;
	protected Logger logger;

	public ClusterCalculationTools() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
		logger = XDBLog.getLogger(this.getClass().getName());
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
}
