package org.xdb.spotgres.aws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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

@SuppressWarnings("unused")
public class AWSTest {
	private AmazonEC2 ec2;
	private ArrayList<String> instanceIds;
	private ArrayList<String> spotInstanceRequestIds;

	@Before
	public void setUp() throws Exception {
		AWSCredentials credentials = new PropertiesCredentials(
				AWSTest.class.getResourceAsStream("AwsCredentials.properties"));

		ec2 = new AmazonEC2Client(credentials);
		Region euWest1 = Region.getRegion(Regions.EU_WEST_1);

		ec2.setRegion(euWest1);
	}

	@Test
	public void test() {
		DescribeSpotPriceHistoryRequest sphr = new DescribeSpotPriceHistoryRequest();
		// sphr.setAvailabilityZone(Region.getRegion(Regions.EU_WEST_1).getName());
		Collection<String> types = new ArrayList<String>();
		types.add(InstanceType.M1Large.toString());
		sphr.setInstanceTypes(types);
		DescribeSpotPriceHistoryResult result = ec2.describeSpotPriceHistory(sphr);
		List<SpotPrice> prices = result.getSpotPriceHistory();
		for (SpotPrice price : prices) {
			System.out.println(price.getTimestamp() + ": " + price.getSpotPrice());
		}
	}

}
