package org.xdb.funsql.optimize;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
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
 * This class merges several equijoin operators into one single sql join operator,
 * to avoid uncessary materialization of intermediary results
 * @author A.C.Mueller
 *
 */
public class JoinCombineVisitor extends AbstractTopDownTreeVisitor {
	
	private AbstractCompileOperator lastOp = null;
	private Error err = new Error();
	private CompilePlan plan;
	
	public JoinCombineVisitor(AbstractCompileOperator root, CompilePlan plan) {
		super(root);
		this.plan = plan;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		//check if it's null then do nothing just change the last op
		if(this.lastOp != null){
			 if(this.lastOp.getType().equals(EnumOperator.SQL_JOIN)){
				//merge new equi join into existing sql join
				((SQLJoin)this.lastOp).mergeChildJoinOp(ej);
			}else {
				//create new sql join
				SQLJoin sqljoin =  new SQLJoin((EquiJoin)ej);
				this.plan.replaceOperator(ej.getOperatorId(), sqljoin, false);
				this.lastOp = sqljoin;
			}
		}else{
			this.lastOp = ej;
		}
		
		return this.err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		this.lastOp = gs;
		return new Error();
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		this.lastOp = sa;
		return new Error();
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		this.lastOp = gp;
		return new Error();
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		this.lastOp = to;
		return new Error();
	}

	@Override
	public Error visitRename(Rename ro) {
		this.lastOp = ro;
		return new Error();
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		this.lastOp = absOp;
		return new Error();
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		this.lastOp = ej;
		return new Error();
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		/*String[] args = { "SQLUnary operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);*/
		this.lastOp = absOp;
		return new Error();
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		this.lastOp = deOp;
		return err;
	}
}
