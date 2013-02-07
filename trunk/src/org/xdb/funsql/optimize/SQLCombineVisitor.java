package org.xdb.funsql.optimize;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;

/**
 * This class visits the current plan and combines sqljoin and sqlunary operators to a s
 * sqlcombined operator. This done for the purpose to avoid materialization due to operator
 * calling.
 * 
 * @author A.C.Mueller
 *
 */
public class SQLCombineVisitor extends AbstractBottomUpTreeVisitor{

	private AbstractCompileOperator lastop = null;
	private Error err = new Error(); 
	private CompilePlan compileplan;
	public SQLCombineVisitor(AbstractCompileOperator root, CompilePlan compilePlan) {
		super(root);
		this.compileplan = compilePlan;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		String[] args = { "EquiJoin operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		this.lastop = ej;
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		this.lastop = gs;
		return err;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		this.lastop = sa;
		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		this.lastop =gp;
		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		this.lastop =to;
		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		this.lastop  = ro;
		return err;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		
		if(this.lastop.getType().equals(EnumOperator.SQL_JOIN)){
			SQLCombined sqlc = new SQLCombined((SQLJoin)this.lastop);
			this.compileplan.replaceOperator(absOp.getOperatorId(), sqlc,true);

			sqlc.mergeSQLUnaryParent(absOp);
		
			this.compileplan.removeOperator(this.lastop.getOperatorId());
		} 
		return err;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		String[] args = { "SQLCombined operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

}