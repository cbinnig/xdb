package org.xdb.funsql.optimize;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;

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
			if(this.lastOp.getType().equals(EnumOperator.EQUI_JOIN)){
			
				//create new sql join
				SQLJoin sqljoin =  new SQLJoin((EquiJoin)this.lastOp);
				
				//merge equi Join into sql jlin
				sqljoin.mergeChildJoinOp(ej);
				// remove operators
				this.plan.replaceOperator(this.lastOp.getOperatorId(), sqljoin, false);
				this.plan.removeOperator(ej.getOperatorId());
 
				this.lastOp = sqljoin;

				return this.err;
			} else if(this.lastOp.getType().equals(EnumOperator.SQL_JOIN)){
				//merge new equi join into existing sql join
				((SQLJoin)this.lastOp).mergeChildJoinOp(ej);
			}else {
				this.lastOp = ej;
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

}
