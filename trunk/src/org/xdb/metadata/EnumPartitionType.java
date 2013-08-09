package org.xdb.metadata;

import java.io.Serializable;

public enum EnumPartitionType implements Serializable{
	NO_PARTITION,
	HASH,
	REFERENCE,
	REVERSE_REFERENCE;
	
	public boolean isNonReferencePartition(){
		if (!this.equals(EnumPartitionType.REFERENCE)
				&& !this.equals(EnumPartitionType.REVERSE_REFERENCE))
			return true;
		else return false;
	}
	
	public boolean isReferencePartition(){
		if (this.equals(EnumPartitionType.REFERENCE)
				|| this.equals(EnumPartitionType.REVERSE_REFERENCE))
			return true;
		else return false;
	}
	
	public static EnumPartitionType getValue(String value){
		if(value.equalsIgnoreCase("HASH"))
			return HASH;
		else if(value.equalsIgnoreCase("REF"))
			return REFERENCE;
		else if(value.equalsIgnoreCase("RREF"))
			return REVERSE_REFERENCE;
		else 
			return NO_PARTITION;
	}
}
