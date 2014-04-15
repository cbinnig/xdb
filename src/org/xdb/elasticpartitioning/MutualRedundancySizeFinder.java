package org.xdb.elasticpartitioning;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.xdb.elasticpartitioning.database.DatabaseAbstractionLayer;
import org.xdb.elasticpartitioning.database.DatabaseNotInitializedException;
import org.xdb.elasticpartitioning.database.ForeignKey;
import org.xdb.elasticpartitioning.database.Table;
import org.xdb.elasticpartitioning.util.MyMath;
import org.xdb.elasticpartitioning.util.Settings;

public class MutualRedundancySizeFinder {
	private int numberOfPartitions;
	private DatabaseAbstractionLayer db;
	private double maxFrequency;
	private double sampleSizeRatio;
	private long dbTime;
	private long calculationTime;
	private Map<Integer, Double> averageCopiesSaver;



	public MutualRedundancySizeFinder(int numberOfPartitions, double sampleSizeRatio, boolean readFromFile) {
		try {
			maxFrequency = -1;
			new MyMath();
			if (!readFromFile)
				DatabaseAbstractionLayer.initialize("xroot", "xroot", "localhost", "3306", "tpch_s1");
			db = DatabaseAbstractionLayer.getInstance();
			this.numberOfPartitions = numberOfPartitions;
			this.sampleSizeRatio = sampleSizeRatio;
			this.dbTime = 0;
			this.calculationTime = 0;
			this.averageCopiesSaver = new HashMap<Integer, Double>();

			if (readFromFile){

				try {
					BufferedReader br;
					br = new BufferedReader(new FileReader(Settings.AVERAGE_COPIES_FILE));
					String line = br.readLine();
					StringTokenizer st;
					int frequency;
					double averageCopies;
					while (line != null) {
						st = new StringTokenizer(line, "\t");
						frequency = Integer.parseInt(st.nextToken());
						averageCopies = Double.parseDouble(st.nextToken());
						averageCopiesSaver.put(frequency, averageCopies);
						line = br.readLine();
					}
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


		} catch (DatabaseNotInitializedException e) {
			e.printStackTrace();
		}
	}

	public double findRedundancy(Table sourceTable, Table destTable, ForeignKey fk) throws Exception{

		double totalTableSize = 0;
		Set<String> attList = fk.getSourceAttributeList();
		long t1 = System.nanoTime();
		Map<String, Integer> histogram = db.buildHistogram(sourceTable, attList, this.sampleSizeRatio);
		long t2 = System.nanoTime();
		dbTime += (t2-t1)/1000000;

		t1 = System.nanoTime();
		int factor = (int)(1 / this.sampleSizeRatio);
		for (String key :  histogram.keySet()){
			int frequency = histogram.get(key) * factor;
			if (frequency > maxFrequency) maxFrequency = frequency;
			double copies = getAverageNumberOfCopies(frequency);

			if (copies <= 0)
				throw new Exception ("Method MutualRedundancySizeFinder.findRedundancy: Copies cannot be negative");
			totalTableSize += copies;
		}
		// Some records in the destTable might not be reffered in the sourceTable.
		// They shouldnt be thrown away. Instead, they should be kept in a secial partition
		// called remainderPartition
		long remainderPartitionSize = destTable.getTableSize() - histogram.size();
		totalTableSize += remainderPartitionSize;
		t2 = System.nanoTime();

		calculationTime += (t2-t1)/1000000;
		return totalTableSize;
	}

	public long getCalculationTime() {
		return calculationTime;
	}
	public long getDbTime() {
		return dbTime;
	}

	public double getMaxFrequency() {
		return maxFrequency;
	}

	private double getAverageNumberOfCopies(int frequency) throws Exception {
		// This function is used when average-number-of-copies are loaded from a file
		// and not actually calculated. 
		// Otherwise, the method calculateAverageNumberOfCopies should be invoked
		
		double copies;
		if (this.averageCopiesSaver.containsKey(frequency))
			copies = this.averageCopiesSaver.get(frequency);
		else copies = this.numberOfPartitions;
		//System.out.println("Frequency" + frequency + " - Copies: " + copies);
		return copies;

	}
	
	private double calculateAverageNumberOfCopies(int frequency) throws Exception {
		 // The actual code for finding average number of copies for each frequency.

		BigInteger numerator = new BigInteger("0");
		BigInteger a;
		BigInteger b;
		MyMath myMath = new MyMath();

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



		System.out.println("Frequency" + frequency + " - Copies: " + output);
		return output.doubleValue();
	}

	public static void main(String[] args) {
		try {
			int partitionsCnt = 100;
			String fileName = "average-copies-for-" + partitionsCnt + "-nodes.txt";
			File file = new File("./src/org/xdb/elasticpartitioning/" + fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			MutualRedundancySizeFinder redFinder = new MutualRedundancySizeFinder(partitionsCnt, 1, false);

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			double copies;
			for (int frequency = 1; frequency < 1000; frequency++){
				copies = redFinder.calculateAverageNumberOfCopies(frequency);
				String s = frequency + "\t" + copies ;
				bw.write(s);
				bw.write("\n");
			}
			bw.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}






}