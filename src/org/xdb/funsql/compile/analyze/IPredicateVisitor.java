package org.xdb.funsql.compile.analyze;

import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.error.Error;

public interface IPredicateVisitor {

	Error visitComplexPredicate(ComplexPredicate cp);
	Error visitSimplePredicate(SimplePredicate sp);
}
