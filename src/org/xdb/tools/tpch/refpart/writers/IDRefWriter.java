package org.xdb.tools.tpch.refpart.writers;

import java.util.List;

import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractRefWriterVisitor;

public class IDRefWriter extends AbstractRefWriterVisitor {

	public IDRefWriter(String outputFileName, int partitionCount) {
		super(outputFileName, partitionCount);
		this.logger = XDBLog.getLogger(EnumXDBComponents.PARTITIONER);
	}

	@Override
	protected String generateReference(List<String> fields) {
		return fields.get(0);
	}

}
