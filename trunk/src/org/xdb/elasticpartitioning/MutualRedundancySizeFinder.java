package org.xdb.elasticpartitioning;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import org.xdb.elasticpartitioning.util.MyMath;

public class MutualRedundancySizeFinder {
	private int numberOfPartitions;
	private DatabaseAbstractionLayer db;
	private MyMath myMath;
	private double maxFrequency;



	public MutualRedundancySizeFinder(int numberOfPartitions) {
		try {
			maxFrequency = -1;
			myMath = new MyMath();
			db = DatabaseAbstractionLayer.getInstance();
			this.numberOfPartitions = numberOfPartitions;
		} catch (DatabaseNotInitializedException e) {
			e.printStackTrace();
		}
	}

	public double findRedundancy(Table table, ForeignKey fk) throws Exception{
		double totalTableSize = 0;
		Set<String> attList = fk.getSourceAttributeList();
		Map<String, Integer> histogram = db.buildHistogram(table, attList);
		for (String key :  histogram.keySet()){
			int frequency = histogram.get(key);
			if (frequency > maxFrequency) maxFrequency = frequency;
			double copies = findAverageNumberOfCopies(frequency);
			if (copies <= 0)
				throw new Exception ("Method MutualRedundancySizeFinder.findRedundancy: Copies cannot be negative");
			totalTableSize += copies;
		}
		return totalTableSize;
	}
	
	public double getMaxFrequency() {
		return maxFrequency;
	}

	private double findAverageNumberOfCopies(int frequency) throws Exception {
		BigInteger numerator = new BigInteger("0");
		BigInteger a;
		BigInteger b;

		for (int copies = 1; copies <= Math.min(frequency, numberOfPartitions); copies++){
			a = myMath.combination(numberOfPartitions, copies);
			b = myMath.combinationWithNonEmptyBoxes(frequency, copies);
			numerator = numerator.add(a.multiply(b).multiply(BigInteger.valueOf(copies)));
			
			if (numerator.signum() != 1)
				throw new Exception("Wrong value in method MutualRedundancySizeFinder.findAverageNumberOfCopies: numerator cannot be negative");
		}
		BigDecimal denominator = BigDecimal.valueOf(numberOfPartitions).pow(frequency);
		
		if (denominator.signum() != 1)
			throw new Exception("Wrong value in method MutualRedundancySizeFinder.findAverageNumberOfCopies: denominator cannot be negative");
		
		BigDecimal output = new BigDecimal(numerator);
		output = output.divide(denominator);
		
		if (output.signum() != 1)
			throw new Exception("method findAverageNumberOfCopies returned a negative or zero, which is wrong either way");
		

		//System.out.println("Frequency" + frequency + " - Copies: " + output);
		return output.doubleValue();
	}
}