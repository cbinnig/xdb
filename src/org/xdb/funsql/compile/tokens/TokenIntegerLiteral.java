package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.types.EnumLiteralType;

public class TokenIntegerLiteral extends TokenLiteral {
	private static final long serialVersionUID = 6479045334346379797L;
	
	private Integer value;
	
	public TokenIntegerLiteral(Integer value){
		this.value = value;
		this.type = EnumLiteralType.SQL_INTEGER_LITERAL;
	}
	
	public TokenIntegerLiteral(String value){
		this.value = Integer.parseInt(value);
		this.type = EnumLiteralType.SQL_INTEGER_LITERAL;
	}
	
	public Integer getValue(){
		return this.value;
	}
	
	@Override
	public String toString(){
		return this.value.toString();
	}
	
	@Override
	public String toSqlString(){
		return this.value.toString();
	}
}
