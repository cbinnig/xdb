package org.xdb.elasticpartitioning.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MyMath {
	private Map<Pair, BigInteger> combinatorialSaver;
	private Map<Pair, BigInteger> stirlingSaver;
	private Map<Integer, BigInteger> factorialSaver;
    BufferedReader br;
	


	public MyMath() {
		stirlingSaver = new HashMap<Pair, BigInteger>();
		combinatorialSaver = new HashMap<Pair, BigInteger>();
		factorialSaver = new HashMap<Integer, BigInteger>();
		factorialSaver.put(0, new BigInteger("1"));
		factorialSaver.put(1, new BigInteger("1"));
		
		try {
			br = new BufferedReader(new FileReader(FilesLocation.STIRLING_FILE));
			String line = br.readLine();
			StringTokenizer st;
			int balls;
			int boxes;
			BigInteger value;
			Pair pair;
	        while (line != null) {
	        	st = new StringTokenizer(line, "\t");
	        	balls = Integer.parseInt(st.nextToken());
	        	boxes = Integer.parseInt(st.nextToken());
	        	value = new BigInteger(st.nextToken());
	        	pair = new Pair(balls, boxes);
	        	stirlingSaver.put(pair, value);
	            line = br.readLine();

	        }
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public BigInteger combination(int n, int k){
		// since C(n, k) = C(n, n-k)
		int max = Math.max(k, n-k);
		int min = Math.min(k, n-k);
		Pair comb = new Pair(n, max);
		if (combinatorialSaver.containsKey(comb))
			return combinatorialSaver.get(comb);
		else{
			BigInteger numerator = incompleteFactorial(n, max+1);
			BigInteger denominator = factorial(min);
			BigInteger output = numerator.divide(denominator);
			combinatorialSaver.put(comb, output);
			return output;
		}
		//return factorial(n).divide((factorial(k).multiply(factorial(n - k))));
	}

	private BigInteger factorial(int i){
		BigInteger output;
		if (factorialSaver.containsKey(i))
			output = factorialSaver.get(i);
		else {
			output = factorial(i - 1).multiply(new BigInteger(String.valueOf(i)));
			factorialSaver.put(i, output);
		}
		return output;
	}	

	private BigInteger incompleteFactorial(int upperNumber, int lowerNumber){
		BigInteger output = new BigInteger("1"); 
		for (int i=lowerNumber; i<= upperNumber; i++)
			output = output.multiply(BigInteger.valueOf(i));
		return output;
	}

	public BigInteger combinationWithNonEmptyBoxes(int balls, int boxes){
		Pair pair = new Pair(balls, boxes);
		if (stirlingSaver.containsKey(pair)) return stirlingSaver.get(pair);
		else{
			int sign = 1;
			BigInteger sum = new BigInteger("0");

			for (int k=0; k < boxes; k++){
				BigInteger a = combination(boxes, k);
				BigInteger b = BigDecimal.valueOf(boxes-k).pow(balls).toBigInteger();
				BigInteger term = a.multiply(b);
				if (sign == 1) sum = sum.add(term);
				else sum = sum.subtract(term);
				sign = -sign;
			}
			stirlingSaver.put(pair, sum);
			assert(sum.signum() == 1):
				"The method combinationWithNonEmptyBoxes returned a negative number, which is wrong"; 
			return sum;
		}
	}
	public static void main(String[] args) {
		MyMath myMath = new MyMath();
		System.out.println( myMath.combinationWithNonEmptyBoxes(222, 148));
		/*
		try {

			File file = new File("./stirling1.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			System.out.println("Done");


			MyMath myMath = new MyMath();
			for (int frequency = 1; frequency < 10; frequency++){
				for (int copies = 1; copies <= frequency; copies++){
					String s = frequency + "\t" + copies + "\t" + myMath.combinationWithNonEmptyBoxes(frequency, copies);
					bw.write(s);
					bw.write("\n");
				}
				System.out.println(frequency + " done.");
			}
			bw.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
}

class Pair{
	private Integer a;
	private Integer b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean equals(Object obj) {
		Pair toBeCompared = null;
		if (obj instanceof Pair)
			toBeCompared = (Pair)obj;
		else return false;
		return (this.a == toBeCompared.a && this.b == toBeCompared.b);
	}

	@Override
	public int hashCode() {
		String temp = a.toString() + b.toString();
		return temp.hashCode();
	}


}
