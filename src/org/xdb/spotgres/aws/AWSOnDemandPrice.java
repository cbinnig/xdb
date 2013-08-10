package org.xdb.spotgres.aws;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	
	private void initHibernate() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
	}		
	
	private static AWSOnDemandPriceRoot loadOnDemandPrices(String url) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		AWSOnDemandPriceRoot root = mapper.readValue(new URL(url),AWSOnDemandPriceRoot.class);
		return root;
	}
	
	private List<NodePrice> filterAndCreateNodePrices(String regionName, Collection<String> instanceTypes){
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
		root =AWSOnDemandPrice.loadOnDemandPrices(AWSOnDemandPriceList);
		List<NodePrice> prices = filterAndCreateNodePrices("eu-west-1", AWSUpdatePriceHistory.EBSInstanceTypes);
		initHibernate();
		Transaction tx = session.beginTransaction();
		for (NodePrice nodePrice : prices) {
			session.persist(nodePrice);
		}
		tx.commit();
		System.out.println("Done!");
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
