package org.xdb.funsql.compile.operator;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.expression.AbstractExpressionVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class RenameExpressionCombineVisitor extends AbstractExpressionVisitor {

	public RenameExpressionCombineVisitor(AbstractExpression expr) {
		super(expr);

	}

	@Override
	public Error visitSimpleExpression(SimpleExpression se) {
		Error e = new Error();
		if(se.isAttribute()){
			TokenAttribute att = se.getAttribute();
			if(att.getTable()!=null);
				att.resetTable();
		}
		return e;
	}

	@Override
	public Error visitMultExpression(ComplexExpression ce) {
		return visitComplexExpression(ce);
	}

	@Override
	public Error visitAddExpression(ComplexExpression ce) {
		return visitComplexExpression(ce);
	}

	@Override
	public Error visitSignedExpression(ComplexExpression ce) {
		return visitComplexExpression(ce);
	}

	private Error visitComplexExpression(ComplexExpression ce) {
		Error e = new Error();
		
		e = visit(ce.getExpr1());
		if(e.isError())
			return e;
		
		for(AbstractExpression expr2: ce.getExprs2()){

			e = visit(expr2);
			if(e.isError())
				return e;
		}
		
		return e;
	}

	@Override
	public Error visitAggregationExpression(AggregationExpression ce) {
		return visit(ce.getExpression());
	}
}
