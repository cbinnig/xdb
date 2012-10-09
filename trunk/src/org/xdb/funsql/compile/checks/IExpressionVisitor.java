package org.xdb.funsql.compile.checks;

import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.error.Error;

public interface IExpressionVisitor {
	
	Error visitSimpleExpression(SimpleExpression se);
	Error visitComplexExpression(ComplexExpression ce);

}
