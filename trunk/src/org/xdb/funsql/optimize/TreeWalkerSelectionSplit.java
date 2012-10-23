package org.xdb.funsql.optimize;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractTreeVisitor;
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
	public Error visitEquiJoin(EquiJoin ej) {
		Error e = new Error();
		return e;

	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		Error e = new Error();
		return e;

	}

	@Override
	public Error visitFunctionCall(FunctionCall fc) {
		Error e = new Error();
		return e;

	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		Error e = new Error();
		return e;

	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		Error e = new Error();
		return e;

	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		Error e = new Error();
		return e;
	}

}
