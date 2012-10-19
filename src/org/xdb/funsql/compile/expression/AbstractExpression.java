package org.xdb.funsql.compile.expression;

import java.util.Set;

import org.xdb.funsql.compile.analyze.IExpressionVisitor;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;

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
	
	/**
	 * Returns all attributes of expression
	 * @return
	 */
	public abstract Set<TokenAttribute> getAttributes();
	
	/**
	 * Checks if expression is only an attribute
	 * @return
	 */
	public abstract boolean isAttribute();
	
	/**
	 * Returns aggregation expressions
	 * @return
	 */
	public abstract boolean isAggregation();
	
	/**
	 * Accepts visitors for compile and optimize phases
	 * @param v
	 */
	public abstract void accept(IExpressionVisitor v);
}
