package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.types.EnumSimpleType;

public class TokenDataType extends AbstractToken {
	private static final long serialVersionUID = 3040832343338867562L;
	
	private EnumSimpleType dataType;
	private int maxLength = Integer.MAX_VALUE;

	public TokenDataType(String dataType) {
		super();
		this.dataType = EnumSimpleType.get(dataType);
	}
	
	public TokenDataType(String dataType, int maxLength) {
		this(dataType);
		this.maxLength = maxLength;
	}

	//getters and setters
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	public EnumSimpleType getDataType() {
		return dataType;
	}

	public int getMaxLength() {
		return maxLength;
	}

	// helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.dataType.toString());

		if (this.maxLength != Integer.MAX_VALUE) {
			buffer.append("(");
			buffer.append(this.maxLength);
			buffer.append(")");
		}

		return buffer.toString();
	}
}
