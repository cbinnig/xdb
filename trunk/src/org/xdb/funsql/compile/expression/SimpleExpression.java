package org.xdb.funsql.compile.expression;

import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.AbstractTokenOperand;

public class SimpleExpression extends AbstractExpression {
	private static final long serialVersionUID = -857048085355641688L;
	
	private AbstractTokenOperand tOper;
	
	//constructors 
	public SimpleExpression() {
		super();
		
		this.type = EnumExprType.SIMPLE_EXPRESSION;
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
}
