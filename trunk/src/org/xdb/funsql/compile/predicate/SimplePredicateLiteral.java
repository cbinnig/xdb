package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenConstants;
import org.xdb.funsql.compile.tokens.TokenLiteral;

public class SimplePredicateLiteral extends AbstractPredicate {
	private TokenAttribute tAttribute;
	private TokenLiteral tLiteral;
	
	//constructors 
	public SimplePredicateLiteral() {
		super();
	}

	public SimplePredicateLiteral(TokenAttribute attribute, TokenLiteral literal) {
		this();
		
		this.tAttribute = attribute;
		this.tLiteral = literal;
	}
	
	//getters and setters
	public TokenAttribute getAttribute() {
		return tAttribute;
	}

	public void setAttribute(TokenAttribute tAttribute) {
		this.tAttribute = tAttribute;
	}

	public TokenLiteral getLiteral() {
		return tLiteral;
	}

	public void setLiteral(TokenLiteral tLiteral) {
		this.tLiteral = tLiteral;
	}

	//helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString(){
		StringBuffer literal = new StringBuffer();
		literal.append(tAttribute.toSqlString());
		literal.append(TokenConstants.EQUAL1);
		literal.append(tLiteral.toSqlString());
		return literal.toString();
	}
}
