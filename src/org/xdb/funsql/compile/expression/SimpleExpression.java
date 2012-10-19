package org.xdb.funsql.compile.expression;

import java.util.HashSet;
import java.util.Set;

import org.xdb.funsql.compile.analyze.IExpressionVisitor;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.AbstractTokenOperand;
import org.xdb.funsql.compile.tokens.EnumOperandType;
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class SimpleExpression extends AbstractExpression {
	private static final long serialVersionUID = -857048085355641688L;
	
	private AbstractTokenOperand tOper;
	
	//constructors 
	public SimpleExpression() {
		super();
		
		this.type = EnumExprType.SIMPLE_EXPRESSION;
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
	public Set<TokenAttribute> getAttributes() {
		HashSet<TokenAttribute> atts = new HashSet<TokenAttribute>();
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
	public void accept(IExpressionVisitor v) {
		v.visitSimpleExpression(this);		
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
	
}
