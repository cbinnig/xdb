package org.xdb.funsql.parallelize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * This class encapsulates the partition information for every operator, needed
 * to parallelize Plans
 * 
 * @author Alexander.C Mueller, Erfan Zamanian
 * 
 */
public class PartitionInfo implements Serializable, Cloneable {

	private static final long serialVersionUID = 7175327340663407036L;
	
	private List<PartitionAttributeSet> partitionAttributeSet = new ArrayList<PartitionAttributeSet>();
	private EnumPartitionType partitionType;
	private int parts;

	public PartitionInfo(List<PartitionAttributeSet> partitionAttributeSet,
			EnumPartitionType partitionType, int parts) {
		super();
		this.partitionAttributeSet = partitionAttributeSet;
		this.partitionType = partitionType;
		this.parts = parts;
	}
	

	public PartitionInfo(EnumPartitionType partitionType, int size) {
		super();
		this.partitionType = partitionType;
		this.parts = size;
	}
	
	public PartitionInfo(PartitionInfo toCopy){
		
		this.partitionType = toCopy.partitionType;
		this.parts = toCopy.parts;
		
		List<PartitionAttributeSet> clonepartitionAttributes = new ArrayList<PartitionAttributeSet>();
		
		for (PartitionAttributeSet attributeSet : toCopy.partitionAttributeSet) {
			clonepartitionAttributes.add(attributeSet.deepCopy());
		}
		this.partitionAttributeSet = clonepartitionAttributes;
	}

	/**
	 * Constructor when a operator is not Partitioned, can be used for example
	 * for a table that is not partioned
	 * 
	 * @param noPartition
	 */
	public PartitionInfo(EnumPartitionType noPartition) {
		this.partitionType = noPartition;
	}

	// getters + setters

	public List<PartitionAttributeSet> getPartitionAttributeSet() {
		return partitionAttributeSet;
	}

	public void setPartitionAttributeSet(List<PartitionAttributeSet> partitionAttributeSet) {
		this.partitionAttributeSet = partitionAttributeSet;
	}
	
	public void addPartitionAttributeSet(List<PartitionAttributeSet> partitionAttributeSet){
		this.partitionAttributeSet.addAll(partitionAttributeSet);
	}

	public EnumPartitionType getPartitionType() {
		return partitionType;
	}

	public void setPartitionType(EnumPartitionType partitionType) {
		this.partitionType = partitionType;
	}

	public int getParts() {
		return parts;
	}

	public void setParts(int parts) {
		this.parts = parts;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("Type : " + this.getPartitionType());
		sb.append(" ");
		sb.append("Parts : " + this.getParts() + "\n" + "Attributes: {");
		for (PartitionAttributeSet attributeSet : this.partitionAttributeSet) {
			sb.append(attributeSet.toString());
		}
		sb.append("}");

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PartitionInfo))
			return false;
		// cast and compare
		PartitionInfo pi = (PartitionInfo) obj;

		
		// TODO: This must be fixed. Not very efficient.
		
		// Firstly, let's check the size of attribute set and its type 
		if (this.partitionAttributeSet.size() != pi.getPartitionAttributeSet().size()) return false;
		if (! this.partitionType.equals(pi.partitionType)) return false;
		
		// Now that we know that the size of partitionAttributeSet of two objects is the same, 
		// we can check whether one is a subset of another. Simple :)
		return this.contains(pi);
	}


	/**
	 * This method checks whether the toBeComparedPartitionInfo is a subset of the current PartitionInfo,
	 * meaning that the PartitionAttributeSet of toBeComparedPartitionInfo is a subset of the current PartitionAttributeSet.
	 * For example, the set {[a], [b]} is a subset of the set {[a], [b], [c], [a, b]}  
	 * @param toBeComparedPartitionInfo The PartitionInfo to be compared with the current object.
	 * @return A boolean value indicating whether the input argument is a subset of the object.
	 */
	public boolean contains(PartitionInfo toBeComparedPartitionInfo) {
		// TODO: This must be fixed. The search is now brute-force 
		
		// First off, if the toBeComparedPartitionInfo is empty, then it means that this is a Union operation.
		// Unless the current PartitionInfo object is empty as well, and the method must return false.
		if (toBeComparedPartitionInfo.getPartitionAttributeSet().size() == 0 )
			if (this.getPartitionAttributeSet().size() != 0) return false;
		
		boolean found = false;
		for (PartitionAttributeSet comparingAttSet : toBeComparedPartitionInfo.getPartitionAttributeSet()){
			found = false;
			for (PartitionAttributeSet originalAttSet : this.getPartitionAttributeSet() ){
				if (comparingAttSet.equals(originalAttSet)){
					found = true;
					break;
				}
			}
			if (! found) return false;
		}
		return true;
	}

	/*
	protected PartitionInfo makeClone(){
	
		PartitionInfo pi  = new PartitionInfo();
		
		pi.partitionType = this.partitionType;
		pi.parts = this.parts;
		
		List<PartitionAttributeSet> clonePartitionAttributeSet = new ArrayList<PartitionAttributeSet>();
		for (PartitionAttributeSet attSet : this.getPartitionAttributeSet()){
			clonePartitionAttributeSet.add((PartitionAttributeSet)attSet.clone());
		}
		pi.addPartitionAttributeSet(clonePartitionAttributeSet);
		return pi;
	}
	*/
	
}
