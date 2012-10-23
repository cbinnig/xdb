package org.xdb.funsql.compile.analyze.predicate;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.expression.RenameExpressionVisitor;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.utils.Identifier;

public class RenamePredicateVisitor extends AbstractPredicateVisitor {
	private Identifier operatorId;
	
	public RenamePredicateVisitor(AbstractPredicate pred, Identifier operatorId) {
		super(pred);
		
		this.operatorId = operatorId;
	}


	@Override
	public Error visitSimplePredicate(SimplePredicate sp) {
		RenameExpressionVisitor renameVisitor1 = new RenameExpressionVisitor(sp.getExpr1(), this.operatorId);
		Error e = renameVisitor1.visit();
		if(e.isError())
			return e;
		
		RenameExpressionVisitor renameVisitor2 = new RenameExpressionVisitor(sp.getExpr2(), this.operatorId);
		e = renameVisitor2.visit();
		if(e.isError())
			return e;
		
		
		return e;
	}
	
	@Override
	public Error visitAndPredicate(ComplexPredicate cp) {
		return visiComplexPredicate(cp);
	}

	@Override
	public Error visitOrPredicate(ComplexPredicate cp) {
		return visiComplexPredicate(cp);
	}

	@Override
	public Error visitNotPredicate(ComplexPredicate cp) {
		return visiComplexPredicate(cp);
	}

	private Error visiComplexPredicate(ComplexPredicate cp) {
		Error e = new Error();
		e = visit(cp.getPredicate1());
		if(e.isError())
			return e;
		
		for(AbstractPredicate pred2: cp.getPredicates2()){
			e = visit(pred2);
			if(e.isError())
				return e;
		}
		return e;
	}


}
