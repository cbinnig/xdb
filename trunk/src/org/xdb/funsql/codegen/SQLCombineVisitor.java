package org.xdb.funsql.codegen;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
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
 * This class visits the current plan and combines SQLJoin and SQLUnary operators to a 
 * SQLCombined operator. 
 * 
 * @author A.C.Mueller
 *
 */
public class SQLCombineVisitor extends AbstractBottomUpTreeVisitor{

	private AbstractCompileOperator lastop = null;
	private Error err = new Error(); 
	private CompilePlan plan;
	
	public SQLCombineVisitor(AbstractCompileOperator root, CompilePlan plan) {
		super(root);
		this.plan = plan;
	}
	
	public SQLCombineVisitor(CompilePlan plan) {
		super();
		this.plan = plan;
	}
	
	@Override
	public void reset(AbstractCompileOperator root){
		super.reset(root);
		this.lastop=null;
	}
	
	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		this.lastop = ej;
		return err;
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
		
		if(this.lastop!=null && this.lastop.getType().isSQLJoin()){
			SQLCombined sqlc = new SQLCombined(this.plan, (SQLJoin)this.lastop);
			sqlc.mergeSQLUnaryParent(absOp);
			
			this.plan.replaceOperator(absOp.getOperatorId(), sqlc);
			this.plan.removeOperator(this.lastop.getOperatorId());
			
			this.lastop = sqlc;
		} 
		return err;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		this.lastop = absOp;
		return err;
	}
}