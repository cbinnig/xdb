package org.xdb.tools.partitioner;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;

/**
 * Usage
 * The partitioner
 * tool has two options: Hashing partition and Reference Partition. 
 * 
 * 1- Hashing Partitioning. 
 * Command line parameters: -f {file name (path)} -m hashing -k 0,1,.. -n {positive number} 
 * 
 * 2- Reference Partitioning 
 * Command line parameters: -f {file name (path)} -m reference -k 0,1,.. -rf {reference file (only table name)} -rk {reference keys}
 * 
 * For more details about the command options; 
 * Command line parameter: -h
 *  
 * @author cbinnig
 *
 */
public class DataPartitioner {

	// Used to store the buffers for every partitions, so we use
	// them later when writing to the partitions file.
	private Map<Integer, BufferedOutputStream> filesMap;
	private Set<String> referenceKeys;
	private BitSet writtenLines;

	private int chunkSize = 100000; 
	private boolean chunkMode = false;
	
	private int partitionNumber; 
	private boolean partialPatitioningMode = false; 
	
	private static final String NEW_LINE = System.getProperty("line.separator");
	private static final String CSV_SEPARATOR = "\\|";

	// constructors
	public DataPartitioner() {
		this.filesMap = new HashMap<Integer, BufferedOutputStream>();
		this.referenceKeys = new HashSet<String>(chunkSize); 
		this.writtenLines = new BitSet(chunkSize);

	} 
	
	/**
	 * @return the partialPatitioningMode
	 */
	public boolean isPartialPatitioningMode() {
		return partialPatitioningMode;
	}

	/**
	 * @param partialPatitioningMode the partialPatitioningMode to set
	 */
	public void setPartialPatitioningMode(boolean partialPatitioningMode, int partitionNumber) {
		this.partialPatitioningMode = partialPatitioningMode;  
		this.partitionNumber = partitionNumber; 
		System.out.println("The partial partitoning is set and the partion required is: "+this.partitionNumber);
	}

	// methods
	public void initChunking(int size, int loadFactor) {
		referenceKeys = new HashSet<String>(size, loadFactor); 
		writtenLines = new BitSet(size);
		chunkMode = true;
		chunkSize = size;
	}

	/**
	 * Create the buffer for the partition files, store them in a hash map with
	 * the number of the partition as a key of the buffer.
	 * 
	 * @param filesName
	 * @param numberofPartitions
	 */
	public void initFilesMap(String fileName, int numberofPartitions)
			throws Exception {

		String fileType = Utils.getFileType(fileName);
    
		// delete old partitions if they are existing, in case of
		// re-partitioning
		Utils.deleteOldPartitions(fileName, this.partialPatitioningMode, this.partitionNumber);

		for (int i = 0; i < numberofPartitions; i++) {  
			// Check if the partial partitioning is set 
			// so one partition is created at once. 
			if(isPartialPatitioningMode() && i != this.partitionNumber) {
				System.out.println("Partition number: "+i+ " excluded from the partitoning");
				continue; 
			}
			// Adding the partition number as a part of the file name.
			File file = new File(Utils.getFileDirectory(fileName) + "/"
					+ Utils.getFileName(fileName) + "_p" + i + "." + fileType);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				// Create a buffer stream for every file, and store it
				// in a hash map.
				FileOutputStream fos = new FileOutputStream(file, true);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				this.filesMap.put(i, bos);
			}
		}

	}

	/**
	 * Partition the data file using the hashing method. The partition function
	 * calculate the hash code of a user-defined set of columns % the number of
	 * the partitions desired.
	 * 
	 * @param fileName
	 * @param indices
	 */
	private void partitionDataByHashing(String fileName, String indices, int numberOfPartitions, boolean isIntHashing)
			throws Exception {

		// Return the indices in an array format
		Integer[] partitionIndices = Utils.getKeyIndicesFromString(indices
				.trim());
		int hash = 0;
		int partitionNumber = 0;
		//int numberOfPartitions = this.filesMap.size();
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String line = "";
		while ((line = br.readLine()) != null) { 
			if(isIntHashing)
			    hash = Utils.calculateIntHash(line, partitionIndices); 
			else 
				hash = Utils.calculateHash(line, partitionIndices);
			
			partitionNumber = (hash % numberOfPartitions + numberOfPartitions) % numberOfPartitions; 

			// Check if the partial partitioning is set 
			// so one partition is written at once.  
			if(isPartialPatitioningMode() && partitionNumber != this.partitionNumber) 
			    continue; 
			filesMap.get(partitionNumber).write(line.getBytes());
			filesMap.get(partitionNumber).write(NEW_LINE.getBytes());
		}
		for (int i = 0; i < filesMap.size(); i++) { 
			// Check if the partial partitioning is set 
			// so one partition is written at once. 
			if(isPartialPatitioningMode() && i != this.partitionNumber) 
			    continue; 
			filesMap.get(i).flush();
			filesMap.get(i).close();
			System.out.println("Partition number " + i + " has been written.");
		}
		br.close();
		System.out.println("Hash partitioning done!");

	}

	/**
	 * Check if the reference table (file) is partitioned or not before starting
	 * the reference partitioning.
	 * 
	 * @param file
	 *            the path of the file will be partitioned.
	 * @param table
	 *            the name of the table will be checked.
	 * 
	 * @return an integer contains the number of partitions found for the
	 *         specified table, 0 if the table is not partitioned
	 */
	private int isTablePartitoned(String file, final String table) {

		String directory = Utils.getFileDirectory(file);
		File dir = new File(directory);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith(table + "_p");
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
	 * prepare what is required to make the reference partition. Read the
	 * reference file partitions and store them in a map, extract the partition
	 * keys and call the function writePartition.
	 * 
	 * @param file
	 *            the input file will be partitioned
	 * @param partitionIndices
	 *            the indices of the partition columns in the input table
	 * @param referenceFile
	 *            the reference file (table) name (without path)
	 * @param referenceIndices
	 *            the indices of the partition columns in the reference table.
	 * @param numberOfReferencePartitions
	 *            the number of partitions desired. (the number of the reference
	 *            file partitions)
	 * @throws Exception
	 * 
	 */
	private void partitionDataByReference(String file, String partitionIndices,
			String referenceFile, String referenceIndices,
			int numberOfReferencePartitions, int chunkSize) throws Exception {

		String directory = Utils.getFileDirectory(file);
		Integer partitionsIndicesList[] = Utils
				.getKeyIndicesFromString(partitionIndices.trim());
		Integer referenceIndicesList[] = Utils
				.getKeyIndicesFromString(referenceIndices.trim());

		for (int i = 0; i < numberOfReferencePartitions; i++) {
			// Check if the partial partitioning is set 
			// so one partition is written at once. 
			if(isPartialPatitioningMode() && i != this.partitionNumber) {
			     continue; 
			}
			BufferedReader br = new BufferedReader(new FileReader(directory
					+ "/" + referenceFile + "_p" + i + ".tbl"));
			String refLine = "";
			int counter = 0;
			while ((refLine = br.readLine()) != null) {
				String[] refLineTokens = refLine.split(CSV_SEPARATOR);

				StringBuffer referenceKey = new StringBuffer();
				for (int j = 0; j < referenceIndicesList.length; j++) {
					referenceKey.append(refLineTokens[referenceIndicesList[j]].trim());

				} 	 
			    referenceKeys.add(referenceKey.toString().trim());
				++counter;
				if (chunkMode && (counter % chunkSize) == 0) {
					writePartition(file, partitionsIndicesList, i);
					referenceKeys.clear();
				}

			}
			writePartition(file, partitionsIndicesList, i);
			referenceKeys.clear();
			this.filesMap.get(i).close();
			writtenLines.clear();
			br.close();
		}
		System.out.println("Reference partitioning done!");

	}

	/**
	 * Write the partition corresponds to the partition in the reference table
	 * by matching the lines in the input file with the keys stored in the hash
	 * map which represent the lines in the reference partition.
	 * 
	 * @param file
	 *            the file name will be partitioned
	 * @param referenceKeys
	 *            the reference table partition to match with.
	 * @param partitionIndicesList
	 *            the keys (indices) of the columns on which the matching
	 *            operation based on
	 * @param partitionNumber
	 *            the partition number in order to know to which partition to
	 *            write.
	 */
	private void writePartition(String file, Integer[] partitionIndicesList,
			int partitionNumber) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			int count = 0;
			int linesCounter =0;
			while ((line = br.readLine()) != null) {  	
				// Check if the line has been written already! 
				if(writtenLines.get(linesCounter)){
					linesCounter++;
					continue;
				}
				StringBuffer partitionKey = new StringBuffer();
				String[] lineTokens = line.split(CSV_SEPARATOR);
				int index;
				for (int i = 0; i < partitionIndicesList.length; i++) {

					index = partitionIndicesList[i];
					partitionKey.append(lineTokens[index].trim());

				}

				// If the line match with the reference line, write it to the
				// partition.
				if (referenceKeys.contains(partitionKey.toString().trim())) {
					filesMap.get(partitionNumber).write(line.getBytes());
					filesMap.get(partitionNumber).write(NEW_LINE.getBytes()); 
					writtenLines.set(linesCounter);
					count++;
				}  
				linesCounter++;
			}
			filesMap.get(partitionNumber).flush();
			System.out.println("Chunk of size " + count
					+ " has been written to partition number "
					+ partitionNumber + " successfully.");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 *            contains the command line parameters file name and path,
	 *            hashing columns, number of partitions
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) {

		DataPartitioner dataPartitioner = new DataPartitioner();

		Options opt = new Options();

		// Set up the command line options.
		opt.addOption("h", false, "Print help for the partitioner tool");
		opt.addOption("f", true, "The file to be partitioned");
		opt.addOption("m", true, "The partitioning method (hashing|reference)");
		opt.addOption("k", true, "The indexes of the key columns (e.g., 1,2,4)");
		opt.addOption("n", true, "The number of partitions");
		opt.addOption("rf", true, "The referenced table name (for reference partitioning)");
		opt.addOption("rk", true, "The key column indexes in the reference table (for reference partitioning)");
		opt.addOption("c", true, "The chunk size (optional)"); 
		opt.addOption("p", true, "The partition's number (optional)"); 
		opt.addOption("t", true, "The type of the key (int|String)"); 

		// Some default values.
		String file = "";
		String partitionMethod;
		String partitionIndices;
		int numberOfPartitions = 3;
		int chunkSize = 0; 
		int partitionNumber = 0; 
		String keyType = "string"; 
		boolean isIntHashing = false;

		try {
			// Parsing the command line
			BasicParser parser = new BasicParser();
			CommandLine cl = parser.parse(opt, args);
			HelpFormatter f = new HelpFormatter();

			if (cl.hasOption('h')) {
				f.printHelp("./partitionerData.sh", opt);
			}

			if (cl.hasOption('f')) {
				file = cl.getOptionValue('f');
			} else {
				System.out.println("Option -f must be defined!");
				f.printHelp("./partitionerData.sh", opt);
				return;
			}

			if (cl.hasOption('m')) {
				partitionMethod = cl.getOptionValue('m');
			} else {
				System.out.println("Option -m must be defined!");
				f.printHelp("./partitionerData.sh", opt);
				return;
			}

			if (cl.hasOption('k')) {
				partitionIndices = cl.getOptionValue('k');
			} else {
				System.out.println("Option -k must be defined!");
				f.printHelp("./partitionerData.sh", opt);
				return;
			}

			if (cl.hasOption('c')) {
				chunkSize = 1000000 * Integer.parseInt(cl.getOptionValue('c'));
				dataPartitioner.initChunking(chunkSize, 1);
			}
			
			if(cl.hasOption('p')){
				partitionNumber = Integer.parseInt(cl.getOptionValue('p')); 
				dataPartitioner.setPartialPatitioningMode(true, partitionNumber);
			} 
			
			if(cl.hasOption('t')){
				keyType = cl.getOptionValue('t'); 
				if(keyType.equalsIgnoreCase("int")) 
					isIntHashing = true;
			}

			// reference partitioning: additional parameters
			if (partitionMethod.equalsIgnoreCase("reference")) {
				String referenceFile = cl.getOptionValue("rf");
				String referenceIndecis = cl.getOptionValue("rk");

				// Check if the the reference table is partitioned
				//numberOfPartitions = dataPartitioner.isTablePartitoned(file,
				//		referenceFile); 
				numberOfPartitions = Integer.parseInt(cl.getOptionValue('n'));
				
				if (numberOfPartitions == 0) {
					System.out
							.println("Reference Table is not partitioned yet");
					return;
				}
				// Create a output files
				dataPartitioner.initFilesMap(file, numberOfPartitions);
				// Do the reference partitioning
				dataPartitioner.partitionDataByReference(file,
						partitionIndices, referenceFile, referenceIndecis,
						numberOfPartitions, chunkSize);

			}
			// hash partitioning
			else if (partitionMethod.equalsIgnoreCase("hashing")) {
				if (cl.hasOption('n')) {
					numberOfPartitions = Integer.parseInt(cl
							.getOptionValue('n'));
				} else {
					System.out.println("Option -n must be defined!");
					f.printHelp("./partitionerData.sh", opt);
					return;
				}

				dataPartitioner.initFilesMap(file, numberOfPartitions);
				dataPartitioner.partitionDataByHashing(file, partitionIndices, numberOfPartitions, isIntHashing);
			} else {
				System.out.println("Unknown partitioning method: "
						+ partitionMethod + "!");
				f.printHelp("./partitionerData.sh", opt);
				return;
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}


}
