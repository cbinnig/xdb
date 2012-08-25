package org.xdb.funsql.compile.operator;

import java.util.Vector;

import org.xdb.funsql.compile.tokens.TokenAttribute;

public class SimpleProjection extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;
	
	private Vector<TokenAttribute> attributes;
	
	//constructors
	public SimpleProjection(AbstractOperator child, int size) {
		super(child);
		
		this.attributes = new Vector<TokenAttribute>(size);
		this.type = EnumOperator.SIMPLE_PROJECTION;
	}
	
	//getters and setters
	public void setTokenAttribute(int i, TokenAttribute attribute){
		this.attributes.set(i, attribute);
	}
	
	public TokenAttribute getAttribtue(int i){
		return this.attributes.get(i);
	}

	public Vector<TokenAttribute> getTokenAttributes() {
		return attributes;
	}
}
