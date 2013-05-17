package org.xdb.funsql.compile.expression;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.AbstractTokenOperand;
import org.xdb.funsql.compile.tokens.EnumOperandType;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;

public class SimpleExpression extends AbstractExpression {
	private static final long serialVersionUID = -857048085355641688L;
	
	private AbstractTokenOperand tOper;
	
	//constructors 
	public SimpleExpression() {
		super();
		
		this.type = EnumExprType.SIMPLE_EXPRESSION;
	}
	
	public SimpleExpression(SimpleExpression toCopy){
		
		this.tOper = null;
	}
	
	
	public SimpleExpression(AbstractTokenOperand tOper) {
		this();
		
		this.tOper = tOper;
	}
	
	//getters and setters

	public AbstractTokenOperand getOper() {
		return tOper;
	}

	public void setOper(AbstractTokenOperand tOper) {
		this.tOper = tOper;
	}

	//helper methods
	@Override
	public String toString() {
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString(){
		StringBuffer sqlValue = new StringBuffer();
		if(this.isNegated)
			sqlValue.append(AbstractToken.MINUS);
		
		sqlValue.append(tOper.toSqlString());
		
		return sqlValue.toString();
	}

	@Override
	public Collection<TokenAttribute> getAttributes() {
		Vector<TokenAttribute> atts = new Vector<TokenAttribute>();
		if(this.tOper.getType() == EnumOperandType.ATTRIBUTE){
			atts.add((TokenAttribute)this.tOper);
		}
	
		return atts;
	}

	@Override
	public boolean isAttribute() {
		if(!this.tOper.isAttribute())
			return false;
		return true;
	}
	@Override
	public boolean isAggregation() {
		return false;
	}

	@Override
	public TokenAttribute getAttribute() {
		if(this.tOper.isAttribute()){
			return (TokenAttribute)this.tOper;
		}
		return null;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public AbstractExpression clone() {
		SimpleExpression expr = new SimpleExpression();
		expr.tOper = this.tOper.clone();
		return expr;
	}

	@Override
	public AbstractExpression replaceExpressions(
			Map<TokenIdentifier, AbstractExpression> exprs) {
		if(this.isAttribute()){
			TokenAttribute att1 = this.getAttribute();
			if(exprs.containsKey(att1.getName()))
				return exprs.get(att1.getName());
		}
		return this;
	}
	
}
