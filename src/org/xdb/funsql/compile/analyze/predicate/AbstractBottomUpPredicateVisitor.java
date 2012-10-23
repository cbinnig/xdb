package org.xdb.funsql.compile.analyze.predicate;

import org.xdb.error.Error;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;

public abstract class AbstractBottomUpPredicateVisitor extends AbstractPredicateVisitor {

	@Override
	public Error visit(AbstractPredicate pred) {
		Error e = new Error();
		switch (pred.getType()) {
		case AND_PREDICATE:
		case OR_PREDICATE:
		case NOT_PREDICATE:
			ComplexPredicate cpred = (ComplexPredicate)pred;
			e = visit(cpred.getPredicate1());
			if(e.isError())
				return e;
			
			for(AbstractPredicate childPred: cpred.getPredicates2()){
				e = visit(childPred);
				if(e.isError())
					return e;
			}

		}
		
		e = super.visit(pred);
		
		return e;
	}

}
