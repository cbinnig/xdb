package org.xdb.funsql.compile.tokens;

import org.xdb.Config;
import org.xdb.funsql.statement.EnumLanguage;

public class TokenFunction extends AbstractToken implements Cloneable {
	private static final long serialVersionUID = -7374551485124320255L;
	
	//attributes
	private TokenIdentifier name;
	private TokenSchema schema;
	private EnumLanguage language = EnumLanguage.FUNSQL;
	
	//constructors
	public TokenFunction(String name){
		this();
		this.name = new TokenIdentifier(name);
	}
	
	public TokenFunction(){
		this.schema = new TokenSchema(Config.COMPILE_DEFAULT_SCHEMA);
	}
	
	//getters and setters

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

	public TokenSchema getSchema() {
		return schema;
	}
	
	public EnumLanguage getLanguage() {
		return language;
	}

	public void setLanguage(EnumLanguage language) {
		this.language = language;
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
		
		buffer.append(this.name.toString());
		
		return buffer.toString();
	}
	
	public String hashKey(long schemaOid){
		StringBuffer key = new StringBuffer();
		key.append(schemaOid);
		key.append(DOT);
		key.append(this.name.toString());
		return key.toString();
	}

	@Override
	public TokenFunction clone() throws CloneNotSupportedException {
		TokenFunction tc =  new TokenFunction(this.name.getValue());
		tc.language = this.language;
		tc.schema = this.schema.clone();
		return tc;
	}


}
