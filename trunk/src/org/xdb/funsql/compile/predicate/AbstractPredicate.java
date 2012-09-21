package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.AbstractToken;

public abstract class AbstractPredicate extends AbstractToken{

	private static final long serialVersionUID = -8077067070422642204L;

	protected boolean isNegated = false;
	
	public AbstractPredicate() {
	}
	
	public void negate(){
		this.isNegated = !this.isNegated;
	}
	
	public boolean isNegated(){
		return this.isNegated;
	}
}
