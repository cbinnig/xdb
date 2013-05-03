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
 * @author mueller
 *
 */
public class RemoveDataExchangeOpVisitor extends AbstractBottomUpTreeVisitor {

	private Error err = new Error();
	private int deOps;
	private CompilePlan compilePlan;
	public RemoveDataExchangeOpVisitor(AbstractCompileOperator root, CompilePlan compilePlan) {
		super(root);
		deOps = 0;
		this.compilePlan = compilePlan;
	}

	public void reset(AbstractCompileOperator root, CompilePlan compilePlan){
		
		this.treeRoot = root;
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
		deOps++;
		if(isRemoveable(deOp.getPartitionOutputInfo(), deOp.getChild().getPartitionOutputInfo())){
			//remove
			deOps--;
			deOp.getChild().setParent(deOp, deOp.getParents().get(0));
			deOp.getParents().get(0).setChild(deOp, deOp.getChild());
			this.compilePlan.removeOperator(deOp.getOperatorId());
		}
		
		return err;
	}
	
	private boolean isRemoveable(PartitionInfo inputOpInfo, PartitionInfo removeOpInfo){	
		
		return (inputOpInfo != null && inputOpInfo.equals(removeOpInfo));
	
	}

	public int getDeOps() {
		return deOps;
	}
	
	private void handleRoot(AbstractCompileOperator absOp){
		//if root
		if(this.compilePlan.getRootIds().contains(absOp.getOperatorId())){
			if(absOp.getPartitionOutputInfo() != null && absOp.getPartitionOutputInfo().getParts() >1){
				//if the last op still is partioned add a super dataexchange Operator
				this.compilePlan.removeRootId(absOp.getOperatorId());
				DataExchangeOperator de = new DataExchangeOperator(absOp,absOp.getResult());
				de.setInputPartitioning(absOp.getPartitionOutputInfo());
				de.setPartitionOutputInfo(new PartitionInfo(EnumPartitionType.NO_PARTITION));
				this.compilePlan.addOperator(de, true);
		
			}
		}
	}

}