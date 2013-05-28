package org.xdb.funsql.compile.analyze.predicate;

import org.xdb.error.Error;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;

public abstract class AbstractPredicateVisitor implements IPredicateVisitor {
	protected AbstractPredicate pred;

	public AbstractPredicateVisitor(AbstractPredicate pred) {
		super();
		this.pred = pred;
	}

	public Error visit() {
		return this.visit(pred);
	}

	@Override
	public Error visit(AbstractPredicate pred) {
		Error e = new Error();
		switch (pred.getType()) {
		case SIMPLE_PREDICATE:
			return this.visitSimplePredicate((SimplePredicate) pred);
		case AND_PREDICATE:
			return this.visitAndPredicate((ComplexPredicate) pred);
		case OR_PREDICATE:
			return this.visitOrPredicate((ComplexPredicate) pred);
		case NOT_PREDICATE:
			return this.visitNotPredicate((ComplexPredicate) pred);
		default:
			return e;
		}
	}

}
