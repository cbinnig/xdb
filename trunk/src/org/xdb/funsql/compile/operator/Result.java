package org.xdb.funsql.compile.operator;

import java.util.Vector;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.types.EnumSimpleType;

public class Result {
	//attribute
	private Vector<TokenAttribute> attribtues;
	private Vector<EnumSimpleType> types;
	
	//constructors
	public Result(int size){
		this.attribtues = new Vector<TokenAttribute>(size);
	}
	
	//getters and setters
	public void setTokenAttribute(int i, TokenAttribute attribute){
		this.attribtues.set(i, attribute);
	}
	
	public TokenAttribute getTokenAttribute(int i){
		return this.attribtues.get(i);
	}
	
	public Vector<TokenAttribute> getTokenAttributes(){
		return this.attribtues;
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
}
