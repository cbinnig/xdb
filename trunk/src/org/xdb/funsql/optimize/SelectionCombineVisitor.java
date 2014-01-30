package org.xdb.funsql.optimize;

import org.xdb.error.EnumError;
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
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.EnumPredicateType;
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class SelectionCombineVisitor extends AbstractTopDownTreeVisitor {
	private AbstractCompileOperator lastOp = null;
	
	public SelectionCombineVisitor() {
		super();
	}
	
	public SelectionCombineVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		Error err = new Error();
		this.lastOp = ej;
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		Error err = new Error();
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
	public Error visitGenericAggregation(GenericAggregation sa) {
		Error err = new Error();
		this.lastOp = sa;
		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		Error err = new Error();
		this.lastOp = gp;
		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		Error err = new Error();
		this.lastOp = to;
		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		Error err = new Error();
		this.lastOp = ro;
		return err;
	}

	@Override
	public Error visitSQLUnary(SQLUnary sqlOp) {
		Error err = new Error();
		this.lastOp = sqlOp;
		return err;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		Error err = new Error();
		this.lastOp = ej;
		return err;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		String[] args = { "SQLCombined operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}
}
