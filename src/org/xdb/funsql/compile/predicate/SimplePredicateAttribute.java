package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenConstants;

public class SimplePredicateAttribute extends AbstractPredicate {
	private TokenAttribute tLeftAtt;
	private TokenAttribute tRightAtt;
	
	//constructors 
	public SimplePredicateAttribute() {
		super();
	}

	public SimplePredicateAttribute(TokenAttribute leftAtt, TokenAttribute rightAtt) {
		this();
		
		this.tLeftAtt = leftAtt;
		this.tRightAtt = rightAtt;
	}
	
	//getters and setters
	public TokenAttribute getLeftAtt() {
		return tLeftAtt;
	}

	public void setLeftAtt(TokenAttribute tLeftAtt) {
		this.tLeftAtt = tLeftAtt;
	}

	public TokenAttribute getRightAtt() {
		return tRightAtt;
	}

	public void setRightAtt(TokenAttribute tRightAtt) {
		this.tRightAtt = tRightAtt;
	}

	//helper methods
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString(){
		StringBuffer literal = new StringBuffer();
		literal.append(tLeftAtt.toSqlString());
		literal.append(TokenConstants.EQUAL1);
		literal.append(tRightAtt.toSqlString());
		return literal.toString();
	}
}
