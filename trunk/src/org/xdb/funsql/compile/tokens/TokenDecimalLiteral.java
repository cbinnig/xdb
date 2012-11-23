package org.xdb.funsql.compile.tokens;

import java.math.BigDecimal;

import org.xdb.funsql.types.EnumLiteralType;

public class TokenDecimalLiteral extends TokenLiteral {
	private static final long serialVersionUID = -8686249372999747945L;
	
	private BigDecimal value;
	
	public TokenDecimalLiteral(BigDecimal value){
		this.value = value;
		this.type = EnumLiteralType.SQL_DECIMAL_LITERAL;
	}
	
	public TokenDecimalLiteral(String value){
		this.value = new BigDecimal(value);
		this.type = EnumLiteralType.SQL_DECIMAL_LITERAL;
	}
	
	public BigDecimal getValue(){
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
	
	@Override
	public AbstractTokenOperand clone() {
		return new TokenDecimalLiteral(this.value);
	}
}
