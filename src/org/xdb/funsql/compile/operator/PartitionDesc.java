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
 * @author cbinnig
 *
 */
public class PartitionDesc implements Serializable{
	private static final long serialVersionUID = 1097865181383599610L;
	
	private List<TokenAttribute> partAttributes = new LinkedList<TokenAttribute>();
	private EnumPartitionType partType = EnumPartitionType.NO_PARTITION;
	private int partNumber=1;
	
	//constructors
	public PartitionDesc(){
		//do nothing
	}
	
	public PartitionDesc(EnumPartitionType partType, int partNumber){
		this.partType = partType;
		this.partNumber = partNumber;
	}
	
	public PartitionDesc(PartitionDesc toCopy){
		this.partType = toCopy.partType;
		this.partNumber =toCopy.partNumber;
		
		for(TokenAttribute partAtt: toCopy.partAttributes){
			TokenAttribute newPartAtt = new TokenAttribute(partAtt);
			this.partAttributes.add(newPartAtt);
		}
	}
	
	//getters and setters
	public void addPartAttributes(TokenAttribute partAttribute) {
		this.partAttributes.add(partAttribute);
	}
	
	public void setPartitionType(EnumPartitionType partType) {
		this.partType = partType;
	}
	
	public EnumPartitionType getPartitionType(){
		return this.partType;
	}
	
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public int getPartitionNumber(){
		return this.partNumber;
	}
	
	public boolean isPartitioned(){
		return !this.partType.equals(EnumPartitionType.NO_PARTITION);
	}
	
	//methods
	public boolean isCompatible(PartitionDesc partDesc){
		//true if one input is not partitioned
		if(partDesc.partType.isNotPartitioned() || 
				this.partType.isNotPartitioned())
			return true;
		
		//false if one of the following checks fail
		if(partDesc.partNumber!=this.partNumber)
			return false;
		
		if(!this.partAttributes.equals(partDesc.partAttributes))
			return false;
		
		if(!this.partType.isCompatible(partDesc.partType))
			return false;
		
		//else: true
		return true;
	}
	
	public boolean isCompatible(TokenAttribute partAtt){
		if(this.partAttributes.size()>0 && !this.partAttributes.get(0).equals(partAtt))
			return false;
		
		return true;
	}
	
	public void renameTable(Identifier newOpId){
		for(TokenAttribute partAtt: this.partAttributes){
			partAtt.setTable(newOpId.toString());
		}
	}
	
	public String toSqlString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(AbstractToken.PARTITION);
		buffer.append(AbstractToken.BLANK);
		buffer.append(AbstractToken.BY);
		buffer.append(AbstractToken.BLANK);
		buffer.append(this.partType);
		buffer.append(AbstractToken.LBRACE);
		int tAttCnt = 0;
		for(TokenAttribute tAtt: this.partAttributes){
			buffer.append(tAtt.getName().toSqlString());
			if(tAttCnt>0)
				buffer.append(AbstractToken.COMMA);
			tAttCnt++;
		}
		buffer.append(AbstractToken.RBRACE);
		buffer.append(AbstractToken.BLANK);
		buffer.append(AbstractToken.PARTITIONS);
		buffer.append(AbstractToken.BLANK);
		buffer.append(this.partNumber);
		
		return buffer.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(o==null)
			return false;
		
		PartitionDesc partDesc = (PartitionDesc)o;
		if(partDesc.partNumber!=this.partNumber)
			return false;
		
		for(TokenAttribute partAtt: partDesc.partAttributes){
			if(!this.partAttributes.contains(partAtt))
				return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return this.toSqlString();
	}
	
	@Override
	public int hashCode(){
		return this.partType.hashCode() % this.partNumber;
	}
}
