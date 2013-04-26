package org.xdb.tools.partitioner;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map; 
import java.util.Set;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

public class DataPartitioner { 

	// Used to store the buffers for every partitions, so we use 
	// them later when writing to the partitions file. 
	private Map<Integer, BufferedOutputStream> filesMap = new HashMap<Integer, BufferedOutputStream>();   
	
	private Set <String> referenceFilePartition; 

	private static int CHUNK_SIZE = 20000000; 
	
	private boolean isChunkwiseMode = false; 
	
	public Set<String> getReferenceFilePartition(){
		return referenceFilePartition;
	} 
	
	public void setReferenceFilePartition(int size, int loadFactor){
		referenceFilePartition = new HashSet<String>(size, loadFactor); 
		isChunkwiseMode = true; 
	} 
	
	public void setReferenceFilePartition(){ 

		referenceFilePartition = new HashSet<String>();
	}
	
	/**
	 * @return the filesMap
	 */
	public Map<Integer, BufferedOutputStream> getFilesMap() {
		return filesMap;
	}

	/** 
	 * Create the buffer for the partition files, store them 
	 * in a hash map with the number of the partition as a key of 
	 * the buffer.
	 * @param filesName  
	 * @param numberofPartitions 
	 */
	public void setFilesMap(String fileName, int numberofPartitions) {

		String fileType = Utils.getFileType(fileName);
		
		// delete old partitions if they are existing, in case of re-partitioning  
		Utils.deleteOldPartitions(fileName); 

		int fileNumber; 
		for (int i=0; i<numberofPartitions; i++){ 

			fileNumber = i; 
			// Adding the partition number as a part of the file name. 
			File file = new File(Utils.getFileDirectory(fileName)+"/"+Utils.getFileName(fileName)+"_p"+fileNumber+"."+fileType);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				try {
					file.createNewFile(); 
					// Create a buffer stream for every file, and store it 
					// in a hash map.
					FileOutputStream fos = new FileOutputStream(file, true); 
					BufferedOutputStream bos = new BufferedOutputStream(fos); 
					this.filesMap.put(i, bos); 
				} catch (IOException e) {
					System.err.println("Error occured while creating the files " + e);
				}
			}  
		} 

	}

	/** 
	 * Partition the data file using the hashing method. The partition function 
	 * calculate the hash code of a user-defined set of columns % the number 
	 * of the partitions desired. 
	 * 
	 * @param fileName
	 * @param indices
	 */
	private void partitionDataByHashing (String fileName, String indices){

		// Return the indices in an array format. 
		String[] partitionIndices = Utils.getKeyIndeicesFromString(indices.trim());

		try {

			int hash; 
			int partitionNumber = 0;  
			int numberOfPartitions = this.filesMap.size(); 

			BufferedReader br = new BufferedReader(new FileReader(fileName)); 
			String line = ""; 
			while ((line = br.readLine()) != null) {   
				hash = Utils.calculateHash(line, partitionIndices);
				partitionNumber = hash%numberOfPartitions; // the partition function 
				filesMap.get(partitionNumber).write(line.getBytes()); 
				filesMap.get(partitionNumber).write(System.getProperty("line.separator").getBytes()); 

			}  
			for(int i = 0; i < filesMap.size(); i++){ // for each file buffer in the file map
				filesMap.get(i).flush();
				filesMap.get(i).close(); 
				System.out.println("Partition number "+i+" has been written.");
			} 
			br.close();
			System.out.println("Hashing Partitioning Done");
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("An error occured while generating the partitions "+e.getMessage());
		}

	} 
 
	/** 
	 * Check if the reference table (file) is partitioned or not
	 * before starting the reference partitioning.
	 * 
	 * @param file the path of the file will be partitioned. 
	 * @param table the name of the table will be checked.
	 * 
	 * @return an integer contains the number of partitions found 
	 * for the specified table, 0 if the table is not partitioned 
	 */
	private int isTablePartitoned(String file, final String table){ 

		String directory = Utils.getFileDirectory(file);
		File dir = new File(directory);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept
			(File dir, String name) {
				return name.startsWith(table+"_p");
			}
		}; 

		String[] children = dir.list(filter);
		if (children == null) { 
			// then the reference table is not partitioned yet. 
			return 0;
		} 

		return children.length;
	}

	/** 
	 * prepare what is required to make the reference partition. Read the reference file partitions and 
	 * store them in a map, extract the partition keys and call the function writePartition. 
	 *  
	 * @param file the input file will be partitioned 
	 * @param partitionIndices the indices of the partition columns in the input table 
	 * @param referenceFile the reference file (table) name (without path)
	 * @param referenceIndices the indices of the partition columns in the reference table. 
	 * @param numberOfReferencePartitions the number of partitions desired. (the number of the reference file partitions)
	 * 
	 */
	private void partitionDataByReference(String file, String partitionIndices,
			String referenceFile, String referenceIndices, int numberOfReferencePartitions, int chunkSize) { 

		String directory = Utils.getFileDirectory(file);
		String partitionsIndicesList[] = Utils.getKeyIndeicesFromString(partitionIndices.trim()); 
		String referenceIndicesList[] = Utils.getKeyIndeicesFromString(referenceIndices.trim()); 

		for(int i = 0; i < numberOfReferencePartitions; i++){ 
			//Set <String> referenceFilePartition = new HashSet<String>();

			try {  

				BufferedReader br = new BufferedReader(new FileReader(directory+"/"+referenceFile+"_p"+i+".tbl")); 
				String refLine = ""; 
				int counter = 0; 
				while ((refLine = br.readLine()) != null) {    
					String [] refLineTokens = refLine.split("\\|");  

					StringBuffer referenceKeys = new StringBuffer("");
					for(int j=0;j<referenceIndicesList.length; j++){
						referenceKeys.append(refLineTokens[Integer.parseInt(referenceIndicesList[j])]); 
						
					}
					//System.out.println("Reference Key "+referenceKeys.toString());
					referenceFilePartition.add(referenceKeys.toString()); 
					++counter;
					if(isChunkwiseMode){
						if(counter%20 == 0){ 
							//System.out.println(chunkSize+" lines has been read...."+counter);
							writePartition(file, partitionsIndicesList, i);  
							referenceFilePartition.clear(); 
							System.out.println(referenceFilePartition.size());
						}
					}

				}  
				System.out.println("Chunk of data has been sent to the file write for partition "+i+"....");

				writePartition(file, partitionsIndicesList, i); 
				referenceFilePartition.clear();  
				this.filesMap.get(i).close();
				br.close(); 

			} catch (Exception e) {
				e.printStackTrace();
				return;
			}  
		}  
		System.out.println("Reference partitioning done");

    }

	/**
	 * Write the partition corresponds to the partition in the reference table by matching the lines in the input file 
	 * with the keys stored in the hash map which represent the lines in the reference partition.
	 * 
	 * @param file the file name will be partitioned 
	 * @param referenceFilePartition the reference table partition to match with.
	 * @param partitionIndicesList the keys (indices) of the columns on which the matching operation based on 
	 * @param partitionNumber the partition number in order to know to which partition to write. 
	 */
	private void writePartition(String file, String[] partitionIndicesList, int partitionNumber) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			System.out.println(file);
			String line = "";  
			int count = 0;
			int inCount = 0;
			while((line = br.readLine()) != null){ 
				StringBuffer partitionKey = new StringBuffer("");
				String [] lineTokens = line.split("\\|");  
				inCount ++;
				int index;
				for(int i=0; i<partitionIndicesList.length; i++){ 

					index = Integer.parseInt(partitionIndicesList[i]);
					partitionKey.append(lineTokens[index]);

				}  

				// If the line match with the reference line, write it to the partition. 
				if(referenceFilePartition.contains(partitionKey.toString())){
					//System.out.println(partitionKey.toString() + " Match");
					filesMap.get(partitionNumber).write(line.getBytes()); 
					filesMap.get(partitionNumber).write(System.getProperty("line.separator").getBytes());
					count++;
				} 
			}  
			filesMap.get(partitionNumber).flush(); 
			//filesMap.get(partitionNumber).close();  
			System.out.println("Chunk of data  with size "+count+" to partition number "+partitionNumber+" has been written successfully."); 
			System.out.println(inCount);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	/**
	 * @param args contains the command line parameters 
	 * file name and path, hashing columns, number of 
	 * partitions 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) {

		DataPartitioner dataPartitioner = new DataPartitioner(); 

		Options opt = new Options();

		// Set up the command line options. 
		opt.addOption("h", false, "Print help for the partitioner tool");
		opt.addOption("f", true, "The file path to be partitioned");
		opt.addOption("m", true, "The partitioning method (hashing|reference)");
		opt.addOption("k", true, "The partitioning keys comma-separated string ex, 1,2,4");  
		opt.addOption("n", true, "The number of partitions desired"); 
		opt.addOption("rf", true, "The reference table in case of reference partitioning"); 
		opt.addOption("rk", true, "The keys in the reference table in case of reference partitioning");  
		opt.addOption("c", true, "The chunk size, if not chunk option provided, then the partitoner will work on the complete file");
        
		// Some default values. 
		String file = ""; 
		String partitionMethod = "hashing"; 
		String partitionIndices = "0";
		int numberOfPartitions = 3;  
		int chunkSize = 0;

		try { 
			// Parsing the command line
			BasicParser parser = new BasicParser();
			CommandLine cl = parser.parse(opt, args);  

			if ( cl.hasOption('h') ) {
				HelpFormatter f = new HelpFormatter();
				f.printHelp("Data Partitioner", opt);
			} 

			if(cl.hasOption('f')) {
				file = cl.getOptionValue('f');
			} else{
				System.out.println("No file entry!"); 
				return; 
			}  

			if(cl.hasOption('m')) {
				partitionMethod = cl.getOptionValue('m');
			} 

			if(cl.hasOption('k')){
				partitionIndices = cl.getOptionValue('k');

			}  

			if(cl.hasOption('n')){
				numberOfPartitions = Integer.parseInt(cl.getOptionValue('n'));
			} 
			
			if(cl.hasOption('c')) {
				chunkSize = 1000000*Integer.parseInt(cl.getOptionValue('c'));  
				dataPartitioner.setReferenceFilePartition(chunkSize, 1);
				
			} else {
				dataPartitioner.setReferenceFilePartition();
			}

			// Handle the case of reference partitioning; read additional 
			// parameters: reference file and the reference indices (columns)
			if(partitionMethod.equalsIgnoreCase("reference")){ 
				String referenceFile = cl.getOptionValue("rf"); 
				String referenceIndecis = cl.getOptionValue("rk");  

				// Check if the the reference table is partitioned or not, 
				// and return the number of partitions in case it is partitioned
				numberOfPartitions = dataPartitioner.isTablePartitoned(file, referenceFile); 
				if(numberOfPartitions == 0){
					System.out.println("Reference Table is not partitioned yet"); 
					return;
				} 
                // Create a number of files is the number of the partitions required.
				dataPartitioner.setFilesMap(file, numberOfPartitions);
				// Do the reference partitioning  
                dataPartitioner.partitionDataByReference(file, partitionIndices, referenceFile, referenceIndecis, numberOfPartitions, chunkSize);

			} else { 
				// Do the hash partitioning 
				dataPartitioner.setFilesMap(file, numberOfPartitions);
				dataPartitioner.partitionDataByHashing(file, partitionIndices); 
			} 


		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
