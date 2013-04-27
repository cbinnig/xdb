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

public class DataPartitioner {

	// Used to store the buffers for every partitions, so we use
	// them later when writing to the partitions file.
	private Map<Integer, BufferedOutputStream> filesMap = new HashMap<Integer, BufferedOutputStream>();
	private Set<String> referenceKeys = new HashSet<String>(CHUNK_SIZE);

	private static int CHUNK_SIZE = 100000;
	private static boolean CHUNK_MODE = false;
	private static String NEW_LINE = System.getProperty("line.separator");

	// constructors
	public DataPartitioner() {
	}

	// getters and setters
	public void setReferenceKeys(int size, int loadFactor) {
		referenceKeys = new HashSet<String>(size, loadFactor);
		CHUNK_MODE = true;
		CHUNK_SIZE = size;
	}

	/**
	 * Create the buffer for the partition files, store them in a hash map with
	 * the number of the partition as a key of the buffer.
	 * 
	 * @param filesName
	 * @param numberofPartitions
	 */
	public void setFilesMap(String fileName, int numberofPartitions)
			throws Exception {

		String fileType = Utils.getFileType(fileName);

		// delete old partitions if they are existing, in case of
		// re-partitioning
		Utils.deleteOldPartitions(fileName);

		for (int i = 0; i < numberofPartitions; i++) {
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
	private void partitionDataByHashing(String fileName, String indices)
			throws Exception {

		// Return the indices in an array format
		Integer[] partitionIndices = Utils.getKeyIndicesFromString(indices
				.trim());

		int hash;
		int partitionNumber = 0;
		int numberOfPartitions = this.filesMap.size();

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = "";
		while ((line = br.readLine()) != null) {
			hash = Utils.calculateHash(line, partitionIndices);
			partitionNumber = hash % numberOfPartitions;
			filesMap.get(partitionNumber).write(line.getBytes());
			filesMap.get(partitionNumber).write(NEW_LINE.getBytes());
		}
		for (int i = 0; i < filesMap.size(); i++) {
			filesMap.get(i).flush();
			filesMap.get(i).close();
			System.out.println("Partition number " + i + " has been written.");
		}
		br.close();
		System.out.println("Hash partitioning done!" + referenceKeys.size());

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

			BufferedReader br = new BufferedReader(new FileReader(directory
					+ "/" + referenceFile + "_p" + i + ".tbl"));
			String refLine = "";
			int counter = 0;
			while ((refLine = br.readLine()) != null) {
				String[] refLineTokens = refLine.split("\\|");

				StringBuffer referenceKey = new StringBuffer();
				for (int j = 0; j < referenceIndicesList.length; j++) {
					referenceKey.append(refLineTokens[referenceIndicesList[j]]);

				}
				referenceKeys.add(referenceKey.toString());
				++counter;
				if (CHUNK_MODE && counter % CHUNK_SIZE == 0) {
					writePartition(file, partitionsIndicesList, i);
					referenceKeys.clear();
				}

			}

			writePartition(file, partitionsIndicesList, i);
			referenceKeys.clear();
			this.filesMap.get(i).close();
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
			while ((line = br.readLine()) != null) {
				StringBuffer partitionKey = new StringBuffer("");
				String[] lineTokens = line.split("\\|");
				int index;
				for (int i = 0; i < partitionIndicesList.length; i++) {

					index = partitionIndicesList[i];
					partitionKey.append(lineTokens[index]);

				}

				// If the line match with the reference line, write it to the
				// partition.
				if (referenceKeys.contains(partitionKey.toString())) {
					filesMap.get(partitionNumber).write(line.getBytes());
					filesMap.get(partitionNumber).write(NEW_LINE.getBytes());
					count++;
				}
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
		opt.addOption("k", true, "The partitioning keys (e.g., 1,2,4)");
		opt.addOption("n", true, "The number of partitions");
		opt.addOption("rf", true,
				"The reference table (reference partitioning)");
		opt.addOption("rk", true,
				"The keys in the reference table (reference partitioning)");
		opt.addOption("c", true, "The chunk size (optional)");

		// Some default values.
		String file = "";
		String partitionMethod;
		String partitionIndices;
		int numberOfPartitions = 3;
		int chunkSize = 0;

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
				dataPartitioner.setReferenceKeys(chunkSize, 1);
			}

			// reference partitioning: additional parameters
			if (partitionMethod.equalsIgnoreCase("reference")) {
				String referenceFile = cl.getOptionValue("rf");
				String referenceIndecis = cl.getOptionValue("rk");

				// Check if the the reference table is partitioned
				numberOfPartitions = dataPartitioner.isTablePartitoned(file,
						referenceFile);
				if (numberOfPartitions == 0) {
					System.out
							.println("Reference Table is not partitioned yet");
					return;
				}
				// Create a output files
				dataPartitioner.setFilesMap(file, numberOfPartitions);
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

				dataPartitioner.setFilesMap(file, numberOfPartitions);
				dataPartitioner.partitionDataByHashing(file, partitionIndices);
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
