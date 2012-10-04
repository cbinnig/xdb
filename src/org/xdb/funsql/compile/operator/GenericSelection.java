package org.xdb.funsql.compile.operator;

import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.predicate.AbstractPredicate;

public class GenericSelection extends AbstractUnaryOperator {
	private static final long serialVersionUID = 5178586492851005421L;
	
	private AbstractPredicate predicate;
	
	//constructors
	public GenericSelection(AbstractOperator child) {
		super(child);
		this.type = EnumOperator.GENERIC_SELECTION;
	}

	//getters and setters
	public AbstractPredicate getPredicate() {
		return predicate;
	}

	public void setPredicate(AbstractPredicate predicate) {
		this.predicate = predicate;
	}	
	
	@Override
	public String toSqlString() {
		// TODO: generate sql
		return null;
	}

	@Override
	void accept(TreeVisitor v) {
		v.visitGenericSelection(this);
	}
}
