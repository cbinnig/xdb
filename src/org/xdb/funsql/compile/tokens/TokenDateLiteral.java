package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.types.EnumLiteralType;

public class TokenDateLiteral extends TokenLiteral {
	private static final long serialVersionUID = -8398646972151222073L;
	
	private String value;
	
	public TokenDateLiteral(String value){
		this.value = value;
		this.type = EnumLiteralType.SQL_DATE_LITERAL;
	}
	
	public String getValue(){
		return this.value;
	}
	@Override
	public String toString(){
		return this.value.toString();
	}
	
	@Override
	public String toSqlString(){
		StringBuffer sqlValue = new StringBuffer();
		sqlValue.append(AbstractToken.DATE);
		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(this.value.toString());
		return sqlValue.toString();
	}
	
	@Override
	public AbstractTokenOperand clone() {
		return new TokenDateLiteral(this.value);
	}
}
