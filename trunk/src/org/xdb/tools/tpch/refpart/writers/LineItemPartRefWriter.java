package org.xdb.tools.tpch.refpart.writers;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractRefWriterVisitor;

public class LineItemPartRefWriter extends AbstractRefWriterVisitor {

	public LineItemPartRefWriter(String outputFileName, int partitionCount) {
		super(outputFileName, partitionCount);
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	@Override
	protected String generateReference(List<String> fields) {
		return String.valueOf(fields.get(2));
	}
}