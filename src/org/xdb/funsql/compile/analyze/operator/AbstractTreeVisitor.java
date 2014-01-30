package org.xdb.funsql.compile.analyze.operator;


import java.util.HashSet;
import java.util.Set;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.utils.Identifier;

public abstract class AbstractTreeVisitor implements ITreeVisitor {
	
	protected AbstractCompileOperator treeRoot = null;
	private boolean stop = false;
	private Set<Identifier> visitedOps = new HashSet<Identifier>();
	
	public AbstractTreeVisitor(){
		this.stop = false;
	}
	
	public AbstractTreeVisitor(AbstractCompileOperator root) {
		this.treeRoot = root;
	}
	
	public void reset(AbstractCompileOperator root){
		this.stop = false;
		this.treeRoot = root;
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
	public Error visit(AbstractCompileOperator absOp) {
		Error e = new Error();
		
		if(this.stop)
			return e;
		
		if(this.visitedOps.contains(absOp.getOperatorId()))
			return e;
		
		this.visitedOps.add(absOp.getOperatorId());
		
		switch(absOp.getType()){
		case EQUI_JOIN:
			return visitEquiJoin((EquiJoin) absOp);
		case SQL_JOIN:
			return visitSQLJoin((SQLJoin) absOp);
		case GENERIC_SELECTION:
			return visitGenericSelection((GenericSelection) absOp);
		case GENERIC_AGGREGATION:
			return visitGenericAggregation((GenericAggregation) absOp);
		case GENERIC_PROJECTION:
			return visitGenericProjection((GenericProjection) absOp);
		case RENAME:
			return visitRename((Rename) absOp);
		case SQL_UNARY:
			return visitSQLUnary((SQLUnary)absOp);
		case SQL_COMBINED:
			return visitSQLCombined((SQLCombined)absOp);
		case TABLE:
			return visitTableOperator((TableOperator) absOp);
		case FUNCTION_CALL:
			return visitFunctionCall((FunctionCall) absOp);
		default:
			String[] args = {"AbstractTreeVisitor: Operator of type "+absOp.getType()+" not supported"};
			e = new Error(EnumError.COMPILER_GENERIC, args);
		}
		
		return e;
	}
	
	@Override
	public Error visitFunctionCall(FunctionCall fc) {
		String[] args = { "Function calls are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}
}
