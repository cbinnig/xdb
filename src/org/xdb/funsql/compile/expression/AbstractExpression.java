package org.xdb.funsql.compile.expression;

import org.xdb.funsql.compile.tokens.AbstractToken;

public abstract class AbstractExpression extends AbstractToken{

	private static final long serialVersionUID = 7432833594195223415L;

	protected boolean isNegated = false;
	protected EnumExprType type;
	
	public AbstractExpression() {
		this.type = EnumExprType.NO_EXPRESSION;
	}
	
	public void negate(){
		this.isNegated = !this.isNegated;
	}
	
	public boolean isNegated(){
		return this.isNegated;
	}

	public EnumExprType getType() {
		return type;
	}

	public void setType(EnumExprType type) {
		this.type = type;
	}
}
