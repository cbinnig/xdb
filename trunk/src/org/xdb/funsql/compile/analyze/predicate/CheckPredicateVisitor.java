package org.xdb.funsql.compile.analyze.predicate;

import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.expression.CheckExpressionVisitor;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.types.EnumSimpleType;


public class CheckPredicateVisitor extends AbstractPredicateVisitor{
	private Map<AbstractToken, EnumSimpleType> expType;
	private AbstractPredicate pred;
	
	public CheckPredicateVisitor(
			Map<AbstractToken, EnumSimpleType> expType,
			AbstractPredicate pred) {
		super();
		this.expType = expType;
		this.pred = pred;
	}
	
	public Error visit(){
		return this.visit(pred);
	}

	@Override
	public Error visitSimplePredicate(SimplePredicate sp) {
		CheckExpressionVisitor checkExpr1 = new CheckExpressionVisitor(this.expType, sp.getExpr1());
		Error e  = checkExpr1.visit();
		if(e.isError())
			return e;
		
		CheckExpressionVisitor checkExpr2 = new CheckExpressionVisitor(this.expType, sp.getExpr2());
		e  = checkExpr2.visit();
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
