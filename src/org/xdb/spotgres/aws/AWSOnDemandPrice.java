package org.xdb.spotgres.aws;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xdb.logging.XDBLog;
import org.xdb.spotgres.HibernateUtil;
import org.xdb.spotgres.aws.ondemand.AWSOnDemandPriceRoot;
import org.xdb.spotgres.aws.ondemand.AWSOnDemandPriceRoot.Config.Region;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodePrice.PRICETYPE;

public class AWSOnDemandPrice {
	private static String AWSOnDemandPriceList = "http://aws.amazon.com/ec2/pricing/pricing-on-demand-instances.json";
	private AWSOnDemandPriceRoot root;
	private Session session;
	private SessionFactory sessionFactory;
	
	protected Logger logger;
		
	private void initHibernate() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
		logger = XDBLog.getLogger(this.getClass().getName());
	}		
	
	private static AWSOnDemandPriceRoot loadOnDemandPrices(String url) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		AWSOnDemandPriceRoot root = mapper.readValue(new URL(url),AWSOnDemandPriceRoot.class);
		return root;
	}
	
	private List<NodePrice> filterAndCreateNodePrices(String regionName, Collection<String> instanceTypes){
		logger.log(Level.INFO, "Filtering available prices by datacenter and node types");
		List<NodePrice> returnValue = new ArrayList<NodePrice>();
		Region region = root.getConfig().getRegionByName(regionName);
		for (String instanceType : instanceTypes) {
			float instancePrice = region.getInstanceTypeByName(instanceType);
			NodePrice price = new NodePrice();
			price.setPrice(instancePrice);
			price.setNodeType(instanceType);
			price.setPriceType(PRICETYPE.ONDEMAND);
			price.setPriceTime(new Timestamp(new Date().getTime()));
			returnValue.add(price);
		}
		return returnValue;
	}
	
	public void execute() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		initHibernate();
		logger.log(Level.INFO, "Loading OnDemand JSON file from Amazon and parsing it");
		root = AWSOnDemandPrice.loadOnDemandPrices(AWSOnDemandPriceList);
		List<NodePrice> prices = filterAndCreateNodePrices("eu-west-1", AWSUpdatePriceHistory.EBSInstanceTypes);
		clearNodePrices();
		saveNodePrices(prices);
		System.out.println("Done!");
	}

	private void saveNodePrices(List<NodePrice> prices) {
		logger.log(Level.INFO, "Saving OnDemand prices to database");
		Transaction tx = HibernateUtil.getTransaction(session);
		for (NodePrice nodePrice : prices) {
			HibernateUtil.saveObject(session, nodePrice);
		}
		tx.commit();
	}
	
	private void clearNodePrices() {
		logger.log(Level.INFO, "Clearing NodePrice Table");
		session=sessionFactory.getCurrentSession();
		Transaction tx = HibernateUtil.getTransaction(session);
		Query deleteQuery = session.createQuery("delete from NodePrice where priceType="+NodePrice.PRICETYPE.ONDEMAND.ordinal());
		deleteQuery.executeUpdate();
		tx.commit();
	}
	
	
	public static void main(String[] args) {
		AWSOnDemandPrice main = new AWSOnDemandPrice();
		try {
			main.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
