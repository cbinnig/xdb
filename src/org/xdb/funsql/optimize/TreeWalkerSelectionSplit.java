package org.xdb.funsql.optimize;

import org.xdb.funsql.compile.AbstractTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.TableOperator;

public class TreeWalkerSelectionSplit extends AbstractTreeVisitor {

	public TreeWalkerSelectionSplit(AbstractOperator root) {
		super(root);
	}

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
	public void visitGenericAggregation(GenericAggregation sa) {
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
