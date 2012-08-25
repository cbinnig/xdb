package org.xdb.funsql.types;

public class SQLVarchar extends AbstractSimpleType {

	private static final long serialVersionUID = -5390412741111271138L;

	// value and maximum length
	private String value;
	private int maxLength = Integer.MAX_VALUE;

	// constructors
	public SQLVarchar() {
		super(EnumSimpleType.SQL_VARCHAR);
		this.isNull = true;
	}

	public SQLVarchar(String value) {
		super(EnumSimpleType.SQL_VARCHAR);
		this.value = value;
	}

	public SQLVarchar(int maxLength) {
		this();
		this.maxLength = maxLength;
	}
	
	public SQLVarchar(String value, int maxLength) {
		this(value);
		this.maxLength = maxLength;
	}

	// getter and setter
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		this.isNull = false;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	// other helper methods
	@Override
	public boolean equals(Object o) {
		SQLVarchar sqlVarchar = (SQLVarchar) o;

		if (!this.value.equals(sqlVarchar.value))
			return false;

		return true;
	}
	
	@Override
	public SQLVarchar clone(){
		SQLVarchar sqlVarchar = new SQLVarchar(this.value, this.maxLength);
		sqlVarchar.setNull(this.isNull);
		return sqlVarchar;
	}
	
	@Override
	public String toString(){
		return this.value;
	}
	
	@Override
	public int hashCode(){
		return this.value.hashCode();
	}
}
