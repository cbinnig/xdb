package org.xdb.funsql.compile.analyze.operator;

import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.expression.CheckExpressionVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.types.EnumSimpleType;

/**
 * CheckOperatorVisitor checks the Operators and the used data types.
 * 
 * @author lschmidt
 * 
 */
public class CheckOperatorVisitor extends AbstractBottomUpTreeVisitor {
	private Map<AbstractToken, EnumSimpleType> expType;

	public CheckOperatorVisitor(Map<AbstractToken, EnumSimpleType> expType,
			AbstractOperator root) {
		super(root);

		this.expType = expType;
	}

	// methods
	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		Error e = new Error();

		return e;
	}

	@Override
	public Error visitFunctionCall(FunctionCall fc) {
		Error e = new Error();

		return e;

	}

	@Override
	public Error visitGenericAggregation(GenericAggregation ga) {
		Error e = new Error();
		
		int i=0;
		for (AbstractExpression expr : ga.getAggregationExpressions()) {
			CheckExpressionVisitor check = new CheckExpressionVisitor(
					this.expType, expr);
			e = check.visit();
			if (e.isError())
				return e;
			
			TokenAttribute att = new TokenAttribute(ga.getAlias(i));
			this.expType.put(att, this.expType.get(expr));
			++i;
			
		}

		for (AbstractExpression expr : ga.getGroupExpressions()) {
			CheckExpressionVisitor check = new CheckExpressionVisitor(
					this.expType, expr);
			e = check.visit();
			if (e.isError())
				return e;
			
			TokenAttribute att = new TokenAttribute(ga.getAlias(i));
			this.expType.put(att, this.expType.get(expr));
			++i;
		}
		return e;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		Error e = new Error();

		for (AbstractExpression expr : gp.getExpressions()) {
			CheckExpressionVisitor check = new CheckExpressionVisitor(
					this.expType, expr);
			e = check.visit();
			if (e.isError())
				return e;
		}

		return e;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		Error e = new Error();
		return e;
	}

	@Override
	public Error visitGenericSelection(GenericSelection es) {
		Error e = new Error();

		return e;
	}
}
