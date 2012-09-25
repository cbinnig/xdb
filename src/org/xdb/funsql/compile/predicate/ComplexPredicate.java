package org.xdb.funsql.compile.predicate;

import java.util.Vector;

import org.xdb.funsql.compile.tokens.AbstractToken;

public class ComplexPredicate extends AbstractPredicate {

	private static final long serialVersionUID = -5584803276387811894L;

	private AbstractPredicate pred1;
	private Vector<EnumBoolOperator> opers;
	private Vector<AbstractPredicate> preds2;

	public ComplexPredicate() {
		super();
		
		this.opers = new Vector<EnumBoolOperator>();
		this.preds2 = new Vector<AbstractPredicate>();
	}

	public ComplexPredicate(EnumPredicateType type) {
		this();

		this.type = type;
	}

	// getter and setter
	public AbstractPredicate getPredicate1() {
		return pred1;
	}

	public void setPredicate1(AbstractPredicate pred1) {
		this.pred1 = pred1;
	}

	public EnumBoolOperator getOperator(int i) {
		return opers.get(i);
	}

	public void addAnd() {
		this.opers.add(EnumBoolOperator.SQL_AND);
	}

	public void addOr() {
		this.opers.add(EnumBoolOperator.SQL_OR);
	}
	
	public void addOp(EnumBoolOperator oper) {
		this.opers.add(oper);
	}

	public AbstractPredicate getPredicate2(int i) {
		return preds2.get(i);
	}

	public void addPredicate2(AbstractPredicate pred2) {
		this.preds2.add(pred2);
	}

	@Override
	public String toString() {
		return this.toSqlString();
	}

	@Override
	public String toSqlString() {
		StringBuffer sqlValue = new StringBuffer();

		if (this.isNegated)
			sqlValue.append(AbstractToken.NOT);

		if (!this.preds2.isEmpty())
			sqlValue.append(AbstractToken.LBRACE);
		
		sqlValue.append(this.pred1);
		
		for(int i=0; i<this.preds2.size(); ++i){
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(this.opers.get(i));
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(this.preds2.get(i).toSqlString());
		}
		
		if (!this.preds2.isEmpty())
			sqlValue.append(AbstractToken.RBRACE);
		
		return sqlValue.toString();
	}
}
