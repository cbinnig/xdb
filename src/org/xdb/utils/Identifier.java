package org.xdb.utils;

import java.io.Serializable;
import java.util.Vector;

/**
 * Implements a hierarchical identifier
 * e.g. 1_1_1
 * Used in XDB to identify plans and operators (compile, tracker, execute)
 * 
 * Plans have a top level id: e.g. 1
 * Operators in a plan have child IDs: e.g. 1_1
 * 
 * One tracker operator can be executed by different execute operators. Thus
 * execute are one level below tracker operators: e.g., tracker operator has 
 * id 1_1 while execute operator has id 1_1_1.
 * 
 * @author cbinnig
 *
 */
public class Identifier implements Serializable, Cloneable{

	private static final long serialVersionUID = -7793679328045299666L;
	private static final String SEPARATOR = "_";
	
	private StringBuffer id;
	private Vector<String> ids = new Vector<String>();
	
	private Identifier(Identifier id) {
		this.id = new StringBuffer(id.id);
		this.ids.addAll(id.ids);
	}
	
	public Identifier(String id) {
		this.id = new StringBuffer(id);
		this.ids.add(id);
	}
	
	public Identifier(Integer id) {
		this(id.toString());
	}
	
	public Identifier append(Integer subId){
		this.id.append(SEPARATOR);
		this.id.append(subId);
		this.ids.add(subId.toString());
		return this;
	}
	
	public Identifier append(String subId){
		this.id.append(SEPARATOR);
		this.id.append(subId);
		this.ids.add(subId);
		return this;
	}
	
	/**
	 * Returns ID for first given number of levels
	 * e.g., 1_1_1 with level=1 returns 1_1
	 * @param level
	 * @return
	 */
	public Identifier getParentId(int level){
		if(level>=this.ids.size())
			return null;
		
		if(level==this.ids.size()-1)
			return this;
		
		Identifier parentId = new Identifier(this.ids.get(0));
		for(int i=1;i<=level; ++i){
			parentId.append(this.ids.get(i));
		}
		return parentId;
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
		return new Identifier(this);
	}
}
