package org.xdb.funsql.compile.tokens;

import java.sql.Date;

import org.xdb.funsql.types.EnumLiteralType;

public class TokenDateLiteral extends TokenLiteral {
	private static final long serialVersionUID = -8398646972151222073L;
	
	private Date value;
	
	public TokenDateLiteral(Date value){
		this.value = value;
		this.type = EnumLiteralType.SQL_DECIMAL_LITERAL;
	}
	
	@SuppressWarnings("deprecation")
	public TokenDateLiteral(String value){
		this.value = new Date(Date.parse(value));
		this.type = EnumLiteralType.SQL_DECIMAL_LITERAL;
	}
	
	public Date getValue(){
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
}
