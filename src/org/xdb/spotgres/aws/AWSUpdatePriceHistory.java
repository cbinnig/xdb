package org.xdb.spotgres.aws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
		DescribeSpotPriceHistoryResult result = ec2.describeSpotPriceHistory(setupSpotPriceRequest());
		processSpotPriceHistoryResult(result);
		tx.commit();
		String nextToken=result.getNextToken();
		int tokenCount=1;
		while (nextToken != null && nextToken.isEmpty() == false){
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			System.out.println("Token " + tokenCount++);
			result = result.withNextToken(nextToken);
			processSpotPriceHistoryResult(result);
			nextToken=result.getNextToken();
			tx.commit();
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
