package org.xdb.tpch.splitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Rene
 *
 */
public class Splitter {
	
	/**
	 * Splits pSrcPath (must be tbl-File) into pCountPartition different files
	 * @param pSrcPath
	 * @param pDestPath
	 * @param pCountPartition
	 * @param keyColumns
	 */
	public void createPartitions(String pSrcPath, String pDestPath, int pCountPartition, int[] keyColumns){
		
		
		Map<Integer, BufferedWriter> writers = new HashMap<Integer, BufferedWriter>();
		BufferedReader br = null;
		
		for(int i=0;i<pCountPartition;i++){
			BufferedWriter bw = null;
			try {
				String fileName = pDestPath.substring(0, pDestPath.lastIndexOf("."));
				fileName += "-" + i + ".tbl"; 
				bw = new BufferedWriter(new FileWriter(fileName));
			} catch (IOException e) {
				//Create Error
				e.printStackTrace();
			}			
			writers.put(i, bw);
		}
		
	     try {
	    	 String currLine;
	    	 // create reader for the input tbl file
	         br = new BufferedReader(new FileReader(pSrcPath));
	         
	         
	         
	         while ((currLine = br.readLine()) != null) {
	        	 
	        	 //Split the line into Attribute-Strings
	        	 String[] atts = currLine.split("|");
	        	 
	        	 // create HashCodes from given Attributes
	        	 int hashCodes[] = new int[keyColumns.length];
	        	 for(int i=0;i<keyColumns.length;i++){
	        		 hashCodes[i] = atts[keyColumns[i]].hashCode();
	        	 }

	        	 // calculate HashValue of this line
	        	 int hashValueLine = 0;
	        	 for(int i=0;i<hashCodes.length;i++){
	        		 hashValueLine += hashCodes[i];
	        	 }
	        	 hashValueLine = hashValueLine % pCountPartition;
	        	 
	        	 writers.get(hashValueLine).append(currLine);
	        	 writers.get(hashValueLine).newLine();
	         }
	         


	         
	       } catch (IOException e) {
	    	   e.printStackTrace();
	       } finally {
	            //Close the BufferedReader and BufferedWriters
	            try {
	            	br.close();
	            	for(int i=0;i<writers.size();i++){
		                if (writers.get(i) != null) {
		                	writers.get(i).flush();
		                	writers.get(i).close();
		                }
	            		
	            	}
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	       }
	}
}
