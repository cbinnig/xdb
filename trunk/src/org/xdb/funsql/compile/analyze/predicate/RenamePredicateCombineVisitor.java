package org.xdb.funsql.compile.analyze.predicate;

import org.xdb.error.Error;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * Visitor that eliminates duplicate table name from predicates
 * @author A.C.Mueller
 *
 */
public class RenamePredicateCombineVisitor extends AbstractPredicateVisitor {
	private Error e = new Error();
	public RenamePredicateCombineVisitor(AbstractPredicate pred) {
		super(pred);
		
	}

	@Override
	public Error visitAndPredicate(ComplexPredicate cp) {
	
		visit(cp.getPredicate1());
		for(AbstractPredicate absP : cp.getPredicates2()){
			visit(absP);
		}
		return e;
	}

	@Override
	public Error visitOrPredicate(ComplexPredicate cp) {
		//renamePredicate(cp);
		visit(cp.getPredicate1());
		for(AbstractPredicate absP : cp.getPredicates2()){
			visit(absP);
		}
		return e;
	}

	@Override
	public Error visitNotPredicate(ComplexPredicate cp) {
		//renamePredicate(cp);
	
		visit(cp.getPredicate1());
		for(AbstractPredicate absP : cp.getPredicates2()){
			visit(absP);
		}
		return e;
	}

	@Override
	public Error visitSimplePredicate(SimplePredicate sp) {
		renamePredicate(sp);
		return e;
	}
	
	public void renamePredicate(AbstractPredicate absP){

		//rename Attributes
		for(TokenAttribute at :absP.getAttributes()) {
			at.resetTable();
	
		}
	}

}