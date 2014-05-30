package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xdb.error.Error;
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
import org.xdb.utils.Identifier;

public class BushyPlanBottomUpTreeVisitor extends AbstractBottomUpTreeVisitor { 
	
    private List<AbstractCompileOperator> ops = new ArrayList<AbstractCompileOperator>(); 
	
	private List<Identifier> nonMatsOps = new ArrayList<Identifier>(); 
	
	private CostModelQueryPlan costModelQueryPlan; 

	@Override
	public Error visitEquiJoin(EquiJoin ej) { 
		if(!nonMatsOps.contains(ej.getOperatorId().getChildId())) 
			ops.add(ej); 
		else if(!ej.isRoot()) { 
			mergeOperators(ej);
		}
		return new Error() ;
	}


	@Override
	public Error visitSQLJoin(SQLJoin ej) { 
		if(!nonMatsOps.contains(ej.getOperatorId().getChildId())) 
			ops.add(ej); 
		else if(!ej.isRoot()) { 
			mergeOperators(ej);
		}
		return new Error();
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if(!nonMatsOps.contains(gs.getOperatorId().getChildId())) 
			ops.add(gs);
		else if(!gs.isRoot()) { 
			mergeOperators(gs);
		}
		return new Error();
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) { 
		if(!nonMatsOps.contains(sa.getOperatorId().getChildId())) 
			ops.add(sa);
		else if(!sa.isRoot()) { 
			mergeOperators(sa);
		}
		return new Error();
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		if(!nonMatsOps.contains(gp.getOperatorId().getChildId())) 
			ops.add(gp);
		else if(!gp.isRoot()) { 
			mergeOperators(gp);
		}
		return new Error();
	}

	@Override
	public Error visitTableOperator(TableOperator to) { 
		if(!nonMatsOps.contains(to.getOperatorId().getChildId())) 
			ops.add(to);
		return new Error();
	}

	@Override
	public Error visitRename(Rename ro) {
		if(!nonMatsOps.contains(ro.getOperatorId().getChildId())) 
			ops.add(ro);
		else if(!ro.isRoot()) { 
			mergeOperators(ro);
		}
		return new Error();
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		if(!nonMatsOps.contains(absOp.getOperatorId().getChildId())) 
			ops.add(absOp);
		else if(!absOp.isRoot()) { 
			mergeOperators(absOp);
		}
		return new Error();
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		if(!nonMatsOps.contains(absOp.getOperatorId().getChildId())) 
			ops.add(absOp);
		else if(!absOp.isRoot()) { 
			mergeOperators(absOp);
		}
		return new Error();
	}

	/**
	 * @return the ops
	 */
	public List<AbstractCompileOperator> getOps() {
		return ops;
	}

	/**
	 * @param ops the ops to set
	 */
	public void setOps(List<AbstractCompileOperator> ops) {
		this.ops = ops;
	}

	/**
	 * @return the nonMatsOps
	 */
	public List<Identifier> getNonMatsOps() {
		return nonMatsOps;
	}

	/**
	 * @param nonMatsOps the nonMatsOps to set
	 */
	public void setNonMatsOps(List<Identifier> nonMatsOps) {
		this.nonMatsOps = nonMatsOps;
	}

	/**
	 * 
	 * @param op
	 */
	private void mergeOperators(AbstractCompileOperator op) {
		// if it non materializable add its children to 
		// its parent children. 
		CostModelOperator cModelOp = costModelQueryPlan.getOperator(op.getOperatorId().getChildId()); 
		Vector<CostModelOperator> parents = cModelOp.getParents(); 
		for (CostModelOperator parent : parents) {
			parent.addChildren(cModelOp.getChildren());
			parent.removeChild(cModelOp); 
			// add the parent to the child 
			for (CostModelOperator child : cModelOp.getChildren() ) 
				child.addParent(parent);
			costModelQueryPlan.removeOperator(op.getOperatorId().getChildId());
		}
		
		// remove children's old parents and add new parents to them 
		for (CostModelOperator child : cModelOp.getChildren() ) {
			child.removeParent(cModelOp);
		}
	}

	/**
	 * @return the costModelQueryPlan
	 */
	public CostModelQueryPlan getCostModelQueryPlan() {
		return costModelQueryPlan;
	}

	/**
	 * @param costModelQueryPlan the costModelQueryPlan to set
	 */
	public void setCostModelQueryPlan(CostModelQueryPlan costModelQueryPlan) {
		this.costModelQueryPlan = costModelQueryPlan;
	}

}
