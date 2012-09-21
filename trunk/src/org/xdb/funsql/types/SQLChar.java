package org.xdb.funsql.types;

public class SQLChar extends AbstractSimpleType {

	private static final long serialVersionUID = -5390412741111271138L;
	private static String BLANK = " ";
	
	// value and maximum length
	private String value;
	private int maxLength = Integer.MAX_VALUE;

	// constructors
	public SQLChar() {
		super(EnumSimpleType.SQL_CHAR);
		this.isNull = true;
	}

	public SQLChar(String value) {
		super(EnumSimpleType.SQL_CHAR);
		this.value = normalize(value, maxLength);
	}

	public SQLChar(int maxLength) {
		this();
		this.maxLength = maxLength;
	}
	
	public SQLChar(String value, int maxLength) {
		this();
		this.maxLength = maxLength;
		this.value = normalize(value, maxLength);
	}

	// getter and setter
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = normalize(value, maxLength);
		this.isNull = false;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
		this.value = normalize(value, maxLength);
	}
	
	private static String normalize(String value, int maxLen){
		StringBuffer normalizedVal = new StringBuffer(value);
		if(maxLen < normalizedVal.length()){
			return normalizedVal.substring(maxLen);
		}
		else{
			while(normalizedVal.length()<maxLen){
				normalizedVal.insert(0, BLANK);
			}
			return normalizedVal.toString();
		}
	}

	// other helper methods
	@Override
	public boolean equals(Object o) {
		SQLChar sqlVarchar = (SQLChar) o;

		if (!this.value.equals(sqlVarchar.value))
			return false;

		return true;
	}
	
	@Override
	public SQLChar clone(){
		SQLChar sqlVarchar = new SQLChar(this.value, this.maxLength);
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
