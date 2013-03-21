package org.xdb.tpch.splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * start external tool. It splits the TPCH-Tables into several files using a hash-function.
	 * main-function, renamed, so there is no influence to funSQL
	 */
//	public static void main(String[] args) {
	public static void usedToBeMain(String[] args) {
		String table = null;
		int columns[] = null;
		int partitions = 0;
		try {
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("which table shoud be splittet? (name or 'all')");
			table = reader.readLine();
			
			System.out.println("which columns should be used by generating hash values? (seperated by comma)");
			String columnsString = reader.readLine();
			String[] colStrings = columnsString.split(",");
			columns = new int[colStrings.length];
			for(int i=0;i<colStrings.length;i++){
				columns[i] = Integer.parseInt(colStrings[i]);
			}

			System.out.println("how many partitions should be made?");
			String partitionString = reader.readLine();
			partitions = Integer.parseInt(partitionString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Splitter splitter = new Splitter();
		
		if(!table.equals("all")){
			String srcPath = /*"tbl"+\\*/table+".tbl";
			String destPath = /*"tbl"+\\*/table+".tbl";
			splitter.createPartitions(srcPath, destPath, partitions, columns);
		}
		else{
			String[] tableNames = {"customer", "lineitem", "nation", "orders", "part", "partsupp", "region", "supplier"};

			for(int i=0;i<tableNames.length;i++){
				String srcPath = "tbl\\"+tableNames[i]+".tbl";
				String destPath = "tbl\\"+tableNames[i]+".tbl";
				splitter.createPartitions(srcPath, destPath, partitions, columns);
				System.out.println(tableNames[i]+" finished");
			}
			
		}
		
		System.out.println("done");
	}
}
