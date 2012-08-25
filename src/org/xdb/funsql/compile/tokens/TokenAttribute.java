package org.xdb.funsql.compile.tokens;

public class TokenAttribute extends AbstractToken{
	// attributes
	private TokenIdentifier name;
	private TokenIdentifier alias;
	private TokenTable table;

	// constructors
	public TokenAttribute() {
		super();
	}
	
	public TokenAttribute(String name) {
		this();
		this.name = new TokenIdentifier(name);
	}

	// getters and setters
	public TokenIdentifier getName() {
		return name;
	}
	
	public TokenIdentifier getAlias() {
		return alias;
	}

	public TokenTable getTable() {
		return table;
	}
	
	public void setAlias(String alias) {
		this.alias = new TokenIdentifier(alias);
	}

	public void setTable(String table) {
		this.table = new TokenTable(table);
	}
	
	public void setName(String name) {
		this.name = new TokenIdentifier(name);
	}
	
	public void setAlias(TokenIdentifier alias) {
		this.alias = alias;
	}

	public void setTable(TokenTable table) {
		this.table = table;
	}
	
	public void setName(TokenIdentifier name) {
		this.name = name;
	}
	//helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString() {
		StringBuffer buffer = new StringBuffer();
		if (this.table != null) {
			buffer.append(this.table.toString());
			buffer.append(AbstractToken.DOT);
		}
		
		if(this.alias != null){
			buffer.append(this.alias.toString());
		}
		else{
			buffer.append(this.name.toString());
		}
		
		return buffer.toString();
	}
}
