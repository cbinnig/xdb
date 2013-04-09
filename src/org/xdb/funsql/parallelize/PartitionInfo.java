package org.xdb.funsql.parallelize;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * This class encapsulates the partition information for every operator, needed
 * to parallelize Plans
 * 
 * @author Alexander.C Mueller
 * 
 */
public class PartitionInfo implements Serializable, Cloneable {

	private static final long serialVersionUID = 7175327340663407036L;
	
	private Set<TokenAttribute> partitionAttributes = new HashSet<TokenAttribute>();
	private EnumPartitionType partitionType;
	private int parts;

	public PartitionInfo(Set<TokenAttribute> partitionAttributes,
			EnumPartitionType partitionType, int parts) {
		super();
		this.partitionAttributes = partitionAttributes;
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
		
		Set<TokenAttribute> clonepartitionAttributes = new HashSet<TokenAttribute>();
		
		for (TokenAttribute tokenAttribute : toCopy.partitionAttributes) {
			clonepartitionAttributes.add(tokenAttribute.clone());
		}
		this.partitionAttributes = clonepartitionAttributes;
		
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

	public Set<TokenAttribute> getPartitionAttributes() {
		return partitionAttributes;
	}

	public void setPartitionAttributes(Set<TokenAttribute> partitionAttributes) {
		this.partitionAttributes = partitionAttributes;
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
		sb.append("Parts : " + this.getParts() + "\n" + "Attributes: [");
		for (TokenAttribute att : this.partitionAttributes) {
			sb.append(att.getName().toString() + ", ");
		}
		sb.append("]");

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PartitionInfo))
			return false;
		// cast and compare
		PartitionInfo pi = (PartitionInfo) obj;

		boolean test1 = true;
		int counter = 0;
		for(TokenAttribute ta :pi.getPartitionAttributes()){
			counter=0;
			for(TokenAttribute originta :this.partitionAttributes){
				if(originta.getName().equals(ta.getName())){
					counter++;
				}
			}
			if(counter==0) test1= false;
		}
		
		boolean test2 = true;;
		for(TokenAttribute originta :this.partitionAttributes){
			counter = 0;
			for(TokenAttribute ta :pi.getPartitionAttributes()){
				if(originta.getName().equals(ta.getName())){
					counter++;
				}
			}
			if(counter==0) test2= false;
		}
		
		return (test1 && test2
				&& pi.getPartitionType().equals(this.partitionType) && pi
					.getParts() == this.getParts());

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
	
		PartitionInfo pi  = (PartitionInfo) super.clone();
		
		pi.partitionType = this.partitionType;
		pi.parts = this.parts;
		
		Set<TokenAttribute> clonepartitionAttributes = new HashSet<TokenAttribute>();
		
		for (TokenAttribute tokenAttribute : partitionAttributes) {
			clonepartitionAttributes.add(tokenAttribute.clone());
		}
		pi.partitionAttributes = clonepartitionAttributes;
		
		return pi;
		
	}
	

}
