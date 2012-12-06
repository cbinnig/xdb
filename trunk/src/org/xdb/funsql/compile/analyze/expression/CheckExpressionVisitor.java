package org.xdb.funsql.compile.analyze.expression;

import java.util.Map;

import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.EnumAggregation;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenLiteral;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.error.EnumError;
import org.xdb.error.Error;


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
			TokenAttribute tAtt = se.getAttribute();
			EnumSimpleType type = this.expType.get(tAtt);
			this.expType.put(se, type);
		}
		else{
			TokenLiteral lit = (TokenLiteral)se.getOper();
			this.expType.put(se, lit.getDataType());
		}
		return e;
	}

	@Override
	public Error visitAggregationExpression(AggregationExpression ce) {
		Error e = this.visit(ce.getExpression());
		if (ce.getAggregation() == EnumAggregation.CNT) {
			this.expType.put(ce, EnumSimpleType.SQL_INTEGER);
		}
		else{
			this.expType.put(ce, this.expType.get(ce.getExpression()));
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
