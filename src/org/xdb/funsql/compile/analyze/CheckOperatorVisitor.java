package org.xdb.funsql.compile.analyze;

import java.util.Collection;
import java.util.Vector;

import org.xdb.funsql.compile.ITreeVisitor;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.SimpleAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.error.Error;

/**
 * CheckOperatorVisitor checks the Operators and the used data types.
 * 
 * @author lschmidt
 * 
 */
public class CheckOperatorVisitor implements ITreeVisitor {
	protected Vector<ResultDesc> results;

	@Override
	public void visitEquiJoin(EquiJoin ej) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitFunctionCall(FunctionCall fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitSimpleAggregation(SimpleAggregation sa) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitGenericProjection(GenericProjection sp) {
		//expressions correct?
		Error e = new Error();
		CheckExpressionVisitor expVisitor = new CheckExpressionVisitor();
		for(int i=0;i<sp.getExpressions().size(); i++){
			if(sp.getExpression(i).getType().equals(EnumExprType.SIMPLE_EXPRESSION)){
				e = expVisitor.visitSimpleExpression((SimpleExpression) sp.getExpression(i));
			}
			else{//ComplexExpression
				e = expVisitor.visitComplexExpression((ComplexExpression) sp.getExpression(i));
			}
		}
		//TODO: other checks?
		
		if(e.isError()){
			//TODO: Error handling
		}
	}

	@Override
	public void visitTableOperator(TableOperator to) {
		//TODO
	}

	@Override
	public void visitGenericSelection(GenericSelection es) {
		// TODO Auto-generated method stub

	}

}
