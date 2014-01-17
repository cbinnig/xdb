package org.xdb.test.hive;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

@Description(
		name = "levenshteinDistance",
		value = "_FUNC_(str, str) - Calculates the Levenshtein distance",
		extended = "Example:\n" +
		"  > SELECT levenshteinDistance(a.name, b.name) FROM authors a, authors b where a.name<>b.name;\n" +
		"  0.2"
		)
public class LevenshteinDistance extends UDF {

	public DoubleWritable evaluate(Text desc1, Text desc2){
		double returnValue = -1;
		returnValue = levenshteinDistance(desc1.toString(), desc2.toString());
		return new DoubleWritable(returnValue);		
	}
	
	/**
	 * Calculate Levenshtein distance
	 * @param string1
	 * @param string2
	 * @return
	 */
	public static double levenshteinDistance(String string1, String string2) {
		final int length1 = string1.length();
		final int length2 = string2.length();

		int[][] distance = new int[length1 + 1][length2 + 1];

		for (int i = 0; i <= length1; i++) {
			distance[i][0] = i;
		}
		for (int j = 0; j <= length2; j++) {
			distance[0][j] = j;
		}

		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				final char char1 = string1.charAt(i - 1);
				final char char2 = string2.charAt(j - 1);
				final int offset = (char1 == char2) ? 0 : 1;
				distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1,
						distance[i][j - 1] + 1), distance[i - 1][j - 1]
						+ offset);
			}
		}
		return 1-((double)distance[length1][length2]) / Math.max(length1, length2);
	}
}
