package org.xdb.funsql.parallelize;

import java.util.Stack;

import org.xdb.error.Error;
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
 * This is a visitor which is used to get a stack of the copied current operator
 * tree elements in postorder, to make it ready for to rebuild in the same order
 * 
 * @author A.C.Mueller
 * 
 */
public class GetOperatorStackVisitor extends AbstractTopDownTreeVisitor {

	private Error error = new Error();

	private int partititionPartIndex;
	
	private Stack<AbstractCompileOperator> operatorStack;

	
	private AbstractCompileOperator lastOp;

	
	
	
	public GetOperatorStackVisitor(AbstractCompileOperator root,int partititionPartIndex) {
		super(root);
		this.partititionPartIndex = partititionPartIndex;
		operatorStack = new Stack<AbstractCompileOperator>();
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		if (!this.checkAddToStack(ej))
			return error;
		operatorStack.push(new EquiJoin(ej));
		return error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		if (!this.checkAddToStack(ej))
			return error;
		operatorStack.push(new SQLJoin(ej));
		return error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if (!this.checkAddToStack(gs))
			return error;
		operatorStack.push(new GenericSelection(gs));
		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		if (!this.checkAddToStack(sa))
			return error;
		operatorStack.push(new GenericAggregation(sa));
		return error;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		if (!this.checkAddToStack(gp))
			return error;
		operatorStack.push(new GenericProjection(gp));
		return error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		if (!this.checkAddToStack(to))
			return error;

		TableOperator newTo = new TableOperator(to);
		operatorStack.push(newTo);
		
		if(to.getTable().isPartioned()){
			if(to.getPart() == -1){
				to.setPart(partititionPartIndex);
			}
		}

		return error;
	}

	@Override
	public Error visitRename(Rename ro) {
		if (!this.checkAddToStack(ro))
			return error;
		operatorStack.push(new Rename(ro));
		return error;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		if (!this.checkAddToStack(absOp))
			return error;
		operatorStack.push(new SQLUnary(absOp));
		return error;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		if (!this.checkAddToStack(absOp))
			return error;
		operatorStack.push(new SQLCombined(absOp));
		return error;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		if (!this.checkAddToStack(deOp))
			return error;
	
		operatorStack.push(deOp);
		
		
		return error;
	}

	public Error getError() {
		return error;
	}

	public Stack<AbstractCompileOperator> getOperatorStack() {
		return operatorStack;
	}
	
	public boolean checkAddToStack(AbstractCompileOperator absOp){
		if (absOp.equals(treeRoot))
			return false;
		
		if(lastOp != null){
			if(absOp.getParents().contains(lastOp)){
				return true;
			}else {
				return false;
			}	
		}else {
			if(this.childContainsDE(absOp)){
				this.lastOp = absOp;
			}
		}
		return true;
	}
	
	public boolean childContainsDE(AbstractCompileOperator absOp){
		for(AbstractCompileOperator child :absOp.getChildren()){
			if(child.getType().equals(EnumOperator.DATA_EXCHANGE)){
				return true;
			}
		}
		return false;
	}
}