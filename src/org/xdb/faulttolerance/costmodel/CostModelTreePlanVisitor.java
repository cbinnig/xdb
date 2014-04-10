package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
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
import org.xdb.utils.Identifier;

public class CostModelTreePlanVisitor extends AbstractTopDownTreeVisitor { 
	
	private List<AbstractCompileOperator> ops = new ArrayList<AbstractCompileOperator>(); 
	
	private List<Identifier> nonMatsOps = new ArrayList<Identifier>();

	public CostModelTreePlanVisitor() { 
		super();
	}  
	
	public CostModelTreePlanVisitor(AbstractCompileOperator root) { 
		super(root);
	} 
	
	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		if(isOpMaterializable(ej))
		   this.setOps(ej);
		return this.visit(ej.getLeftChild());
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		if(isOpMaterializable(ej))
		   this.setOps(ej);
		return getNonTableChildForAnOp(ej);
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if(isOpMaterializable(gs))
		   this.setOps(gs);
		return this.visit(gs.getChild());
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		if(isOpMaterializable(sa))
		   this.setOps(sa);
		return this.visit(sa.getChild());
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) { 
		if(isOpMaterializable(gp))
		   this.setOps(gp);
		return this.visit(gp.getChild());
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		//do nothing if it is table operator 
		return new Error();
	}

	@Override
	public Error visitRename(Rename ro) {
		if(isOpMaterializable(ro))
		    this.setOps(ro); 
		return getNonTableChildForAnOp(ro);  
	
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) { 
		if(isOpMaterializable(absOp))
		   this.setOps(absOp);
		return this.visit(absOp.getChild());
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		if(isOpMaterializable(absOp))
		   this.setOps(absOp);
		return getNonTableChildForAnOp(absOp);
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
	public void setOps(AbstractCompileOperator op) {
		this.ops.add(op);
	} 
	
	/**
	 * 
	 * Check the children of a certain operator to find 
	 * the left child to vist.  
	 * 
	 * @param op
	 * @return
	 */
	private Error getNonTableChildForAnOp(AbstractCompileOperator op){
		Vector<AbstractCompileOperator> childOps = op.getChildren();  
		AbstractCompileOperator selectedVisitedOp = null;
		for (AbstractCompileOperator abstractCompileOperator : childOps) {
			if(abstractCompileOperator.getType().equals(EnumOperator.TABLE)){ 
				selectedVisitedOp = abstractCompileOperator;
				continue; 
			}
			else {
				selectedVisitedOp = abstractCompileOperator; 
				break;
			}
		} 
		return this.visit(selectedVisitedOp);  
	} 
	
	private boolean isOpMaterializable(AbstractCompileOperator op){

		if(nonMatsOps.contains(op.getOperatorId().getChildId())) 
		    return false; 
		else 
			return true;
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

}
