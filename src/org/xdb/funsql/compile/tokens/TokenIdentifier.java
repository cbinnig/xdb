package org.xdb.funsql.compile.tokens;

public class TokenIdentifier extends AbstractToken implements Cloneable{
	private static final long serialVersionUID = -7726723089171901571L;
	
	//attributes
	private String name;

	//constructors
	public TokenIdentifier(String name) {
		this.name = name;
	}

	
	/**
	 * @param name2
	 */
	public TokenIdentifier(TokenIdentifier toCopy) {
		if(toCopy!=null){
			this.name = toCopy.name;
		}
		
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
	
	@Override
	public TokenIdentifier clone(){
		return new TokenIdentifier(this.name);
	}
	
	public String hashKey(){
		return this.name;
	}
	
}
