package org.xdb.utils;

import java.io.Serializable;

public class Identifier implements Serializable, Cloneable{

	private static final long serialVersionUID = -7793679328045299666L;
	private static final String SEPARATOR = "_";
	
	private StringBuffer id;
	
	public Identifier(String id) {
		this.id = new StringBuffer(id);
	}
	
	public Identifier(Integer id) {
		this.id = new StringBuffer(id.toString());
	}
	
	public Identifier append(Integer subId){
		this.id.append(SEPARATOR);
		this.id.append(subId);
		return this;
	}
	
	public Identifier append(String subId){
		this.id.append(SEPARATOR);
		this.id.append(subId);
		return this;
	}
	
	@Override
	public String toString(){
		return this.id.toString();
	}
	
	@Override
	public boolean equals(Object o){
		String s = o.toString();
		return id.toString().equals(s);
	}
	
	@Override
	public int hashCode(){
		return this.id.toString().hashCode();
	}

	@Override
	public Identifier clone(){
		return new Identifier(this.id.toString());
	}
}
