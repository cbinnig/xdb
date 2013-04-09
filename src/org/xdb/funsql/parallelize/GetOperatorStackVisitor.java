package org.xdb.funsql.parallelize;

import java.util.Stack;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
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
 * This is a visitor which is used to get a stack of the copied current operator
 * tree elements in postorder, to make it ready for to rebuild in the same order
 * 
 * @author A.C.Mueller
 * 
 */
public class GetOperatorStackVisitor extends AbstractTopDownTreeVisitor {

	private Error error = new Error();

	private Stack<AbstractCompileOperator> operatorStack;

	public GetOperatorStackVisitor(AbstractCompileOperator root) {
		super(root);
		operatorStack = new Stack<AbstractCompileOperator>();
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		if (ej.equals(treeRoot))
			return error;
		operatorStack.push(new EquiJoin(ej));
		return error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		if (ej.equals(treeRoot))
			return error;
		operatorStack.push(new SQLJoin(ej));
		return error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if (gs.equals(treeRoot))
			return error;
		operatorStack.push(new GenericSelection(gs));
		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		if (sa.equals(treeRoot))
			return error;
		operatorStack.push(new GenericAggregation(sa));
		return error;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		if (gp.equals(treeRoot))
			return error;
		operatorStack.push(new GenericProjection(gp));
		return error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		if (to.equals(treeRoot))
			return error;
		TableOperator newTo = new TableOperator(to);
		operatorStack.push(newTo);
		return error;
	}

	@Override
	public Error visitRename(Rename ro) {
		if (ro.equals(treeRoot))
			return error;
		operatorStack.push(new Rename(ro));
		return error;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		if (absOp.equals(treeRoot))
			return error;
		operatorStack.push(new SQLUnary(absOp));
		return error;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		if (absOp.equals(treeRoot))
			return error;
		operatorStack.push(new SQLCombined(absOp));
		return error;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		if (deOp.equals(treeRoot))
			return error;
		operatorStack.push(new DataExchangeOperator(deOp));
		return error;
	}

	public Error getError() {
		return error;
	}

	public Stack<AbstractCompileOperator> getOperatorStack() {
		return operatorStack;
	}
}
