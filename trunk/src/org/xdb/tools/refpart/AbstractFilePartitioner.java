package org.xdb.tools.refpart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.tools.refpart.DefRefPart.FILE_MODE;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;
import org.xdb.tools.refpart.util.MurmurHash;

public abstract class AbstractFilePartitioner {
	protected static void createDebug(Logger logger, String text) {
		logger.log(Level.INFO, text);
	}

	private static BufferedWriter createWriter(DefRefPart.FILE_MODE mode, String fileDir, String fileName,
			int partitionCounter, Logger logger) throws IOException {
		BufferedWriter returnValue = null;
		String fileLocation = null;
		if (mode == FILE_MODE.HASH) {
			fileLocation = fileName + DefRefPart.FILENAME_DELIMITER + partitionCounter + DefRefPart.FILENAME_DELIMITER
					+ DefRefPart.HASH_FILE_EXTENSTION;
		} else {
			fileLocation = fileDir + partitionCounter + "/" + fileName + DefRefPart.FILENAME_DELIMITER
					+ DefRefPart.SQL_FILE_EXTENSTION;
			String dirName = fileDir + "/" + partitionCounter;
			File dir = new File(dirName);
			if (!dir.exists()) {
				dir.mkdirs();
				createDebug(logger, "Creating directory: " + dirName);
			}
		}

		File file = new File(fileLocation);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		returnValue = new BufferedWriter(fw);
		return returnValue;
	}
	static BufferedWriter getHashWriter(List<BufferedWriter> writerList, String fileName, int partition,
			String initData, Logger logger) throws IOException {
		return getWriter(FILE_MODE.HASH, writerList, "", fileName, partition, initData, logger);
	}
	static BufferedWriter getWriter(FILE_MODE mode, List<BufferedWriter> writerList, String fileDir, String fileName,
			int partition, String initData, Logger logger) throws IOException {
		BufferedWriter returnValue = null;
		returnValue = writerList.get(partition);
		if (returnValue == null) {
			returnValue = createWriter(mode, fileDir, fileName, partition, logger);
			if (initData != null) {
				returnValue.write(initData);
			}
			writerList.set(partition, returnValue);
		}

		return returnValue;
	}
	private static BufferedReader openFile(String fileLocation) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(fileLocation));
		return br;
	}
	private static HashSet<Integer> readHashFile(String hashFile, int partitionCounter) throws IOException {
		HashSet<Integer> hashList = new HashSet<Integer>();
		String hashFileLocation = hashFile + DefRefPart.FILENAME_DELIMITER + partitionCounter
				+ DefRefPart.FILENAME_DELIMITER + DefRefPart.HASH_FILE_EXTENSTION;
		BufferedReader br = openFile(hashFileLocation);
		String line;
		while ((line = br.readLine()) != null) {
			if (!line.isEmpty()) {
				try {
					hashList.add(Integer.valueOf(line));
				} catch (NumberFormatException e) {
					// don't add the line
				}
			}
		}
		br.close();
		return hashList;
	}
	protected int columnCount = -1;
	private String inputFile;
	protected String inputHashFile;
	protected Logger logger;
	private String outputFileDir;
	private String outputFileName;
	protected String outputHashFile;

	private List<Integer> outputWriterCounterList;

	private List<BufferedWriter> outputWriterList;

	protected int partitionCount;

	private Collection<AbstractRefWriterVisitor> referenceVisitorList;

	protected String tableName;

	private WORK_MODE workMode;

	public AbstractFilePartitioner(WORK_MODE workMode, int partitionCount, String inputDir, String outputDir,
			String tempDir, String fileName, String tableName) {
		super();
		this.workMode = workMode;
		this.partitionCount = partitionCount;
		this.inputFile = inputDir + fileName + DefRefPart.FILENAME_DELIMITER + DefRefPart.TPCH_FILE_EXTENSTION;
		this.outputFileDir = outputDir;
		this.outputFileName = fileName;
		this.outputHashFile = tempDir + fileName;
		this.tableName = tableName;
		this.referenceVisitorList = new HashSet<AbstractRefWriterVisitor>();
		defineReferenceVisitors();
	}

	protected void addReferenceVisitor(AbstractRefWriterVisitor visitor) {
		this.referenceVisitorList.add(visitor);
	}

	abstract protected String buildSQLRow(List<String> fields);

	private int calculatePartition(int hashValue) {
		return hashValue % partitionCount;
	}

	private void closeOutputWriters() throws IOException {
		if (outputWriterList != null) {
			String sqlEnd = getSQLEnd();
			for (BufferedWriter writer : outputWriterList) {
				if (writer != null){
					if (sqlEnd != null && !sqlEnd.isEmpty()) {
						writer.write(";\n");
						writer.write(sqlEnd);
					}
					writer.close();
				}
			}
			outputWriterList.clear();
			outputWriterList = null;
		}
	}

	private void closeReferenceWriters() throws IOException {
		for (AbstractRefWriterVisitor visitor : referenceVisitorList) {
			visitor.closeReferenceWriters();
		}
	}

	protected Error createError(Exception e) {
		e.printStackTrace();

		String[] args = { e.toString() };
		Error err = new Error(EnumError.CLIENT_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}

	abstract protected void defineReferenceVisitors();

	public final void execute() throws IOException {
		createDebug(logger, "Start with input file: " + inputFile);
		if (columnCount == -1) {
			throw new RuntimeException("Please define the number of columns to process!");
		}
		switch (workMode) {
		case FILE:
			executeFilePartition();
			break;
		case HASH:
			executeHashPartition();
			break;
		case REPLICATE:
			executeReplication();
			break;
		default:
			break;
		}
		closeReferenceWriters();
		closeOutputWriters();
		createDebug(logger, "It was fun while it lastet!");
	}

	private void executeFilePartition() throws IOException {
		for (int partition = 0; partition < partitionCount; partition++) {
			createDebug(logger, "Start with partition: " + partition);
			HashSet<Integer> hashList = readHashFile(inputHashFile, partition);
			processInputFile(inputFile, hashList, partition);
			closeOutputWriters();
		}
	}

	private void executeHashPartition() throws IOException {
		createDebug(logger, "Using an available column to hash.");
		processInputFile(inputFile);
	}

	private void executeReferenceVisitors(int partition, List<String> fields) throws IOException {
		if (referenceVisitorList != null) {
			for (AbstractRefWriterVisitor visitor : referenceVisitorList) {
				visitor.visitReferenceGenerator(partition, fields);
			}
		}
	}

	private void executeReplication() throws IOException {
		processInputFile(inputFile);
	}

	private List<String> explodeInputString(String inputString) {
		ArrayList<String> returnValue = new ArrayList<String>();

		StringTokenizer tokenizer = new StringTokenizer(inputString, DefRefPart.COLUMN_DELIMITER);
		while (tokenizer.hasMoreTokens()) {
			returnValue.add(tokenizer.nextToken());
		}

		return returnValue;
	}

	protected int getHashValue(List<String> fields) {
		return MurmurHash.hash32(fields.get(0));
	}

	protected String getIDValue(List<String> fields) {
		return fields.get(0);
	}

	abstract protected String getSQLEnd();

	abstract protected String getSQLStart();

	private void processInputFile(String fileName) throws IOException {
		this.processInputFile(fileName, null, -1);
	}

	private void processInputFile(String fileName, HashSet<Integer> hashList, int hashListValue) throws IOException {
		String line;
		int lineCounter = 0;
		BufferedReader br = openFile(fileName);
		while ((line = br.readLine()) != null) {
			if (!line.isEmpty()) {
				List<String> fields = explodeInputString(line);
				if (columnCount == fields.size()) {
					if (lineCounter % 100000 == 0) {
						createDebug(logger, "Processing line " + lineCounter);
					}
					processLine(fields, hashList, hashListValue);
					lineCounter++;
				}
			}
		}
		br.close();
	}

	private void processLine(List<String> fields, HashSet<Integer> hashList, int hashListValue) throws IOException {
		int hashValue = getHashValue(fields);
		int partition = -1;
		boolean write = true;
		if (workMode == WORK_MODE.FILE) {
			if (hashList.contains(hashValue)) {
				partition = hashListValue;
			} else {
				write = false;
			}
		} else {
			partition = calculatePartition(hashValue);
		}
		if (write) {
			writeLine(fields, partition);
		}
	}

	private void writeLine(List<String> fields, int partition) throws IOException {
		String sql = buildSQLRow(fields);
		if (workMode == WORK_MODE.REPLICATE) {
			for (int partitionCounter = 0; partitionCounter < partitionCount; partitionCounter++) {
				writeSQL(partitionCounter, sql);
			}
		} else {
			writeSQL(partition, sql);
			executeReferenceVisitors(partition, fields);
		}
	}

	private void writeSQL(int partition, String sql) throws IOException {
		if (outputWriterList == null) {
			outputWriterList = new ArrayList<BufferedWriter>(partitionCount);
			outputWriterCounterList = new ArrayList<Integer>(partitionCount);
			for (int i = 0; i < partitionCount; i++) {
				outputWriterList.add(null);
				outputWriterCounterList.add(Integer.valueOf(0));
			}
		}

		BufferedWriter br = getWriter(FILE_MODE.SQL, outputWriterList, outputFileDir, outputFileName, partition,
				getSQLStart(), logger);
		Integer sqlCounter = outputWriterCounterList.get(partition);
		if (sqlCounter != 0) {
			if (sqlCounter >= DefRefPart.MAX_INSERT_COUNT) {
				br.write(";\n INSERT INTO " + tableName + " VALUES ");
				sqlCounter = Integer.valueOf(1);
			} else {
				br.write(",");
				br.write("\n");
				sqlCounter++;
			}

		} else {
			sqlCounter++;
		}
		outputWriterCounterList.set(partition, sqlCounter);
		br.write(sql);
	}
}
