package org.xdb.funsql.compile.expression;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;

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
	
	public Vector<AbstractExpression> getExprs2() {
		return exprs2;
	}

	public void addExpr2(AbstractExpression expr2) {
		this.exprs2.add(expr2);
	}

	// methods
	@Override
	public String toString() {
		return this.toSqlString();
	}
	
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

	@Override
	public Collection<TokenAttribute> getAttributes() {
		Collection<TokenAttribute> atts1 = this.expr1.getAttributes();
		Vector<TokenAttribute> atts = new Vector<TokenAttribute>(atts1);
		
		for(AbstractExpression expr2: this.exprs2){
			atts.addAll(expr2.getAttributes());
		}
		return atts;
	}

	@Override
	public boolean isAttribute() {
		if(this.exprs2.size()==0){
			return this.expr1.isAttribute();
		}
		return false;
	}

	@Override
	public boolean isAggregation() {
		if(this.expr1.isAggregation())
			return true;
		
		for(AbstractExpression expr2: this.exprs2){
			if(expr2.isAggregation())
				return true;
		}
		
		return false;
	}

	@Override
	public TokenAttribute getAttribute() {
		if(this.exprs2.size()==0){
			return this.expr1.getAttribute();
		}
		return null;
	}

	@Override
	public int size() {
		return 1 + this.exprs2.size();
	}

	@Override
	public AbstractExpression clone() {
		ComplexExpression expr = new ComplexExpression(this.type);
		
		if(this.expr1!=null)
			expr.expr1 = this.expr1.clone();
		
		expr.ops = new Vector<EnumExprOperator>(this.ops);
		
		expr.exprs2 = new Vector<AbstractExpression>(this.exprs2.size());
		for(AbstractExpression expr2: this.exprs2){
			expr.exprs2.add(expr2.clone());
		}
		
		return expr;
	}

	@Override
	public AbstractExpression replaceExpressions(
			Map<TokenIdentifier, AbstractExpression> exprs) {
		this.expr1 = this.expr1.replaceExpressions(exprs);
		
		int i=0;
		Vector<AbstractExpression> expr2Tmp = new Vector<AbstractExpression>(this.exprs2);
		for(AbstractExpression expr2: expr2Tmp){
			this.exprs2.set(i, expr2.replaceExpressions(exprs));
			i++;
		}
		return this;
	}
}