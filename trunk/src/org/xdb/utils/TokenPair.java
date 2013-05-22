package org.xdb.utils;

import java.io.Serializable;

import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenTable;

public class TokenPair implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1086680908299017075L;
	
	private TokenAttribute leftTokenAttribute;
	private TokenAttribute rightTokenAttribute;
	private String leftTableName;
	private String rightTableName;

	public TokenPair(TokenPair toCopy){
		this.leftTableName = toCopy.leftTableName;
		this.rightTableName = toCopy.rightTableName;
		this.leftTokenAttribute = toCopy.leftTokenAttribute.clone();
		this.rightTokenAttribute = toCopy.rightTokenAttribute.clone();
	}
	
	public String getLeftTableName() {
		return leftTableName;
	}
	public void setLeftTableName(String leftTableName) {
		this.leftTableName = leftTableName;
	}
	public String getRightTableName() {
		return rightTableName;
	}
	public void setRightTableName(String rightTableName) {
		this.rightTableName = rightTableName;
	}
	public TokenPair(TokenAttribute leftTokenAttribute,
			TokenAttribute rightTokenAttribute) {
		this.leftTokenAttribute = leftTokenAttribute;
		this.rightTokenAttribute = rightTokenAttribute;
	}
	public TokenTable findTableForColumn(String columnname){
		
		if (this.leftTokenAttribute.getTable().getName().equals(columnname)){
			return this.leftTokenAttribute.getTable();
		}else if (this.rightTokenAttribute.getTable().getName().equals(columnname)) {
			return this.rightTokenAttribute.getTable();
		} else {
			return null;
		}
			
	}
	
	// getter and setters
	public TokenAttribute getLeftTokenAttribute() {
		return leftTokenAttribute;
		
	}

	public void setLeftTokenAttribute(TokenAttribute leftTokenAttribute) {
		this.leftTokenAttribute = leftTokenAttribute;
	}

	public void setRightTokenAttribute(TokenAttribute rightTokenAttribute) {
		this.rightTokenAttribute = rightTokenAttribute;
	}

	public TokenAttribute getRightTokenAttribute() {
		return rightTokenAttribute;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TokenPair)){
			return false;
		}
		TokenPair t = (TokenPair) o;
		return ( t.leftTokenAttribute.equals(this.leftTokenAttribute) && t.rightTokenAttribute.equals(this.rightTokenAttribute));
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(this.leftTokenAttribute);
		buffer.append(AbstractToken.EQUAL1);
		buffer.append(this.rightTokenAttribute);
		
		return buffer.toString();
	}
	
}
