package org.xdb.funsql.compile.analyze;

import java.util.HashMap;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenLiteral;
import org.xdb.funsql.types.EnumLiteralType;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.error.EnumError;
import org.xdb.error.Error;

/**
 * Checks if expressions have errors in data types.
 * 
 * @author lschmidt
 * 
 */
public class CheckExpressionVisitor implements IExpressionVisitor {
	HashMap<AbstractToken, EnumSimpleType> expType;
	HashMap<AbstractToken, EnumLiteralType> litType;

	@Override
	public Error visitSimpleExpression(SimpleExpression se) {
		Error e = new Error();
		TokenAttribute ta = null;
		if (se.isAttribute()) {// attribute
			while (se.getAttributes().iterator().hasNext()) {
				ta = se.getAttributes().iterator().next();
				this.expType.put(ta, ta.getDataType());
			}
		} else {// literal
			TokenLiteral tl = (TokenLiteral) se.getOper();
			EnumLiteralType tlt = tl.getLiteralType();
			this.litType.put(tl, tlt);
		}
		return e;
	}

	@Override
	public Error visitComplexExpression(ComplexExpression ce) {
		Error e = new Error();
		if (ce.isAttribute()) {
			// only 1 expression: attribute
			while (ce.getExpr1().getAttributes().iterator().hasNext()) {
				this.visitSimpleExpression((SimpleExpression) ce.getExpr1());
				//sets the data type
			}
		} else if ((!ce.isAttribute()) && (ce.getSize() == 1)) {
			// only 1 expression: literal
			this.visitSimpleExpression((SimpleExpression) ce.getExpr1());
		} else if ((!ce.isAttribute()) && (ce.getSize() > 1)) {// ComplexExpression
			AbstractExpression ae1 = ce.getExpr1();
			EnumSimpleType st = this.expType.get(ae1);
			switch(ce.getType()){
			case SIGNED_EXPRESSION://-(a+1) ->ce.isNegated()
				//data type has to be decimal
				if(st.equals(EnumExprType.SIMPLE_EXPRESSION))
					this.visitSimpleExpression((SimpleExpression) ae1);
				else
					this.visitComplexExpression((ComplexExpression)ae1);
				if(!(st.equals(EnumSimpleType.SQL_DECIMAL))||!(st.equals(EnumSimpleType.SQL_INTEGER))){
					String[] s ={"This type has to be decimal or integer!"};
					return new Error(EnumError.COMPILER_TYPE_ERROR,s);
				}
				for(int i = 0; i< ce.getSize();i++){
					this.visitComplexExpression((ComplexExpression) ce.getExpr2(i));
					EnumSimpleType st2 = this.expType.get( ce.getExpr2(i));
					if(!(st.equals(EnumSimpleType.SQL_DECIMAL))||!(st2.equals(EnumSimpleType.SQL_INTEGER))){
						String[] s ={"This type has to be decimal or integer!"};
						return new Error(EnumError.COMPILER_TYPE_ERROR,s);
					}
				}
			case SIMPLE_EXPRESSION:
				this.visitSimpleExpression((SimpleExpression) ae1);
			case MULT_EXPRESSION://has to be integer or decimal
				if(st.equals(EnumExprType.NO_EXPRESSION)){
					//TODO
				}else if(st.equals(EnumExprType.SIMPLE_EXPRESSION)){
					this.visitSimpleExpression((SimpleExpression) ae1);	
					
					
				}else{
					this.visitComplexExpression((ComplexExpression)ae1);					
				}
				if(!(st.equals(EnumSimpleType.SQL_DECIMAL))||!(st.equals(EnumSimpleType.SQL_INTEGER))){
					String[] s ={"This type has to be decimal or integer!"};
					return new Error(EnumError.COMPILER_TYPE_ERROR,s);
				}
				//expression2
				for(int i = 0; i< ce.getSize();i++){
					AbstractExpression ae2 = ce.getExpr2(i);
					this.visitComplexExpression((ComplexExpression) ae2);
					EnumSimpleType st2 = this.expType.get(ae2);
					if(st2.equals(EnumExprType.NO_EXPRESSION)){
						//TODO
					}else if(st2.equals(EnumExprType.SIGNED_EXPRESSION)){
						//TODO
					}else{
						this.visitComplexExpression((ComplexExpression)ae2);
					}
				}
					
			case ADD_EXPRESSION:
				//TODO
			case NO_EXPRESSION:
				//TODO
				
			}
			String args[] = { "Error in ComplexExpression!" };
			return new Error(EnumError.COMPILER_GENERIC, args);
		}

		return e;
	}

	@Override
	public Error visitAggregationExpression(AggregationExpression ce) {
		return this.visit(ce.getExpression());
	}

	@Override
	public Error visit(AbstractExpression expr) {
		switch(expr.getType()){
		case SIMPLE_EXPRESSION:
			return this.visitSimpleExpression((SimpleExpression)expr);
		case MULT_EXPRESSION:
		case ADD_EXPRESSION:
		case SIGNED_EXPRESSION:
			return this.visitComplexExpression((ComplexExpression)expr);
		case AGG_EXPRESSION:
			return this.visitAggregationExpression((AggregationExpression)expr);
		}
		return null;
	}
}
