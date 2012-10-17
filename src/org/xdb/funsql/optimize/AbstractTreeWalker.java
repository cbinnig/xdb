package org.xdb.funsql.optimize;

import org.xdb.funsql.compile.ITreeVisitor;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.SimpleAggregation;
import org.xdb.funsql.compile.operator.TableOperator;

public abstract class AbstractTreeWalker implements ITreeVisitor {
	
	AbstractOperator thisRoot = null;
	
	public AbstractTreeWalker(AbstractOperator root) {
		thisRoot = root;
	}
	
	public void runWalker() {
		visit(thisRoot);
	}

	@Override
	public void visit(AbstractOperator absOp) {
		switch(absOp.getType()){
		case EQUI_JOIN:
			visitEquiJoin((EquiJoin) absOp);
			break;
		case GENERIC_SELECTION:
			visitGenericSelection((GenericSelection) absOp);
			break;
//		case FUNCTION_CALL:
//			visitFunctionCall((FunctionCall) absOp);
//			break;
		case SIMPLE_AGGREGATION:
			visitSimpleAggregation((SimpleAggregation) absOp);
			break;
//		case GENERIC_PROJECTION:
		case SIMPLE_PROJECTION:
			visitGenericProjection((GenericProjection) absOp);
			break;
		case TABLE:
			visitTableOperator((TableOperator) absOp);
			break;
		}
	}

	public abstract void visitEquiJoin(EquiJoin ej);
	
	public abstract void visitGenericSelection(GenericSelection gs);
	
	public abstract void visitFunctionCall(FunctionCall fc);

	public abstract void visitSimpleAggregation(SimpleAggregation sa);

	public abstract void visitGenericProjection(GenericProjection gp);

	public abstract void visitTableOperator(TableOperator to);
}
