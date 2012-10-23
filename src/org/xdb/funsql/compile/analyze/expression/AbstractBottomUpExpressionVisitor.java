package org.xdb.funsql.compile.analyze.expression;

import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;

public abstract class AbstractBottomUpExpressionVisitor extends AbstractExpressionVisitor {

	@Override
	public Error visit(AbstractExpression expr) {
		Error e = new Error();
		switch (expr.getType()) {
		case MULT_EXPRESSION:
		case ADD_EXPRESSION:
		case SIGNED_EXPRESSION:
			ComplexExpression cExpr = (ComplexExpression)expr;
			e = visit(cExpr.getExpr1());
			if(e.isError())
				return e;
			
			for(AbstractExpression childExpr: cExpr.getExprs2()){
				e = visit(childExpr);
				if(e.isError())
					return e;
			}
			
			break;
		case AGG_EXPRESSION:
			AggregationExpression aExpr = (AggregationExpression)expr;
			e = visit(aExpr.getExpression());
			if(e.isError())
				return e;
			break;
		}
		
		e = super.visit(expr);
		
		return e;
	}
}
