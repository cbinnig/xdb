package org.xdb.metadata;

import java.io.Serializable;

public enum EnumPartitionType implements Serializable{
	NO_PARTITION,
	HASH,
	REFERENCE;
	
	public boolean isReference(){
		if (this.equals(EnumPartitionType.REFERENCE))
			return true;
		else return false;
	}
	
	public boolean isHash(){
		if (this.equals(EnumPartitionType.HASH))
			return true;
		else return false;
	}
	
	public static EnumPartitionType getValue(String value){
		if(value.equalsIgnoreCase("HASH"))
			return HASH;
		else if(value.equalsIgnoreCase("REF"))
			return REFERENCE;
		else 
			return NO_PARTITION;
	}
}
