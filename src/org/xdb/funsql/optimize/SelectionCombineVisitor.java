package org.xdb.funsql.optimize;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.EnumPredicateType;
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class SelectionCombineVisitor extends AbstractTopDownTreeVisitor {
	private AbstractCompileOperator lastOp = null;
	private Error err = new Error();
	
	public SelectionCombineVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		this.lastOp = ej;
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		//combine two subsequent selections
		if(this.lastOp.getType() == EnumOperator.GENERIC_SELECTION){
			//cut last selection
			GenericSelection lastSel = (GenericSelection)this.lastOp;
			lastSel.cut();
			
			//create new conjunctive predicate
			AbstractPredicate selPred = gs.getPredicate();
			AbstractPredicate lastSelPred = lastSel.getPredicate();
			TokenAttribute.renameTable(lastSelPred.getAttributes(), gs.getChild().getOperatorId().toString());
			ComplexPredicate newSelPred = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
			newSelPred.setPredicate1(selPred);
			newSelPred.addAnd();
			newSelPred.addPredicate2(lastSelPred);
			
			//set predicate as new predicate
			gs.setPredicate(newSelPred);
		}
		this.lastOp = gs;
		return err;
	}

	@Override
	public Error visitFunctionCall(FunctionCall fc) {
		String[] args = { "Optimizer: Selection combination for function call not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		this.lastOp = sa;
		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		this.lastOp = gp;
		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		this.lastOp = to;
		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		this.lastOp = ro;
		return err;
	}

}
