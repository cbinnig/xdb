package org.xdb.metadata;

import java.io.Serializable;

public enum EnumPartitionType implements Serializable{
	NO_PARTITION,
	HASH,
	REF,
	RREF;
	
	public boolean isReference(){
		if (this.equals(EnumPartitionType.REF))
			return true;
		else if (this.equals(EnumPartitionType.RREF))
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
			return REF;
		else if(value.equalsIgnoreCase("RREF"))
			return RREF;
		else 
			return NO_PARTITION;
	}
	
	public static EnumPartitionType getMaterializeType(EnumPartitionType type){
		return HASH;
	}
	
	public boolean isCompatible(EnumPartitionType type){
		return true;
	}
}
