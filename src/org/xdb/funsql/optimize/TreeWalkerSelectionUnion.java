package org.xdb.funsql.optimize;

import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.SimpleAggregation;
import org.xdb.funsql.compile.operator.TableOperator;

public class TreeWalkerSelectionUnion extends AbstractTreeWalker {
	
	public TreeWalkerSelectionUnion(AbstractOperator root) {
		super(root);
	}
	
	@Override
	public void visitEquiJoin(EquiJoin ej) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitGenericSelection(GenericSelection gs) {
		switch (gs.getChild().getType()){
		case GENERIC_SELECTION:
			
			break;
		}


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
