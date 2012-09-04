package org.xdb.funsql.compile.tokens;

import org.xdb.Config;

public class TokenTable extends AbstractToken{
	//attributes
	private TokenIdentifier name;
	private TokenIdentifier alias;
	private TokenSchema schema;
	
	//constructors
	public TokenTable(String name){
		this();
		this.name = new TokenIdentifier(name);
	}
	
	public TokenTable(){
		this.schema = new TokenSchema(Config.COMPILE_DEFAULT_SCHEMA);
	}
	
	//getters and setters
	public void setAlias(String alias) {
		this.alias = new TokenIdentifier(alias);
	}

	public void setSchema(TokenSchema schema) {
		this.schema = schema;
	}
	
	public void setSchema(String schema) {
		this.schema = new TokenSchema(schema);
	}
	
	public void setName(String name) {
		this.name = new TokenIdentifier(name);
	}
	
	public void setName(TokenIdentifier name) {
		this.name = name;
	}
	
	public TokenIdentifier getName() {
		return name;
	}

	public TokenIdentifier getAlias() {
		return alias;
	}

	public TokenSchema getSchema() {
		return schema;
	}

	//helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString(){
		StringBuffer buffer =  new StringBuffer();
		if(this.schema != null){
			buffer.append(this.schema.toString());
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
	
	public String hashKey(long schemaOid){
		StringBuffer key = new StringBuffer();
		key.append(schemaOid);
		key.append(DOT);
		key.append(this.name.toString());
		return key.toString();
	}
}
