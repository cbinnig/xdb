package org.xdb.elasticpartitioning.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SQLToXMLConverter {
	public static void main(String[] args) {
		BufferedReader br = null;
		 
		try {
 
			String line;
 
			br = new BufferedReader(new FileReader("./src/org/xdb/elasticpartitioning/query.tpl"));
 
			Map<String, String> tableNameAbreviation = new HashMap<String, String>();
			while ((line = br.readLine()) != null) {
				if (line.startsWith("from")){
					String[] tokens = line.split(" ");
					if (tokens.length == 3)
						tableNameAbreviation.put(tokens[2].trim(), tokens[1].trim());
					else if (tokens.length == 2) tableNameAbreviation.put(tokens[1].trim(), tokens[1].trim());
					else throw new Exception("exception in FROM clause:");
				}
				else if (line.contains("=")){
					String[] parts = line.split("=");
					String[] left = parts[0].trim().split("\\.");
					if (!tableNameAbreviation.containsKey(left[0].trim())) throw new Exception("table " + left[0].trim() + " not found");
					String leftTableName = tableNameAbreviation.get(left[0].trim());
					String leftAttribute = left[1].trim();
					
					String[] right = parts[1].split("\\.");
					if (!tableNameAbreviation.containsKey(right[0].trim())) throw new Exception("table " + right[0].trim() + " not found");
					String rightTableName = tableNameAbreviation.get(right[0].trim());
					String rightAttribute = right[1].trim();
					
					System.out.println("\t\t\t\t" + "<equi-join>");
					System.out.println("\t\t\t\t\t" + "<source-table>" + leftTableName + "</source-table>");
					System.out.println("\t\t\t\t\t" + "<target-table>" + rightTableName + "</target-table>");
					System.out.println("\t\t\t\t\t" + "<source-attribute>" + leftAttribute + "</source-attribute>");
					System.out.println("\t\t\t\t\t" + "<target-attribute>" + rightAttribute + "</target-attribute>");
					System.out.println("\t\t\t\t" + "</equi-join>");
					
				}
				else throw new Exception("What??");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
