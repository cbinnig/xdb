package org.xdb.funsql.compile.checks;

import java.util.Collection;
import java.util.Vector;

import org.xdb.funsql.compile.TreeVisitor;
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

/**
 * CheckOperatorVisitor checks the Operators and their meta data
 * 
 * @author lschmidt
 * 
 */
public class CheckOperatorVisitor implements TreeVisitor {
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
		// TODO
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
