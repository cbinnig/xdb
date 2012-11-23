package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.types.EnumLiteralType;

public class TokenStringLiteral extends TokenLiteral {

	private static final long serialVersionUID = 2059513693773115009L;
	
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
		literal.append(AbstractToken.QUOTE);
		literal.append(this.value);
		literal.append(AbstractToken.QUOTE);
		return literal.toString();
	}

	@Override
	public AbstractTokenOperand clone() {
		return new TokenStringLiteral(this.value);
	}
}
