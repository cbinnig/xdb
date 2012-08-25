package org.xdb.funsql.compile.tokens;

public class TokenIntegerLiteral extends TokenLiteral {
	private Long value;
	
	public TokenIntegerLiteral(Long value){
		this.value = value;
	}
	
	public TokenIntegerLiteral(String value){
		this.value = Long.parseLong(value);
	}
	
	public Long getValue(){
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
