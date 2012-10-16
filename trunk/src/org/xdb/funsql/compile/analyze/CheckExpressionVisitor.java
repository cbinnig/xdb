package org.xdb.funsql.compile.analyze;

import java.util.Vector;

import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprOperator;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.tokens.AbstractTokenOperand;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenLiteral;
import org.xdb.funsql.compile.tokens.TokenIntegerLiteral;
import org.xdb.funsql.types.EnumLiteralType;
import org.xdb.error.EnumError;
import org.xdb.error.Error;

/**
 * Checks if expressions have errors in data types.
 * 
 * @author lschmidt
 * 
 */
public class CheckExpressionVisitor implements IExpressionVisitor {
	@Override
	public Error visitSimpleExpression(SimpleExpression se) {
		Error e;
		TokenAttribute ta = null;
		if (se.isAttribute()) {// attribute
			while (se.getAttributes().iterator().hasNext()) {
				ta = se.getAttributes().iterator().next();
				e = this.checkAttribute(ta);
				if (e.isError())
					return e;
			}
		} else {// literal
			TokenLiteral tl = (TokenLiteral) se.getOper();
			EnumLiteralType tlt = tl.getLiteralType();
			if (tlt.equals("SQL_STRING_LITERAL")) {

			} else if (tlt.equals("SQL_INTEGER_LITERAL")) {
				Integer i = ((TokenIntegerLiteral) tl).getValue();
			}
		}
		return new Error(EnumError.NO_ERROR, null);
	}

	@Override
	public Error visitComplexExpression(ComplexExpression ce) {
		Error e;
		if (ce.isAttribute()) {// only 1 expression: attribute
			while (ce.getExpr1().getAttributes().iterator().hasNext()) {
				this.visitSimpleExpression((SimpleExpression) ce.getExpr1());
			}
		} else if ((!ce.isAttribute()) && (ce.getSize() == 1)) {
			// only 1 expression: literal
			this.visitSimpleExpression((SimpleExpression) ce.getExpr1());
		} else if ((!ce.isAttribute()) && (ce.getSize() > 1)) {// ComplexExpression
			AbstractExpression ae1 = ce.getExpr1();
			switch(ce.getType()){
			case SIGNED_EXPRESSION:
				//TODO
			case SIMPLE_EXPRESSION:
				this.visitSimpleExpression((SimpleExpression) ae1);
			case MULT_EXPRESSION:
				if(ae1.getType().equals(EnumExprType.NO_EXPRESSION)){
					//TODO
				}else if(ae1.getType().equals(EnumExprType.SIMPLE_EXPRESSION)){
					this.visitSimpleExpression((SimpleExpression) ae1);
				}else{
					this.visitComplexExpression((ComplexExpression)ae1);
				}
				AbstractExpression ae2 = ce.getExpr2(0);
				if(ae2.getType().equals(EnumExprType.NO_EXPRESSION)){
					//TODO
				}else if(ae2.getType().equals(EnumExprType.SIGNED_EXPRESSION)){
					//TODO
				}else{
					this.visitComplexExpression((ComplexExpression)ae2);
				}
			case ADD_EXPRESSION:
				//TODO
			case NO_EXPRESSION:
				//TODO
			}
		} else {
			String args[] = { "Error in ComplexExpression!" };
			return new Error(EnumError.COMPILER_GENERIC, args);
		}

		return new Error(EnumError.NO_ERROR, null);
	}

	private Error checkAttribute(TokenAttribute ta) {
		// TODO
		return new Error(EnumError.NO_ERROR, null);
	}

}
