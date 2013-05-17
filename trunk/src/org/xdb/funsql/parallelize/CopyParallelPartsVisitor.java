package org.xdb.funsql.parallelize;

import java.util.Stack;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
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
 * This visitor goes through the operator until it founds a dataexchange
 * operator and then multiplies the parts below this operator in order to
 * generate a parallel compilePlan
 * 
 * @author A.C.Mueller
 * 
 */
public class CopyParallelPartsVisitor extends AbstractBottomUpTreeVisitor {

	private Error error;

	private CompilePlan plan;

	public CopyParallelPartsVisitor(AbstractCompileOperator root,
			CompilePlan plan) {
		super(root);

		error = new Error();
		this.plan = plan;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		return error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		return error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		return error;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		return error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		return error;
	}

	@Override
	public Error visitRename(Rename ro) {
		return error;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		return error;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		return error;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		// copy all elements under the de n times until the next de is reached
		// and annotated the TableOperators with the part
		for (int i = 0; i < deOp.getInputPartitioning().getParts() - 1; i++) {
			GetOperatorStackVisitor cp = new GetOperatorStackVisitor(deOp, i);
			cp.visit();
			copyStackElements(cp.getOperatorStack(), deOp, i + 1);
		}
		return error;
	}

	/**
	 * Copies all Elements from the Stack to the plan in the correct order and
	 * with correct parent and child relationship
	 * 
	 * @param opStack
	 * @param root
	 * @return
	 */
	public Error copyStackElements(Stack<AbstractCompileOperator> opStack,
			AbstractCompileOperator root, int part) {

		// build stacks for children
		Stack<AbstractCompileOperator> childOpStack = new Stack<AbstractCompileOperator>();

		AbstractCompileOperator currentOperator = null;
		AbstractCompileOperator currentChildOperator = null;

		while (opStack.size() > 0) {
			// get Operator from Stack
			currentOperator = opStack.pop();

			// add it to Plan

			if (currentOperator.getType().equals(EnumOperator.TABLE)) {
				if (((TableOperator) currentOperator).getTable().isPartioned()) {
					// if part already set don't change it, so lookup if changed
					if (((TableOperator) currentOperator).getPart() == -1) {
						((TableOperator) currentOperator).setPart(part);
					}

				}
			}

			if (this.plan.isInPlan(currentOperator.getOperatorId())) {
				childOpStack.push(currentOperator);
			} else {
				this.plan.addOperator(currentOperator, false);
				int childsize = currentOperator.getChildren().size();
		
				currentOperator.resetChildren();
				for (int i = 0; i < childsize; i++) {
					currentChildOperator = childOpStack.pop();
					currentOperator.addChild(currentChildOperator);
					//here are potentially more parents added than really needed
					currentChildOperator.addParent(currentOperator);
				}
				//eliminate unnecessary parent Operators from the child Op
				if (currentOperator.getParents().size() >0) {
					//loop over parents of child op
					Vector<AbstractCompileOperator> toremove = new Vector<AbstractCompileOperator>();
					for (AbstractCompileOperator tmpParent : currentOperator
							.getParents()) {
						//and eliminate every Parent Operator (tmpParent) from the child Operator where the child Operator is not in the child list of the tmpParent
						if(!tmpParent.getChildren().contains(currentOperator)){
							if(!root.equals(tmpParent))toremove.add(tmpParent);
						}
		
					}
					currentOperator.getParents().removeAll(toremove);
				}
				childOpStack.push(currentOperator);
			}
		}

		root.addChild(currentOperator);
		currentOperator.setParent(0, root);

		return error;
	}

}
