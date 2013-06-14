package org.xdb.funsql.compile.analyze.operator;


import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
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

public abstract class AbstractTreeVisitor implements ITreeVisitor {
	
	protected AbstractCompileOperator treeRoot = null;
	protected boolean stop = false;
	protected int normalOperatorsCnt;	// all operators except DE operators
	
	public AbstractTreeVisitor(AbstractCompileOperator root) {
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
	public Error visit(AbstractCompileOperator absOp) {
		Error e = new Error();
		
		if(e.isError() || this.stop)
			return e;
		
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
		case DATA_EXCHANGE:
			return visitDataExchange((DataExchangeOperator) absOp);
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

	
	public int getNormalOperatorsCnt() {
		return normalOperatorsCnt;
	}

	
}
