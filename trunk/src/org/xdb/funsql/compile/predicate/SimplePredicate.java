package org.xdb.funsql.compile.predicate;

import java.util.HashSet;
import java.util.Set;

import org.xdb.funsql.compile.analyze.IPredicateVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class SimplePredicate extends AbstractPredicate {
	private static final long serialVersionUID = -857048085355641688L;

	private AbstractExpression expr1;
	private EnumCompOperator comp;
	private AbstractExpression expr2;

	// constructors
	public SimplePredicate() {
		super();

		this.type = EnumPredicateType.SIMPLE_PREDICATE;
	}

	// getters and setters
	public EnumCompOperator getComp() {
		return comp;
	}

	public void setComp(EnumCompOperator comp) {
		this.comp = comp;
	}

	public AbstractExpression getExpr1() {
		return expr1;
	}

	public void setExpr1(AbstractExpression expr1) {
		this.expr1 = expr1;
	}

	public AbstractExpression getExpr2() {
		return expr2;
	}

	public void setExpr2(AbstractExpression expr2) {
		this.expr2 = expr2;
	}

	// helper methods
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
		sqlValue.append(expr1.toSqlString());
		sqlValue.append(comp.toString());
		sqlValue.append(expr2.toSqlString());
		sqlValue.append(AbstractToken.RBRACE);

		return sqlValue.toString();
	}

	@Override
	public Set<TokenAttribute> getAttributes() {
		HashSet<TokenAttribute> atts = new HashSet<TokenAttribute>();
		atts.addAll(this.expr1.getAttributes());
		atts.addAll(this.expr2.getAttributes());
		return atts;
	}

	@Override
	public boolean isEquiJoinPredicate() {
		if (this.comp != EnumCompOperator.SQL_EQUAL)
			return false;
		else if (!this.expr1.isAttribute())
			return false;
		else if (!this.expr2.isAttribute())
			return false;
		else {
			TokenAttribute[] leftAtt = this.expr1.getAttributes().toArray(
					new TokenAttribute[1]);
			TokenAttribute[] rightAtt = this.expr2.getAttributes().toArray(
					new TokenAttribute[1]);

			if (leftAtt[0].getTable() == null || rightAtt[0].getTable() == null){
				return false;
			}
			else if (leftAtt[0].getTable().equals(rightAtt[0].getTable())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Set<AbstractPredicate> splitAnd() {
		HashSet<AbstractPredicate> predicates = new HashSet<AbstractPredicate>();
		predicates.add(this);
		return predicates;
	}

	@Override
	public void accept(IPredicateVisitor v) {
		v.visitSimplePredicate(this);
		
	}

	@Override
	public Set<AbstractExpression> getAggregations() {
		HashSet<AbstractExpression> aggExprs = new HashSet<AbstractExpression>();
		if(this.expr1.isAggregation())
			aggExprs.add(this.expr1);
		if(this.expr2.isAggregation())
			aggExprs.add(this.expr2);
		return aggExprs;
	}
}
