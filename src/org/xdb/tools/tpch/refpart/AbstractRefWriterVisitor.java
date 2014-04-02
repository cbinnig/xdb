package org.xdb.tools.tpch.refpart;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;

public abstract class AbstractRefWriterVisitor {
	protected Logger logger;

	private String outputFileName;

	private int partitionCount;
	private List<BufferedWriter> referenceWriterList;
	public AbstractRefWriterVisitor(String outputFileName, int partitionCount) {
		super();
		this.outputFileName = outputFileName;
		this.partitionCount = partitionCount;
		this.logger = XDBLog.getLogger(EnumXDBComponents.PARTITIONER);
	}

	private void addHashToFile(int partition, String value) throws IOException {
		if (referenceWriterList == null) {
			referenceWriterList = new ArrayList<BufferedWriter>(partitionCount);
			for (int i = 0; i < partitionCount; i++) {
				referenceWriterList.add(null);
			}
		}
		BufferedWriter writer = AbstractFilePartitioner.getHashWriter(referenceWriterList, outputFileName, partition,
				null, logger);
		writer.write(value);
		writer.write("\n");
	}

	public void closeReferenceWriters() throws IOException {
		if (referenceWriterList != null) {
			for (BufferedWriter writer : referenceWriterList) {
				writer.close();
			}
		}
	}

	protected abstract String generateReference(List<String> fields);

	public void visitReferenceGenerator(int partition, List<String> fields) throws IOException {
		String referenceValue = generateReference(fields);
		addHashToFile(partition, referenceValue);
	}
}
