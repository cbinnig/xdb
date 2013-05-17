package org.xdb.funsql.compile.analyze.predicate;

import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * This Visitor is used in the Context of the
 * RebuildExpressionandAttributesAfterCopyVisitor in order to rebuild the
 * predicate of selection Operators.
 * 
 * @author A.C.Mueller
 * 
 */
public class RebuildPredicatesAfterCopyVisitor extends AbstractPredicateVisitor {

	private Error e = new Error();
	private Map<String, String> oldToNewIDMap;

	public RebuildPredicatesAfterCopyVisitor(AbstractPredicate pred,
			Map<String, String> oldToNewIDMap) {
		super(pred);
		this.oldToNewIDMap = oldToNewIDMap;
	}

	@Override
	public Error visitAndPredicate(ComplexPredicate cp) {

		visit(cp.getPredicate1());
		for (AbstractPredicate absP : cp.getPredicates2()) {
			visit(absP);
		}
		return e;
	}

	@Override
	public Error visitOrPredicate(ComplexPredicate cp) {

		visit(cp.getPredicate1());
		for (AbstractPredicate absP : cp.getPredicates2()) {
			visit(absP);
		}
		return e;
	}

	@Override
	public Error visitNotPredicate(ComplexPredicate cp) {
		visit(cp.getPredicate1());
		for (AbstractPredicate absP : cp.getPredicates2()) {
			visit(absP);
		}
		return e;
	}

	@Override
	public Error visitSimplePredicate(SimplePredicate sp) {
		renamePredicate(sp);
		return e;
	}

	public void renamePredicate(AbstractPredicate absP) {
		String newName = "";
		// rename Attributes
		for (TokenAttribute at : absP.getAttributes()) {

			newName = oldToNewIDMap.get(at.getTable().getName().getName());
			// if no new name found it wasn't renamed so continue
			if (newName == null)
				continue;
			at.getTable().setName(newName);
		}

	}

}
