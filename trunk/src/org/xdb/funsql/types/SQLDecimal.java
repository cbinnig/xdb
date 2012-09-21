package org.xdb.funsql.types;

import java.math.BigDecimal;

public class SQLDecimal extends AbstractSimpleType {

	private static final long serialVersionUID = 5028158476945772100L;

	//integer value
	private BigDecimal value;

	// constructors
	public SQLDecimal() {
		super(EnumSimpleType.SQL_DECIMAL);
		this.isNull = true;
	}

	public SQLDecimal(BigDecimal value) {
		super(EnumSimpleType.SQL_DECIMAL);
		this.value = value;
	}

	// getter and setter
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
		this.isNull = false;
	}

	// other helper methods
	@Override
	public boolean equals(Object o){
		SQLDecimal sqlDecimal = (SQLDecimal)o;
		
		if(!this.value.equals(sqlDecimal.value))
			return false;
		
		return true;
	}
	
	@Override
	public SQLDecimal clone(){
		SQLDecimal sqlInt = new SQLDecimal(this.value);
		sqlInt.setNull(this.isNull);
		return sqlInt;
	}
	
	@Override
	public String toString(){
		return ""+this.value;
	}
	
	@Override
	public int hashCode(){
		return this.value.hashCode();
	}
}
