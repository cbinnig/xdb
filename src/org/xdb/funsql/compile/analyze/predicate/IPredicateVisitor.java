package org.xdb.funsql.compile.analyze.predicate;

import org.xdb.error.Error;

import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;

public interface IPredicateVisitor {
	Error visit(AbstractPredicate pred); 
	Error visitAndPredicate(ComplexPredicate cp);
	Error visitOrPredicate(ComplexPredicate cp);
	Error visitNotPredicate(ComplexPredicate cp);
	Error visitSimplePredicate(SimplePredicate sp);
}
