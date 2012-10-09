package org.xdb.funsql.compile.checks;

import java.util.Vector;

import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.tokens.AbstractTokenOperand;
import org.xdb.funsql.compile.tokens.TokenAttribute;
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
		TokenAttribute ta = null;
		if (se.isAttribute() && (se.getAttributes().size() == 0)) {
			String args[] = { "No attribute available in SimpleExpression though isAttribute()==true!" };
			return new Error(EnumError.COMPILER_GENERIC, args);
		} else if (se.isAttribute()) {// attribute
			while (se.getAttributes().iterator().hasNext()) {
				ta = se.getAttributes().iterator().next();
				this.checkAttribute(ta);
			}
		} else {// literal
				// TODO: check literals
		}
		return new Error(EnumError.NO_ERROR, null);
	}

	@Override
	public Error visitComplexExpression(ComplexExpression ce) {
		Error e;
		if (ce.isAttribute() && ce.getAttributes().size() == 0) {
			String args[] = { "No attribute available in ComplexExpression though isAttribute()==true!" };
			return new Error(EnumError.COMPILER_GENERIC, args);
		} else if (ce.isAttribute()) {// only 1 expression: attribute
			while (ce.getExpr1().getAttributes().iterator().hasNext()) {
				TokenAttribute ta = ce.getExpr1().getAttributes().iterator()
						.next();
				e = this.checkAttribute(ta);
				if (e.isError())
					return e;
			}
		} else if ((!ce.isAttribute()) && (ce.getSize() == 1)) {// only 1
																// expression:
																// literal
			// TODO: check
		} else if ((!ce.isAttribute()) && (ce.getSize() > 1)) {// ComplexExpression
			// TODO: check
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
