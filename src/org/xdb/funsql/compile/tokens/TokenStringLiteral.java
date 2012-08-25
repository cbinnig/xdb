package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.types.EnumLiteralType;

public class TokenStringLiteral extends TokenLiteral {

	private String value;
	
	public TokenStringLiteral(String value){
		this.value = value;
		this.type = EnumLiteralType.SQL_STRING_LITERAL;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString(){
		return this.value;
	}
	
	@Override
	public String toSqlString(){
		StringBuffer literal = new StringBuffer();
		literal.append(TokenConstants.QUOTE);
		literal.append(this.value);
		literal.append(TokenConstants.QUOTE);
		return literal.toString();
	}
}
