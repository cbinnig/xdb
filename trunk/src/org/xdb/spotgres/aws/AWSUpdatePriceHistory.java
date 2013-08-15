package org.xdb.spotgres.aws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
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
import org.xdb.spotgres.HibernateUtil;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.SpotPriceHelper;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeSpotPriceHistoryRequest;
import com.amazonaws.services.ec2.model.DescribeSpotPriceHistoryResult;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.SpotPrice;

public class AWSUpdatePriceHistory {
	private AmazonEC2 ec2;
	private Session session;
	private SessionFactory sessionFactory;
	private List<NodePrice> nodePriceList;
	
	public static Collection<String> EBSInstanceTypes = new ArrayList<String>();
	static {
		EBSInstanceTypes.add(InstanceType.M1Large.toString());
		EBSInstanceTypes.add(InstanceType.M1Xlarge.toString());
		EBSInstanceTypes.add(InstanceType.M3Xlarge.toString());
		EBSInstanceTypes.add(InstanceType.M32xlarge.toString());
		EBSInstanceTypes.add(InstanceType.M22xlarge.toString());
		EBSInstanceTypes.add(InstanceType.M24xlarge.toString());
		EBSInstanceTypes.add(InstanceType.C1Xlarge.toString());
	}
	
	protected Logger logger;
	
	private void initHibernate() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
	}
	
	public void setUp() throws IOException {
		AWSCredentials credentials = new PropertiesCredentials(
				AWSTest.class.getResourceAsStream("AwsCredentials.properties"));

		ec2 = new AmazonEC2Client(credentials);
		Region euWest1 = Region.getRegion(Regions.EU_WEST_1);

		ec2.setRegion(euWest1);

		initHibernate();

		logger = XDBLog.getLogger(this.getClass().getName());
	}

	private void writePrice(SpotPrice spotPrice) {
		NodePrice price = new NodePrice(spotPrice);
		session.persist(price);
		price = null;
	}

	private void processSpotPriceHistoryResult(DescribeSpotPriceHistoryResult spotPriceResult) {
		List<SpotPrice> prices = spotPriceResult.getSpotPriceHistory();
		for (SpotPrice price : prices) {
			writePrice(price);
		}
		prices = null;
	}

	private DescribeSpotPriceHistoryRequest setupSpotPriceRequest() {
		DescribeSpotPriceHistoryRequest returnValue = new DescribeSpotPriceHistoryRequest();
		returnValue.setInstanceTypes(EBSInstanceTypes);
		return returnValue;
	}

	public void execute() throws IOException {
		setUp();
		clearNodePriceTable();
		loadPriceHistory();
		addDurationForPrice();
		calculateAvailabilityPercentageAllTypes((float) 0.10);
		calculateAvailabilityPercentageAllTypes((float) 0.20);
		calculateAvailabilityPercentageAllTypes((float) 0.30);
	}

	private void clearNodePriceTable() {
		logger.log(Level.INFO, "Clearing NodePrice Table");
		Transaction tx = session.beginTransaction();
		Query deleteQuery = session.createQuery("delete from NodePrice where priceType="+NodePrice.PRICETYPE.SPOT.ordinal());
		deleteQuery.executeUpdate();
		tx.commit();
	}

	private void loadPriceHistory() {
		logger.log(Level.INFO, "Loading price history from Amazon");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		DescribeSpotPriceHistoryRequest request = setupSpotPriceRequest();
		DescribeSpotPriceHistoryResult result = null;
		String nextToken = "";
		Date endDate = calculateEndDate(3);
		int tokenCount = 1;
		boolean continueLoad = true;
		while (continueLoad) {
			result = ec2.describeSpotPriceHistory(request);
			processSpotPriceHistoryResult(result);
			session.flush();
			session.clear();
			nextToken = result.getNextToken();
			if (nextToken == null || nextToken.isEmpty()
					|| result.getSpotPriceHistory().get(0).getTimestamp().before(endDate)) {
				continueLoad = false;
			}
			request.setNextToken(nextToken);
			if (continueLoad)
				System.out.println("Token " + (tokenCount++) + " +++ ID: " + nextToken);
		}
		tx.commit();
	}

	private void addDurationForPrice() {
		logger.log(Level.INFO, "Adding duration to NodePrice data sets");
		Map<String, Map<String, java.sql.Timestamp>> durationMap = new HashMap<String, Map<String, java.sql.Timestamp>>();
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Iterator<NodePrice> priceIter = session.createQuery("from NodePrice order by priceTime desc").iterate();
		int counter = 0;
		while (priceIter.hasNext()) {
			NodePrice nodePrice = priceIter.next();
			calculateDuration(durationMap, nodePrice);
			if (counter++ % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
		tx.commit();
	}

	private void calculateDuration(Map<String, Map<String, java.sql.Timestamp>> durationMap, NodePrice nodePrice) {
		logger.log(Level.INFO, "Calculating lifespan of NodePrice data sets");
		Map<String, java.sql.Timestamp> zoneMap;
		zoneMap = durationMap.get(nodePrice.getClusterZone());
		if (zoneMap == null) {
			zoneMap = new HashMap<String, java.sql.Timestamp>();
			durationMap.put(nodePrice.getClusterZone(), zoneMap);
		}
		java.sql.Timestamp lastTimestamp = zoneMap.get(nodePrice.getNodeType());
		if (lastTimestamp != null) {
			nodePrice.setDuration((lastTimestamp.getTime() - nodePrice.getPriceTime().getTime()) / 1000);
		}
		zoneMap.put(nodePrice.getNodeType(), nodePrice.getPriceTime());
	}

	private Date calculateEndDate(int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -months);
		Date endDate = cal.getTime();
		return endDate;
	}
	
	public Map<String, Map<String, SpotPriceHelper>> calculateAvailabilityPercentageAllTypes(float bidPrice) {
		logger.log(Level.INFO, "Calculating availability per NodeType & Zone");
		Map<String, Map<String, SpotPriceHelper>> availabilityMap = new HashMap<String, Map<String, SpotPriceHelper>>();
		loadNodePriceList();
		Iterator<NodePrice> priceIter = nodePriceList.iterator();
		while (priceIter.hasNext()) {
			NodePrice nodePrice = priceIter.next();
			if (nodePrice.getDuration() > 0) {
				Map<String, SpotPriceHelper> zoneMap = availabilityMap.get(nodePrice.getClusterZone());
				if (zoneMap == null) {
					zoneMap = new HashMap<String, SpotPriceHelper>();
					availabilityMap.put(nodePrice.getClusterZone(), zoneMap);
				}
				SpotPriceHelper priceData = zoneMap.get(nodePrice.getNodeType());
				if (priceData == null) {
					priceData = new SpotPriceHelper(nodePrice.getNodeType(), nodePrice.getClusterZone());
					zoneMap.put(nodePrice.getNodeType(), priceData);
				}
				priceData.addNodePriceData(nodePrice, bidPrice);
			}
		}
		System.out.println("Given price: " + bidPrice + "$");
		for (String zone : availabilityMap.keySet()) {
			System.out.println(zone);
			System.out.println("==============");
			Map<String, SpotPriceHelper> zoneMap = availabilityMap.get(zone);
			List<String> types = new ArrayList<String>(zoneMap.keySet());
			Collections.sort(types);
			for (String type : types) {
				SpotPriceHelper priceHelper = zoneMap.get(type);
				System.out.println(priceHelper.toString());
			}
			System.out.println();
		}
		return availabilityMap;
	}
	
	@SuppressWarnings("unchecked")
	private void loadNodePriceList(){
		if (this.nodePriceList == null){
			session = sessionFactory.getCurrentSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			this.nodePriceList = session.createQuery("from NodePrice").list();
			tx.commit();
		}
	}
	
	public void resetNodePriceList(){
		this.nodePriceList=null;
	}
		
	public static void main(String[] args) {
		AWSUpdatePriceHistory main = new AWSUpdatePriceHistory();
		try {
			main.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
