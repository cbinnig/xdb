package org.xdb.funsql.types;

import java.sql.Date;


public class SQLDate extends AbstractSimpleType {

	private static final long serialVersionUID = 5028158476945772100L;

	//integer value
	private Date value;

	// constructors
	public SQLDate() {
		super(EnumSimpleType.SQL_DATE);
		this.isNull = true;
	}

	public SQLDate(Date value) {
		super(EnumSimpleType.SQL_INTEGER);
		this.value = value;
	}

	// getter and setter
	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
		this.isNull = false;
	}

	// other helper methods
	@Override
	public boolean equals(Object o){
		SQLDate sqlInt = (SQLDate)o;
		
		if(this.value != sqlInt.value)
			return false;
		
		return true;
	}
	
	@Override
	public SQLDate clone(){
		SQLDate sqlInt = new SQLDate(this.value);
		sqlInt.setNull(this.isNull);
		return sqlInt;
	}
	
	@Override
	public String toString(){
		return this.value.toString();
	}
	
	@Override
	public int hashCode(){
		return this.value.hashCode();
	}
}
