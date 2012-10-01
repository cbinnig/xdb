package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.AbstractToken;

/**
 * Predicate representation for compilation
 * @author cbinnig
 *
 */
public abstract class AbstractPredicate extends AbstractToken{

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
}
