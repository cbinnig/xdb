package org.xdb.elasticpartitioning.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyMath {
	private Map<Pair, BigInteger> combinatorialSaver;
	private Map<Pair, BigInteger> stirlingSaver;
	private Map<Integer, BigInteger> factorialSaver;



	public MyMath() {
		stirlingSaver = new HashMap<Pair, BigInteger>();
		combinatorialSaver = new HashMap<Pair, BigInteger>();
		factorialSaver = new HashMap<Integer, BigInteger>();
		factorialSaver.put(0, new BigInteger("1"));
		factorialSaver.put(1, new BigInteger("1"));
/*
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
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/


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
	
	public static List<List<Integer>> powerSet(Set<Integer> originalSet) {
        List<List<Integer>> sets = new ArrayList<List<Integer>>();
        if (originalSet.isEmpty()) {
            sets.add(new ArrayList<Integer>());
            return sets;
        }
        List<Integer> list = new ArrayList<Integer>(originalSet);
        Integer head = list.get(0);
        Set<Integer> rest = new HashSet<Integer>(list.subList(1, list.size()));
        for (List<Integer> set : powerSet(rest)) {
            List<Integer> newSet = new ArrayList<Integer>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
	
	public static void main(String[] args) {
		Set<Integer> a = new HashSet<Integer>();
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		
		List<List<Integer>> b = MyMath.powerSet(a);
		Comparator<List<Integer>> comparator = new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				if (o1.size() < o2.size()) return -1;
				else return 1;
			}
		};
		Collections.sort(b, comparator);
		System.out.println(b);
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
