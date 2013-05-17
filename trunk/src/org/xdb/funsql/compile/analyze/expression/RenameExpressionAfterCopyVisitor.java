package org.xdb.funsql.compile.analyze.expression;

import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * This visitor is used in the context of
 * RebuildExpressionAndAttributesAfterCopyVisitor in order to rebuild the table
 * names of Expression, like AggregationExpression
 * 
 * @author A.C.Mueller
 * 
 */
public class RenameExpressionAfterCopyVisitor extends AbstractExpressionVisitor {

	private Map<String, String> oldToNewIDMap;

	public RenameExpressionAfterCopyVisitor(AbstractExpression expr,
			Map<String, String> oldToNewIDMap) {
		super(expr);
		this.oldToNewIDMap = oldToNewIDMap;
	}

	@Override
	public Error visitSimpleExpression(SimpleExpression se) {
		Error e = new Error();
		if (se.isAttribute()) {
			TokenAttribute att = se.getAttribute();

			String newName = this.oldToNewIDMap.get(att.getTable().getName()
					.getName());


			if (newName != null) {
				att.getTable().setName(newName);
			} 

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
		if (e.isError())
			return e;

		for (AbstractExpression expr2 : ce.getExprs2()) {

			e = visit(expr2);
			if (e.isError())
				return e;
		}

		return e;
	}

	@Override
	public Error visitAggregationExpression(AggregationExpression ce) {
		return visit(ce.getExpression());
	}
}
