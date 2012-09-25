package org.xdb.funsql.compile.expression;

import java.util.Vector;

import org.xdb.funsql.compile.tokens.AbstractToken;

public class ComplexExpression extends AbstractExpression {

	private static final long serialVersionUID = -9124620590454450656L;

	private AbstractExpression expr1;
	private Vector<EnumExprOperator> ops;
	private Vector<AbstractExpression> exprs2;

	public ComplexExpression() {
		super();
		
		this.ops = new Vector<EnumExprOperator>();
		this.exprs2 = new Vector<AbstractExpression>();
	}
	
	public ComplexExpression(EnumExprType type) {
		this();
		
		this.type=type;
	}

	// getters and setters
	public AbstractExpression getExpr1() {
		return expr1;
	}

	public void setExpr1(AbstractExpression expr1) {
		this.expr1 = expr1;
	}

	public EnumExprOperator getOp(int i) {
		return ops.get(i);
	}

	public void addOp(EnumExprOperator op) {
		this.ops.add(op);
	}

	public AbstractExpression getExpr2(int i) {
		return exprs2.get(i);
	}

	public void addExpr2(AbstractExpression expr2) {
		this.exprs2.add(expr2);
	}

	// methods
	@Override
	public String toSqlString() {
		StringBuffer sqlValue = new StringBuffer();
		if (this.isNegated)
			sqlValue.append(AbstractToken.MINUS);

		if (!this.exprs2.isEmpty()) {
			sqlValue.append(AbstractToken.LBRACE);
		}

		sqlValue.append(expr1.toSqlString());

		for(int i=0; i<this.exprs2.size(); ++i){
			sqlValue.append(this.ops.get(i).toString());
			sqlValue.append(this.exprs2.get(i).toSqlString());
		}
		
		if (!this.exprs2.isEmpty()) {
			sqlValue.append(AbstractToken.RBRACE);
		}

		return sqlValue.toString();
	}
}
