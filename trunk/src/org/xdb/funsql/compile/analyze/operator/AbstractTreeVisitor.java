package org.xdb.funsql.compile.analyze.operator;


import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.TableOperator;

public abstract class AbstractTreeVisitor implements ITreeVisitor {
	
	AbstractOperator thisRoot = null;
	
	public AbstractTreeVisitor(AbstractOperator root) {
		thisRoot = root;
	}

	public Error visit(){
		return this.visit(thisRoot);
	}
	
	@Override
	public Error visit(AbstractOperator absOp) {
		Error e = new Error();
		switch(absOp.getType()){
		case EQUI_JOIN:
			return visitEquiJoin((EquiJoin) absOp);
		case GENERIC_SELECTION:
			return visitGenericSelection((GenericSelection) absOp);
//		case FUNCTION_CALL:
//			visitFunctionCall((FunctionCall) absOp);
//			break;
		case GENERIC_AGGREGATION:
			return visitGenericAggregation((GenericAggregation) absOp);
		case GENERIC_PROJECTION:
			visitGenericProjection((GenericProjection) absOp);
			break;
		case TABLE:
			return visitTableOperator((TableOperator) absOp);
		}
		
		return e;
	}
}
