package org.xdb.tools.tpch.refpart.writers;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractRefWriterVisitor;
import org.xdb.tools.tpch.refpart.CombinedPartSuppKey;

public class LineItemPartSuppRefWriter extends AbstractRefWriterVisitor {

	public LineItemPartSuppRefWriter(String outputFileName, int partitionCount) {
		super(outputFileName, partitionCount);
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	@Override
	protected String generateReference(List<String> fields) {
		CombinedPartSuppKey cpsk = new CombinedPartSuppKey(Long.valueOf(fields.get(1)), Long.valueOf(fields.get(2)));
		return String.valueOf(cpsk.hashCode());
	}
}
