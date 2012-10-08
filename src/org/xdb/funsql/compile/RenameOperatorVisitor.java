package org.xdb.funsql.compile;

import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.SimpleAggregation;
import org.xdb.funsql.compile.operator.TableOperator;

/**
 * renames the operators and attributes for code execution
 * @author lschmidt
 *
 */
public class RenameOperatorVisitor implements TreeVisitor {

	@Override
	public void visitEquiJoin(EquiJoin ej) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitGenericSelection(GenericSelection gs) {
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
	public void visitGenericProjection(GenericProjection gp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTableOperator(TableOperator to) {
		// TODO Auto-generated method stub

	}

}
