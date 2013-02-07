package org.xdb.funsql.codegen;

import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.expression.AbstractExpressionVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * @author a.c.mueller
 * A Visitor that renames all expressions to allow simple more efficient Table handling
 *
 */
public class ReReNameExpressionVisitor extends AbstractExpressionVisitor {
	private HashMap<String, String> renamedAttributes;
	public ReReNameExpressionVisitor(AbstractExpression expr, HashMap<String, String> renamedAttributes) {
		super(expr);
		this.renamedAttributes = renamedAttributes;
	}

	@Override
	public Error visitSimpleExpression(SimpleExpression se) {
		Error e = new Error();
		if(se.isAttribute()){
			TokenAttribute att = se.getAttribute();
			String newName= renamedAttributes.get(att.getName());
			if(newName == null)
				return e;
				
			att.setName(newName);
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
