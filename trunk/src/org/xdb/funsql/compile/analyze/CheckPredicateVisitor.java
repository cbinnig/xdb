package org.xdb.funsql.compile.analyze;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;

public class CheckPredicateVisitor implements IPredicateVisitor{
	CheckExpressionVisitor v;
	
	@Override
	public Error visitComplexPredicate(ComplexPredicate cp) {
		//TODO
		return new Error(EnumError.NO_ERROR, null);
	}

	@Override
	public Error visitSimplePredicate(SimplePredicate sp) {
		if(sp.getExpr1().getType().equals(EnumExprType.SIMPLE_EXPRESSION))
			this.v.visitSimpleExpression((SimpleExpression) sp.getExpr1());
		else
			this.v.visitComplexExpression((ComplexExpression) sp.getExpr1());	
		//TODO: check sp.getExpr2() 
		return new Error(EnumError.NO_ERROR, null);
	}


}
