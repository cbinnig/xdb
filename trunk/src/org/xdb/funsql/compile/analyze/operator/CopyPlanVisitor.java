package org.xdb.funsql.compile.analyze.operator;

import java.util.Map;
import java.util.Stack;

import org.xdb.error.Error;
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

public class CopyPlanVisitor extends AbstractTopDownTreeVisitor {
	private Error error = new Error();
	
	private Map<AbstractCompileOperator,AbstractCompileOperator> oldtoNewOp;
	private Stack<AbstractCompileOperator> operatorStack;

	public CopyPlanVisitor(AbstractCompileOperator root, Map<AbstractCompileOperator,AbstractCompileOperator>  oldtoNewOp) {
		super(root);
		this.oldtoNewOp = oldtoNewOp;
		operatorStack = new Stack<AbstractCompileOperator>();
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		if (!this.checkAddToStack(ej))
			return error;
		EquiJoin newEJ = new EquiJoin(ej);
		oldtoNewOp.put(ej, newEJ);
		operatorStack.push(newEJ);
		return error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		if (!this.checkAddToStack(ej))
			return error;
		SQLJoin newEJ = new SQLJoin(ej);
		oldtoNewOp.put(ej,newEJ);
		operatorStack.push(newEJ);
		return error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if (!this.checkAddToStack(gs))
			return error;
		GenericSelection newGS = new GenericSelection(gs);
		oldtoNewOp.put(gs, newGS);
		operatorStack.push(newGS);
		
		
		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		if (!this.checkAddToStack(sa))
			return error;
		GenericAggregation newGA = new GenericAggregation(sa);
		
		oldtoNewOp.put(sa, newGA);
		operatorStack.push(newGA);
		return error;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		if (!this.checkAddToStack(gp))
			return error;
		GenericProjection newGP = new GenericProjection(gp);
		oldtoNewOp.put(gp, newGP);
		operatorStack.push(newGP);
		return error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		if (!this.checkAddToStack(to))
			return error;

		TableOperator newTo = new TableOperator(to);
		oldtoNewOp.put(to, newTo);
		operatorStack.push(newTo);
		return error;
	}

	@Override
	public Error visitRename(Rename ro) {
		if (!this.checkAddToStack(ro))
			return error;
		Rename newRo= new Rename(ro);
		oldtoNewOp.put(ro,newRo );
		operatorStack.push(newRo);
		return error;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		if (!this.checkAddToStack(absOp))
			return error;
		SQLUnary newSQLUnary = new SQLUnary(absOp);
		oldtoNewOp.put(absOp, newSQLUnary);
		operatorStack.push(newSQLUnary);
		return error;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		if (!this.checkAddToStack(absOp))
			return error;
		SQLCombined newSQLCombine = new SQLCombined(absOp);
		oldtoNewOp.put(absOp, newSQLCombine);
		operatorStack.push(newSQLCombine);
		return error;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
	
		DataExchangeOperator newDEOp = new DataExchangeOperator(deOp);
		oldtoNewOp.put(deOp, newDEOp);
		operatorStack.push(newDEOp);
		
		return error;
	}

	public Error getError() {
		return error;
	}

	public Stack<AbstractCompileOperator> getOperatorStack() {
		return operatorStack;
	}
	
	public boolean checkAddToStack(AbstractCompileOperator absOp){
		if(oldtoNewOp.containsKey(absOp)){
			operatorStack.push(oldtoNewOp.get(absOp));
			
			return false;
		}
		return true;
	}
	

}