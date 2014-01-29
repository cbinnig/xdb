package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.metadata.EnumPartitionType;
import org.xdb.utils.Identifier;

/**
 * Describes the partitioning properties of an operator result
 * 
 * @author cbinnig
 * 
 */
public class PartitionDesc implements Serializable {
	private static final long serialVersionUID = 1097865181383599610L;

	private List<TokenAttribute> partAttributes = new LinkedList<TokenAttribute>();
	private String table = null;
	private String refTable = null;
	private EnumPartitionType partType = EnumPartitionType.NO_PARTITION;
	private int partCount = 1;

	// constructors
	public PartitionDesc() {
		// do nothing
	}

	public PartitionDesc(EnumPartitionType partType, int partCount) {
		this.partType = partType;
		this.partCount = partCount;
	}

	public PartitionDesc(PartitionDesc toCopy) {
		this.partType = toCopy.partType;
		this.partCount = toCopy.partCount;

		for (TokenAttribute partAtt : toCopy.partAttributes) {
			TokenAttribute newPartAtt = new TokenAttribute(partAtt);
			this.partAttributes.add(newPartAtt);
		}

		this.table = toCopy.table;
		this.refTable = toCopy.refTable;
	}

	// getters and setters
	public void addPartAttributes(TokenAttribute partAttribute) {
		this.partAttributes.add(partAttribute);
	}

	public void setPartitionType(EnumPartitionType partType) {
		this.partType = partType;
	}

	public EnumPartitionType getPartitionType() {
		return this.partType;
	}

	public void setPartCount(int partCount) {
		this.partCount = partCount;
	}

	public int getPartitionCount() {
		return this.partCount;
	}

	public void setTableName(String table) {
		this.table = table;
	}

	public void setRefTableName(String refTable) {
		this.refTable = refTable;
	}

	public boolean isPartitioned() {
		return !this.partType.equals(EnumPartitionType.NO_PARTITION);
	}

	// methods
	public boolean isCompatible(PartitionDesc partDesc) {
		// true if one input is not partitioned
		if (partDesc.partType.isNotPartitioned()
				|| this.partType.isNotPartitioned())
			return true;

		// false if one of the following checks fail
		if (partDesc.partCount != this.partCount)
			return false;

		// check compatibility of partition types
		if (this.partType.isHash() && partDesc.partType.isHash()) {
			if (!this.partAttributes.equals(partDesc.partAttributes))
				return false;
		} else if (this.partType.isReference() && partDesc.partType.isHash()) {
			if (!this.refTable.equals(partDesc.table))
				return false;
		} else if (partDesc.partType.isReference() && this.partType.isHash()) {
			if (!partDesc.refTable.equals(this.table))
				return false;
		} else if (this.partType.isReference()
				&& partDesc.partType.isReference()) {
			if (!this.refTable.equals(partDesc.table)
					&& !partDesc.refTable.equals(this.table))
				return false;
		}
		// else: true
		return true;
	}

	public boolean isCompatible(TokenAttribute partAtt) {
		if (this.partAttributes.size() > 0
				&& !this.partAttributes.get(0).equals(partAtt))
			return false;

		return true;
	}

	public void renameTable(Identifier newOpId) {
		for (TokenAttribute partAtt : this.partAttributes) {
			partAtt.setTable(newOpId.toString());
		}
	}

	public String getRepartDDL() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(AbstractToken.PARTITION);
		buffer.append(AbstractToken.BLANK);
		buffer.append(AbstractToken.BY);
		buffer.append(AbstractToken.BLANK);
		buffer.append(this.partType);
		buffer.append(AbstractToken.LBRACE);
		int tAttCnt = 0;
		for (TokenAttribute tAtt : this.partAttributes) {
			buffer.append(tAtt.getName().toSqlString());
			if (tAttCnt > 0)
				buffer.append(AbstractToken.COMMA);
			tAttCnt++;
		}
		buffer.append(AbstractToken.RBRACE);
		buffer.append(AbstractToken.BLANK);
		buffer.append(AbstractToken.PARTITIONS);
		buffer.append(AbstractToken.BLANK);
		buffer.append(this.partCount);

		return buffer.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		PartitionDesc partDesc = (PartitionDesc) o;
		if (partDesc.partCount != this.partCount)
			return false;

		for (TokenAttribute partAtt : partDesc.partAttributes) {
			if (!this.partAttributes.contains(partAtt))
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return this.getRepartDDL();
	}

	@Override
	public int hashCode() {
		return this.partType.hashCode() % this.partCount;
	}
}
