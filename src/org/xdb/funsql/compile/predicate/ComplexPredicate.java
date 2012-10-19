package org.xdb.funsql.compile.predicate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.xdb.funsql.compile.analyze.IPredicateVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;

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

		for (int i = 0; i < this.preds2.size(); ++i) {
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(this.opers.get(i));
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(this.preds2.get(i).toSqlString());
		}

		if (!this.preds2.isEmpty())
			sqlValue.append(AbstractToken.RBRACE);

		return sqlValue.toString();
	}

	@Override
	public Set<TokenAttribute> getAttributes() {
		HashSet<TokenAttribute> atts = new HashSet<TokenAttribute>();
		atts.addAll(this.pred1.getAttributes());

		for (AbstractPredicate pred2 : this.preds2) {
			atts.addAll(pred2.getAttributes());
		}

		return atts;
	}

	@Override
	public boolean isEquiJoinPredicate() {
		if(this.type==EnumPredicateType.NOT_PREDICATE){
			return this.pred1.isEquiJoinPredicate();
		}
		return false;
	}

	@Override
	public Set<AbstractPredicate> splitAnd() {
		HashSet<AbstractPredicate> predicates = new HashSet<AbstractPredicate>();
		if (this.type == EnumPredicateType.OR_PREDICATE) {
			if (this.preds2.size() == 0) {
				return this.pred1.splitAnd();
			} else {
				return predicates;
			}
		} else {
			if (this.type == EnumPredicateType.AND_PREDICATE) {
				predicates.add(this.pred1);
				predicates.addAll(this.preds2);
			} else {
				predicates.add(this);
			}
		}
		return predicates;

	}

	@Override
	public void accept(IPredicateVisitor v) {
		v.visitComplexPredicate(this);
	}
	
	@Override
	public Set<AbstractExpression> getAggregations() {
		HashSet<AbstractExpression> aggExprs = new HashSet<AbstractExpression>();
		aggExprs.addAll(this.pred1.getAggregations());
		for(AbstractPredicate pred2: this.preds2){
			aggExprs.addAll(pred2.getAggregations());
		}
		return aggExprs;
	}

	@Override
	public AbstractPredicate replaceAliases(Map<AbstractExpression, TokenIdentifier> aliases) {
		this.pred1.replaceAliases(aliases);
		
		for(AbstractPredicate pred2: this.preds2){
			pred2.replaceAliases(aliases);
		}
		
		return this;
	}
}
