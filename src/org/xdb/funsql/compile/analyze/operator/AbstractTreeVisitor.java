package org.xdb.funsql.compile.analyze.operator;


import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.TableOperator;

public abstract class AbstractTreeVisitor implements ITreeVisitor {
	
	protected AbstractOperator treeRoot = null;
	protected boolean stop = false;
	
	public AbstractTreeVisitor(AbstractOperator root) {
		treeRoot = root;
	}

	public Error visit(){
		this.stop = false;
		return this.visit(treeRoot);
	}
	
	public void stop(){
		this.stop = true;
	}
	
	public boolean stopped(){
		return this.stop;
	}
	
	@Override
	public Error visit(AbstractOperator absOp) {
		Error e = new Error();
		
		if(e.isError() || this.stop)
			return e;
		
		switch(absOp.getType()){
		case EQUI_JOIN:
			return visitEquiJoin((EquiJoin) absOp);
		case GENERIC_SELECTION:
			return visitGenericSelection((GenericSelection) absOp);
		case GENERIC_AGGREGATION:
			return visitGenericAggregation((GenericAggregation) absOp);
		case GENERIC_PROJECTION:
			return visitGenericProjection((GenericProjection) absOp);
		case RENAME:
			return visitRename((Rename) absOp);
		case TABLE:
			return visitTableOperator((TableOperator) absOp);
		default:
			String[] args = {"AbstractTreeVisitor: Operator of type "+absOp.getType()+" not supported"};
			e = new Error(EnumError.COMPILER_GENERIC, args);
		}
		
		return e;
	}
}
