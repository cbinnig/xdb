package org.xdb.funsql.compile.tokens;


public class TokenAttribute extends AbstractTokenOperand{
	private static final long serialVersionUID = -846135086164968356L;
	
	// attributes
	private TokenIdentifier name;
	private TokenTable table;
	
	// constructors
	public TokenAttribute() {
		super(EnumOperandType.ATTRIBUTE);
	}
	
	public TokenAttribute(String name) {
		this();
		this.name = new TokenIdentifier(name);
	}
	
	public TokenAttribute(String table, String name) {
		this(name);
		this.table = new TokenTable(table);
	}
	
	public TokenAttribute(TokenIdentifier name) {
		this();
		this.name = name;
	}
	
	// getters and setters
	public TokenIdentifier getName() {
		return name;
	}
	
	public TokenTable getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = new TokenTable(table);
	}
	
	public void setName(String name) {
		this.name = new TokenIdentifier(name);
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
			buffer.append(this.table.getName().toString());
			buffer.append(AbstractToken.DOT);
		}
		
		buffer.append(this.name.toString());
		
		return buffer.toString();
	}
	
	@Override
	public boolean isAttribute() {
		return true;
	}
	
	public String hashKey(){
		StringBuffer key = new StringBuffer();
		key.append(this.table.getName().hashKey());
		key.append(DOT);
		key.append(this.name.hashKey());
		return key.toString();
	}
}
