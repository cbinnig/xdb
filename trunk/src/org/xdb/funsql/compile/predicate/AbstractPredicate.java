package org.xdb.funsql.compile.predicate;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;

/**
 * Predicate representation for compilation
 * @author cbinnig
 *
 */
public abstract class AbstractPredicate extends AbstractToken implements Cloneable{

	private static final long serialVersionUID = -8077067070422642204L;

	protected boolean isNegated = false;
	protected EnumPredicateType type;
	
	public AbstractPredicate() {
		this.type = EnumPredicateType.NO_PREDICATE;
	}
	
	public void negate(){
		this.isNegated = !this.isNegated;
	}
	
	public boolean isNegated(){
		return this.isNegated;
	}

	public EnumPredicateType getType() {
		return type;
	}

	public void setType(EnumPredicateType type) {
		this.type = type;
	}
	
	public abstract Collection<TokenAttribute> getAttributes();
	
	public abstract boolean isEquiJoinPredicate();
	
	public abstract Set<AbstractPredicate> splitAnd();
	
	public abstract Set<AbstractExpression> getAggregations();
	
	public abstract AbstractPredicate replaceExpressions(Map<TokenIdentifier,AbstractExpression> exprs);
	
	public abstract AbstractPredicate replaceAliases(Map<AbstractExpression, TokenIdentifier> aliases);
	
	public abstract AbstractPredicate clone();
}
