package org.xdb.funsql.compile.tokens;

public class TokenSchema extends AbstractToken implements Cloneable{
	private static final long serialVersionUID = -6833608816316673507L;
	
	//attributes
	private TokenIdentifier name;
	
	//constructors
	public TokenSchema(){
		super();
	}
	
	public TokenSchema(String name){
		this();
		this.name = new TokenIdentifier(name);
	}
	
	//getters and setters
	public void setName(String name){
		this.name = new TokenIdentifier(name);
	}
	
	public void setName(TokenIdentifier name){
		this.name = name;
	}
	
	public TokenIdentifier getName() {
		return name;
	}

	//helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString(){
		return this.name.toString();
	}
	
	public String hashKey(){
		return this.name.toString();
	}

	@Override
	protected TokenSchema clone() throws CloneNotSupportedException {
		TokenSchema ts = (TokenSchema) super.clone();
		ts.name.clone();
		return ts;
	}
	

	
}
