package org.xdb.funsql.parallelize;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
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
 * This class goes through the plan and removes unnecessary Dataexchange operators
 * @author A.C.Mueller
 *
 */
public class RemoveDataExchangeOpVisitor extends AbstractBottomUpTreeVisitor {

	private Error err = new Error();
	private CompilePlan compilePlan;
	
	private double efficiencyHeuristic;
	
	public RemoveDataExchangeOpVisitor(AbstractCompileOperator root, CompilePlan compilePlan) {
		super(root);
		this.compilePlan = compilePlan;
	}

	public void reset(AbstractCompileOperator root, CompilePlan compilePlan){
		
		this.treeRoot = root;
		this.efficiencyHeuristic = 0.0;
		this.compilePlan = compilePlan;
	}
	
	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		handleRoot(ej);
		return err;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		handleRoot(ej);
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		handleRoot(gs);
		return err;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		handleRoot(sa);
		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		handleRoot(gp);
		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		handleRoot(to);
		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		handleRoot(ro);
		return err;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		handleRoot(absOp);
		return err;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		handleRoot(absOp);
		return err;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		if(isRemoveable(deOp, deOp.getParents().get(0).getOutputPartitionInfo())){
			//remove
			deOp.getChild().setParent(deOp, deOp.getParents().get(0));
			deOp.getParents().get(0).setChild(deOp, deOp.getChild());
			this.compilePlan.removeOperator(deOp.getOperatorId());
		}else {
			// So the repartitioning operator must stay. Then we need to update the cost of the plan
			
			int operatorsBeforeCnt = getNormalOperatorsCnt();
			PlanCost planCost = this.compilePlan.getPlanCost();
			planCost.setRepartitionOperatorCnt(planCost.getRepartitionOperatorCnt() + 1);
			planCost.setAccumulativeDistanceToRoots(planCost.getAccumulativeDistanceToRoots() + operatorsBeforeCnt);
			/* Alex's code:
			double quota = 1/ ((double) 10*operatorsbefore);
			this.efficiencyHeuristic = this.efficiencyHeuristic + quota;
			*/
		}
		
		return err;
	}
	

	/**
	 * 
	 * @param deOp
	 * @param removeOpInfo
	 * @return
	 */
	private boolean isRemoveable(DataExchangeOperator deOp, PartitionInfo removeOpInfo){	
		// TODO: Understand why we do the first comparison
		boolean returnvalue = deOp.getInputPartitioning().equals(removeOpInfo) && deOp.getInputPartitioning().equals(deOp.getOutputPartitionInfo());
		
		// Now we should check if the OutputPartitionInfo of the RepartitionOperator is a subset of its InputPartitionInfo.
		// Because if so, this means that the RepartitionOperator is not needed anymore.
		//boolean rv2 = deOp.getInputPartitioning().equals(deOp.getOutputPartitionInfo());
		boolean rv2 = deOp.getInputPartitioning().contains(deOp.getOutputPartitionInfo());
		
		return returnvalue||rv2;
	}

	private void handleRoot(AbstractCompileOperator absOp){
		//if root
		if(this.compilePlan.getRootIds().contains(absOp.getOperatorId())){
			if(absOp.getOutputPartitionInfo() != null && absOp.getOutputPartitionInfo().getParts() >1){
				//if the last op still is partioned add a super dataexchange Operator
				this.compilePlan.removeRootId(absOp.getOperatorId());
				DataExchangeOperator de = new DataExchangeOperator(absOp,absOp.getResult());
				de.setInputPartitioning(absOp.getOutputPartitionInfo());
				de.setOutputPartitionInfo(new PartitionInfo(EnumPartitionType.NO_PARTITION));
				this.compilePlan.addOperator(de, true);
		
			}
		}
	}
	
	public double getEfficiencyHeuristic() {
		return efficiencyHeuristic;
	}

}