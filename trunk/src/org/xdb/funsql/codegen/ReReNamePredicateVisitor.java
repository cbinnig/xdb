package org.xdb.funsql.codegen;

import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.predicate.AbstractPredicateVisitor;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * @author a.c.mueller
 * This Class is a visitor that renames all predicates to enable more efficient table op handling
 */
public class ReReNamePredicateVisitor extends AbstractPredicateVisitor {

	private Error e = new Error();
	private Map<String, String> renamedAttributes;
	public ReReNamePredicateVisitor(AbstractPredicate pred, Map<String, String> renamedAttributes) {
		super(pred);
		this.renamedAttributes = renamedAttributes;
	}

	@Override
	public Error visitAndPredicate(ComplexPredicate cp) {
		//renamePredicate(cp);
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
		String newName ="";
		//rename Attributes
		for(TokenAttribute at :absP.getAttributes()) {
			newName = renamedAttributes.get(at.getName());
			//if no new name found it wasn't renamed so continue
			if(newName == null) continue;
			at.setName(newName);
		}
	}

}
