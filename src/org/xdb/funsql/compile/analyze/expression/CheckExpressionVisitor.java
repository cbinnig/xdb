package org.xdb.funsql.compile.analyze.expression;

import java.util.Map;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenLiteral;
import org.xdb.funsql.types.EnumSimpleType;


/**
 * Checks if expressions have errors in data types.
 * 
 * @author lschmidt
 * 
 */
public class CheckExpressionVisitor extends AbstractExpressionVisitor {
	private Map<AbstractToken, EnumSimpleType> expType;
	
	public CheckExpressionVisitor(
			Map<AbstractToken, EnumSimpleType> expType,
			AbstractExpression expr) {
		super(expr);
		this.expType = expType;
	}

	@Override
	public Error visitSimpleExpression(SimpleExpression se) {
		Error e = new Error();
		if(se.isAttribute()){
			TokenAttribute ta = se.getAttribute();
			EnumSimpleType type = this.expType.get(ta);
			this.expType.put(se, type);
		}
		else if(se.isLiteral()){
			TokenLiteral lit = (TokenLiteral)se.getOper();
			this.expType.put(se, lit.getDataType());
		}
		else{ //expression is STAR
			this.expType.put(se, EnumSimpleType.SQL_INTEGER);
		}
		return e;
	}

	@Override
	public Error visitAggregationExpression(AggregationExpression aggExpr) {
		Error e = this.visit(aggExpr.getExpression());
		if (aggExpr.getAggregation().isCnt()) {
			this.expType.put(aggExpr, EnumSimpleType.SQL_INTEGER);
		}
		else{
			AbstractExpression expr1 = aggExpr.getExpression();
			e = visit(expr1);
			if(e.isError())
				return e;
			this.expType.put(aggExpr, this.expType.get(aggExpr.getExpression()));
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

		AbstractExpression expr1 = ce.getExpr1();
		e = visit(expr1);
		if(e.isError())
			return e;
		
		EnumSimpleType type1 = this.expType.get(expr1);
		for (AbstractExpression expr2 : ce.getExprs2()) {
			e = visit(expr2);
			if(e.isError())
				return e;
			
			EnumSimpleType type2 = this.expType.get(expr2);
			EnumSimpleType temp = EnumSimpleType.promote(type1, type2);
			if (!type1.isSet()) {
				String[] args = { type1.toString(), type2.toString() };
				return new Error(EnumError.COMPILER_SELECT_DATATYPE_MISMATCH,
						args);
			}
			type1 = temp;
		}

		this.expType.put(ce, type1);

		return e;
	}
}
