package org.xdb.funsql.optimize;

import java.util.HashSet;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.AbstractUnaryOperator;
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
import org.xdb.utils.Identifier;

/**
 * Visitor combined several unary operators in a row into one combined operator
 * in the plan. Goal: Reduce the execution costs!
 * 
 * @author cbinnig
 * 
 */
public class SQLUnaryCombineVisitor extends AbstractBottomUpTreeVisitor {
		
	private SQLUnary sqlUnaryOp = null;
	private int parentIdx = 0;
	private boolean isLastOp = false;
	private CompilePlan plan;
	private HashSet<Identifier> combinedOps = new HashSet<Identifier>();

	private Error err = new Error();

	public SQLUnaryCombineVisitor(AbstractCompileOperator root, CompilePlan plan) {
		super(root);

		this.plan = plan;
	}

	private void init(AbstractUnaryOperator op) {
		if(this.sqlUnaryOp!=null){
			this.plan.removeOperator(this.sqlUnaryOp.getOperatorId());
		}
		
		this.sqlUnaryOp = new SQLUnary(op.getChild());
		this.plan.addOperator(this.sqlUnaryOp, false);
		this.sqlUnaryOp.cut();
		this.parentIdx = op.getChild().findParent(op);
		this.isLastOp = false;
		this.combinedOps.clear();
	}

	private void checkLastOp(AbstractCompileOperator op) {
		this.combinedOps.add(op.getOperatorId());
		
		if (op.getParents().size() > 1) {
			this.isLastOp = true;
		}
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		if(this.sqlUnaryOp != null){
			this.plan.removeOperator(this.sqlUnaryOp.getOperatorId());
			this.sqlUnaryOp = null;
		}
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if (this.sqlUnaryOp == null || this.isLastOp)
			this.init(gs);

		// add operator to combined operator
		if (!this.sqlUnaryOp.addSelection(gs)) {
			this.isLastOp = true;
			return err;
		}

		// check if operator is last operator
		this.checkLastOp(gs);

		return err;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		if (this.sqlUnaryOp == null || this.isLastOp)
			this.init(sa);

		// add operator to combined operator
		if (!this.sqlUnaryOp.addAggregation(sa)) {
			this.isLastOp = true;
			return err;
		}

		// check if operator is last operator
		this.checkLastOp(sa);

		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		if (this.sqlUnaryOp == null || this.isLastOp)
			this.init(gp);

		// add operator to combined operator
		if (!this.sqlUnaryOp.addProjection(gp)) {
			this.isLastOp = true;
			return err;
		}

		// projection is always last operator, thus paste new operator into plan
		this.isLastOp = true;
		if (this.sqlUnaryOp.countOperators() > 1) {
			this.paste(gp);
		}

		return err;
	}

	
	private void paste(GenericProjection gp){
		// add result description
		
	
		this.sqlUnaryOp.addResult(gp.getResult());
		
		// replace projection by combined operator
		plan.replaceOperator(gp.getOperatorId(), this.sqlUnaryOp, true);
		
		// paste to parents
		for (AbstractCompileOperator parent : gp.getParents()) {
			int childIdx = parent.findChild(gp);
			parent.setChild(childIdx, this.sqlUnaryOp);
		}
		this.sqlUnaryOp.addParents(gp.getParents());
	
		// remove link to old parent 
		this.sqlUnaryOp.getChild().setParent(parentIdx, this.sqlUnaryOp);
		
		//remove other operators form plan
		for(Identifier opId: this.combinedOps){
			this.plan.removeOperator(opId);
		}
		

		// reset SQLUnary operator

		this.sqlUnaryOp = null;
	}
	
	@Override
	public Error visitTableOperator(TableOperator to) {
		return err;
	}

	@Override
	public Error visitRename(Rename renameOp) {
		if (this.sqlUnaryOp == null || this.isLastOp)
			this.init(renameOp);

		// add operator to combined operator
		if (!this.sqlUnaryOp.addRename(renameOp)) {
			this.isLastOp = true;
			return err;
		}

		// check if operator is last operator
		this.checkLastOp(renameOp);

		return err;
	}

	@Override
	public Error visitSQLUnary(SQLUnary sqlOp) {

		Error e = new Error();
		return e;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		if(this.sqlUnaryOp != null){
			this.plan.removeOperator(this.sqlUnaryOp.getOperatorId());
			this.sqlUnaryOp = null;
		}
		return err;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		Error e = new Error();
		return e;

	}
	
	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		return err;
	}
}
