package org.xdb.funsql.compile.analyze.expression;

import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.error.Error;

public interface IExpressionVisitor {
	
	Error visit(AbstractExpression expr); 
	Error visitSimpleExpression(SimpleExpression se);
	Error visitMultExpression(ComplexExpression ce);
	Error visitAddExpression(ComplexExpression ce);
	Error visitSignedExpression(ComplexExpression ce);
	Error visitAggregationExpression(AggregationExpression ce);

}
