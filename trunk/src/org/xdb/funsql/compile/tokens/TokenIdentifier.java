package org.xdb.funsql.compile.tokens;

public class TokenIdentifier extends AbstractToken{
	//attributes
	private String name;

	//constructors
	public TokenIdentifier(String name) {
		this.name = name;
	}

	//getter and setter
	public String getName() {
		return name;
	}

	//helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString(){
		return this.name;
	}
	
	public String hashKey(){
		return this.name;
	}
}
