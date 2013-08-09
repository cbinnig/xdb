package org.xdb.funsql.compile.operator;

import java.io.Serializable;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.metadata.EnumPartitionType;

public class PartitionDesc implements Serializable{
	private static final long serialVersionUID = 1097865181383599610L;
	
	private TokenAttribute partAttributes;
	private EnumPartitionType partType;
	private int partNumber;
	
	//getters and setters
	public TokenAttribute getPartAttributes() {
		return partAttributes;
	}
	
	public void setPartAttributes(TokenAttribute partAttributes) {
		this.partAttributes = partAttributes;
	}
	
	public EnumPartitionType getPartType() {
		return partType;
	}
	
	public void setPartType(EnumPartitionType partType) {
		this.partType = partType;
	}
	
	public int getPartNumber() {
		return partNumber;
	}
	
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
}
