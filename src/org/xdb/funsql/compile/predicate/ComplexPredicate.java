package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.AbstractToken;

public class ComplexPredicate extends AbstractPredicate {

	private static final long serialVersionUID = -5584803276387811894L;

	private AbstractPredicate pred1;
	private EnumBoolOperator oper;
	private AbstractPredicate pred2;

	public ComplexPredicate() {
		super();
	}

	public ComplexPredicate(EnumBoolOperator oper) {
		super();

		this.oper = oper;
	}

	public ComplexPredicate(AbstractPredicate pred1, AbstractPredicate pred2,
			EnumBoolOperator oper) {
		this(oper);
		this.pred1 = pred1;
		this.pred2 = pred2;
	}

	// getter and setter
	public AbstractPredicate getPredicate1() {
		return pred1;
	}

	public void setPredicate1(AbstractPredicate pred1) {
		this.pred1 = pred1;
	}

	public EnumBoolOperator getOperator() {
		return oper;
	}

	public void setAnd() {
		this.oper = EnumBoolOperator.SQL_AND;
	}

	public void setOr() {
		this.oper = EnumBoolOperator.SQL_OR;
	}

	public AbstractPredicate getPredicate2() {
		return pred2;
	}

	public void setPredicate2(AbstractPredicate pred2) {
		this.pred2 = pred2;
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

		sqlValue.append(AbstractToken.LBRACE);
		sqlValue.append(this.pred1);
		if (this.pred2 != null) {
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(this.oper.toString());
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(this.pred2);
		}
		sqlValue.append(AbstractToken.RBRACE);

		return sqlValue.toString();
	}
}
