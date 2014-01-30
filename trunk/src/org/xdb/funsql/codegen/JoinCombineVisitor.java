package org.xdb.funsql.codegen;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
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
 * This class merges several equi-join operators into one single SQL join operator,
 * to avoid unnecessary materialization of intermediary results
 * @author A.C.Mueller
 *
 */
public class JoinCombineVisitor extends AbstractTopDownTreeVisitor {
	
	private SQLJoin sqlJoin = null;
	private CompilePlan plan;
	
	public JoinCombineVisitor(AbstractCompileOperator root, CompilePlan plan) {
		super(root);
		this.plan = plan;
	}
	
	public JoinCombineVisitor(CompilePlan plan) {
		super();
		this.plan = plan;
	}

	@Override
	public void reset(AbstractCompileOperator root){
		super.reset(root);
		this.sqlJoin=null;
	}
	
	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		Error err = new Error();
		if(this.sqlJoin!=null){
			//merge new equi join into existing sql join
			this.sqlJoin.mergeChildJoinOp(ej);
			this.plan.removeOperator(ej.getOperatorId());
		}else {
			//create new sql join
			SQLJoin sqljoin =  new SQLJoin((EquiJoin)ej);
			this.plan.replaceOperator(ej.getOperatorId(), sqljoin);
			this.sqlJoin = sqljoin;
		}
		return err;
	}
	

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		this.sqlJoin = null;
		return new Error();
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		this.sqlJoin = null;
		return new Error();
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		this.sqlJoin = null;
		return new Error();
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		this.sqlJoin = null;
		return new Error();
	}

	@Override
	public Error visitRename(Rename ro) {
		this.sqlJoin = null;
		return new Error();
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		this.sqlJoin = null;
		return new Error();
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		this.sqlJoin = null;
		return new Error();
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		this.sqlJoin = null;
		return new Error();
	}
}
