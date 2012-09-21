package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.AbstractTokenOperand;

public class SimplePredicate extends AbstractPredicate {
	private static final long serialVersionUID = -857048085355641688L;
	
	private AbstractTokenOperand tOper1;
	private EnumComperator comp;
	private AbstractTokenOperand tOper2;
	
	//constructors 
	public SimplePredicate() {
		super();
		
		this.comp = EnumComperator.SQL_EQUAL;
	}

	public SimplePredicate(AbstractTokenOperand tOper1, AbstractTokenOperand tOper2) {
		this();
		
		this.tOper1 = tOper1;
		this.tOper2 = tOper2;
	}
	
	//getters and setters
	public EnumComperator getComp() {
		return comp;
	}

	public void setComp(EnumComperator comp) {
		this.comp = comp;
	}

	public AbstractTokenOperand getOper1() {
		return tOper1;
	}

	public void setOper1(AbstractTokenOperand tOper1) {
		this.tOper1 = tOper1;
	}

	public AbstractTokenOperand getOper2() {
		return tOper2;
	}

	public void setOper2(AbstractTokenOperand tOper2) {
		this.tOper2 = tOper2;
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
			sqlValue.append(AbstractToken.NOT);
		
		sqlValue.append(AbstractToken.LBRACE);
		sqlValue.append(tOper1.toSqlString());
		sqlValue.append(comp.toString());
		sqlValue.append(tOper2.toSqlString());
		sqlValue.append(AbstractToken.RBRACE);
		
		return sqlValue.toString();
	}
}
