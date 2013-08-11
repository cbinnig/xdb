package org.xdb.spotgres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xdb.logging.XDBLog;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodeType;

public class ClusterCalculationTools {
	private Session session;
	private SessionFactory sessionFactory;
	protected Logger logger;

	public ClusterCalculationTools() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
		logger = XDBLog.getLogger(this.getClass().getName());
	}

	@SuppressWarnings("unchecked")
	public List<NodeType> loadNodeTypes(){
		logger.log(Level.INFO, "Calculating availability per NodeType & Zone");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<NodeType> availableNodeTypes = session.createQuery("from NodeType").list();
		tx.commit();
		return availableNodeTypes;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, NodePrice> loadOnDemandPrices(){
		logger.log(Level.INFO, "Loading onDemand prices");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<NodePrice> nodePriceList =session.createQuery("from NodePrice where priceType="+NodePrice.PRICETYPE.ONDEMAND.ordinal()).list();
		tx.commit();
		Map<String, NodePrice> returnValue = new HashMap<String, NodePrice>();
		for (NodePrice nodePrice : nodePriceList) {
			returnValue.put(nodePrice.getNodeType(),nodePrice);
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Map<String, NodePrice> loadCurrentSpotPrices(){
		logger.log(Level.INFO, "Loading current spot prices");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<NodePrice> nodePriceList = session.createQuery("from NodePrice where duration=-1 and priceType="+NodePrice.PRICETYPE.SPOT.ordinal()).list();
		tx.commit();
		Map<String, NodePrice> returnValue = new HashMap<String, NodePrice>();
		for (NodePrice nodePrice : nodePriceList) {
			NodePrice oldPrice = returnValue.get(nodePrice.getNodeType());
			if  (oldPrice==null || oldPrice.getPrice()>nodePrice.getPrice()){
				returnValue.put(nodePrice.getNodeType(),nodePrice);
			}
		}
		return returnValue;
	}
}
