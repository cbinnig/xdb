package org.xdb.funsql.types;

public class SQLInteger extends AbstractSimpleType {

	private static final long serialVersionUID = 5028158476945772100L;

	//integer value
	private int value;

	// constructors
	public SQLInteger() {
		super(EnumSimpleType.SQL_INTEGER);
		this.isNull = true;
	}

	public SQLInteger(int value) {
		super(EnumSimpleType.SQL_INTEGER);
		this.value = value;
	}

	// getter and setter
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		this.isNull = false;
	}

	// other helper methods
	@Override
	public boolean equals(Object o){
		SQLInteger sqlInt = (SQLInteger)o;
		
		if(this.value != sqlInt.value)
			return false;
		
		return true;
	}
	
	@Override
	public SQLInteger clone(){
		SQLInteger sqlInt = new SQLInteger(this.value);
		sqlInt.setNull(this.isNull);
		return sqlInt;
	}
	
	@Override
	public String toString(){
		return ""+this.value;
	}
	
	@Override
	public int hashCode(){
		return this.value;
	}
}
