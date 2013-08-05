package org.xdb.spotgres.aws;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xdb.spotgres.HibernateUtil;
import org.xdb.spotgres.pojos.NodePrice;

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

	public void setUp() throws IOException {
		AWSCredentials credentials = new PropertiesCredentials(
				AWSTest.class.getResourceAsStream("AwsCredentials.properties"));

		ec2 = new AmazonEC2Client(credentials);
		Region euWest1 = Region.getRegion(Regions.EU_WEST_1);

		ec2.setRegion(euWest1);
	}

	private void writePrice(SpotPrice spotPrice) {
		NodePrice price = new NodePrice(spotPrice);
		session.persist(price);
		price = null;
	}

	private void initHibernate() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
	}

	private void processSpotPriceHistoryResult(
			DescribeSpotPriceHistoryResult spotPriceResult) {
		List<SpotPrice> prices = spotPriceResult.getSpotPriceHistory();
		int i = 0;
		for (SpotPrice price : prices) {
			writePrice(price);
		}
		prices = null;
	}
	
	private DescribeSpotPriceHistoryRequest setupSpotPriceRequest(){
		DescribeSpotPriceHistoryRequest returnValue = new DescribeSpotPriceHistoryRequest();
		Collection<String> types = new ArrayList<>();
		types.add(InstanceType.M1Large.toString());
		types.add(InstanceType.M1Xlarge.toString());
		types.add(InstanceType.M3Xlarge.toString());
		types.add(InstanceType.M32xlarge.toString());
		types.add(InstanceType.M22xlarge.toString());
		types.add(InstanceType.M24xlarge.toString());
		types.add(InstanceType.C1Xlarge.toString());
		returnValue.setInstanceTypes(types);
		return returnValue;
	}
	
	public void execute() throws IOException{
		setUp();
		initHibernate();
		Transaction tx = session.beginTransaction();
		DescribeSpotPriceHistoryRequest request = setupSpotPriceRequest();
		DescribeSpotPriceHistoryResult result = ec2.describeSpotPriceHistory(request);
		processSpotPriceHistoryResult(result);
		tx.commit();
		String nextToken=result.getNextToken();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -3);
		Date endDate = cal.getTime();
		int tokenCount=1;
		boolean continueLoad=true;
		while (nextToken != null && nextToken.isEmpty() == false && continueLoad){
			if (!session.isOpen()){
				session = sessionFactory.openSession();
			}
			request.setNextToken(nextToken);
			result = ec2.describeSpotPriceHistory(request);
			tx = session.beginTransaction();
			System.out.println("Token " + (tokenCount++) +" +++ ID: " + nextToken);
			if (result.getSpotPriceHistory().get(0).getTimestamp().before(endDate)){
				continueLoad = false;
			}
			processSpotPriceHistoryResult(result);
			nextToken=result.getNextToken();
			tx.commit();
			System.gc();
		}
	}
	
	public static void main(String[] args) {
		AWSUpdatePriceHistory main = new AWSUpdatePriceHistory();
		try {
			main.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
