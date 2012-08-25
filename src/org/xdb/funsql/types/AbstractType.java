package org.xdb.funsql.types;

import java.io.Serializable;

public abstract class AbstractType implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -494489689340972161L;
	
	//is null indicator
	protected boolean isNull;
	
	//constructors
	public AbstractType(){
		this.isNull = false;
	}

	//getters and setters
	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	
	//helper methods
	@Override
	public abstract AbstractType clone();
}
