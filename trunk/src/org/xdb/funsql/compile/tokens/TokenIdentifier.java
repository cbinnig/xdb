package org.xdb.funsql.compile.tokens;

public class TokenIdentifier extends AbstractToken implements Cloneable{
	private static final long serialVersionUID = -7726723089171901571L;
	
	//attributes
	private String value;

	//constructors
	public TokenIdentifier(String value) {
		this.value = value;
	}

	
	/**
	 * @param name2
	 */
	public TokenIdentifier(TokenIdentifier toCopy) {
		if(toCopy!=null){
			this.value = toCopy.value;
		}
		
	}

	//getter and setter
	public String getValue() {
		return value;
	}

	//helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString(){
		return this.value;
	}
	
	@Override
	public TokenIdentifier clone(){
		return new TokenIdentifier(this.value);
	}
	
	public String hashKey(){
		return this.value;
	}
	
}
