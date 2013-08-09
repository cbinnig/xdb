package org.xdb.metadata;

import java.io.Serializable;

public enum EnumPartitionType implements Serializable{
	ROUND_ROBIN,
	HASH,
	NO_PARTITION,
	REFERENCE,
	REVERSE_REFERENCE;


	/**
	 * This method checks whether two partition types are compatible with each other or not.
	 * Under two conditions two partition types are compatible. 
	 * <br/>
	 * 1. If both are the same type (HASH, ROUND_ROBIN, ...)
	 * <br/>
	 * 2. If one is referred to another.
	 * @param partitionType The partition type to be compared with.
	 * @return
	 */
	public boolean isCompatibleWith(EnumPartitionType partitionType) {
		if (this.equals(partitionType)
				|| (this.isNonReferencePartition() && !partitionType.isNonReferencePartition())
				|| (!this.isNonReferencePartition() && partitionType.isNonReferencePartition()))
			return true;
		else return false;
				
	}
	
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
}
