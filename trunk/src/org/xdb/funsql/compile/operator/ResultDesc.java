package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.Vector;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.types.EnumSimpleType;

public class ResultDesc implements Serializable{
	private static final long serialVersionUID = 8819533773334264233L;
	
	//attribute
	private Vector<TokenAttribute> attributes;
	private Vector<EnumSimpleType> types;
	private boolean materialize;
	private PartitionDesc partitionDesc;
	
	//constructors
	public ResultDesc(int size){
		this.attributes = new Vector<TokenAttribute>(size);
	}
	
	//getters and setters
	public void setAttribute(int i, TokenAttribute attribute){
		this.attributes.set(i, attribute);
	}
	
	public TokenAttribute getAttribute(int i){
		return this.attributes.get(i);
	}
	
	public Vector<TokenAttribute> getAttributes(){
		return this.attributes;
	}
	
	public void setType(int i, EnumSimpleType type){
		this.types.set(i, type);
	}
	
	public EnumSimpleType getType(int i){
		return this.types.get(i);
	}
	
	public Vector<EnumSimpleType> getTypes(){
		return this.types;
	}
	
	public boolean isMaterialize() {
		return materialize;
	}

	public void setMaterialize(boolean materialize) {
		this.materialize = materialize;
	}

	public PartitionDesc getPartitionDesc() {
		return partitionDesc;
	}

	public void setPartitionDesc(PartitionDesc partitionDesc) {
		this.partitionDesc = partitionDesc;
	}
	
	public String toSqlString() {
		//TODO: create SQL expression suitable for use in tracker.operator.MySQLOperator
		
		return null;
	}
}
