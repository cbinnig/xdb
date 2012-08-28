package org.xdb.utils;

import java.io.Serializable;

public class Identifier implements Serializable, Cloneable{

	private static final long serialVersionUID = -7793679328045299666L;
	private static final String SEPARATOR = "_";
	
	private String id;
	
	public Identifier(String id) {
		this.id = id;
	}
	
	public void append(Integer subId){
		this.id += SEPARATOR;
		this.id += subId;
	}
	
	public void append(String subId){
		this.id += SEPARATOR;
		this.id += subId;
	}
	
	@Override
	public String toString(){
		return this.id;
	}
	
	@Override
	public boolean equals(Object o){
		String s = o.toString();
		return id.equals(s);
	}
	
	@Override
	public int hashCode(){
		return this.id.hashCode();
	}

	@Override
	public Identifier clone(){
		return new Identifier(this.id);
	}
}
