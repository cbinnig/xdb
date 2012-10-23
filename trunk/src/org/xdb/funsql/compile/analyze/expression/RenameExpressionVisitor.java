package org.xdb.funsql.compile.analyze.expression;

import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;

public class RenameExpressionVisitor extends AbstractExpressionVisitor {
	private Identifier operatorId;
	
	public RenameExpressionVisitor(AbstractExpression expr, Identifier operatorId) {
		super(expr);
		
		this.operatorId = operatorId;
	}

	@Override
	public Error visitSimpleExpression(SimpleExpression se) {
		Error e = new Error();
		if(se.isAttribute()){
			TokenAttribute att = se.getAttribute();
			if(att.getTable()!=null)
				att.setName(ResultDesc.createResultAtt(att.getTable().getName().toSqlString(), att.getName().toSqlString()));
			att.setTable(operatorId.toString());
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
