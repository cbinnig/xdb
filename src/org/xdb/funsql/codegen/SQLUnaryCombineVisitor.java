package org.xdb.funsql.codegen;

import java.util.HashSet;
import java.util.Set;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.AbstractUnaryOperator;
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
	private AbstractUnaryOperator lastUnaryOp = null;
	
	private int parentIdx = 0;
	private CompilePlan plan;
	private Set<Identifier> toRemoveOps = new HashSet<Identifier>();

	private Error err = new Error();

	public SQLUnaryCombineVisitor(AbstractCompileOperator root, CompilePlan plan) {
		super(root);
		this.plan = plan;
	}
	
	public SQLUnaryCombineVisitor(CompilePlan plan) {
		super();
		this.plan = plan;
	}

	@Override
	public void reset(AbstractCompileOperator root){
		super.reset(root);
		this.lastUnaryOp=null;
		this.sqlUnaryOp = null;
		this.parentIdx=0;
		this.toRemoveOps.clear();
	}
	
	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if (this.sqlUnaryOp == null)
			this.initVisitor(gs);

		// add operator to combined operator
		if (!this.sqlUnaryOp.addSelection(gs)) {
			this.stop();
			return err;
		}

		// check if operator is last operator
		this.checkLastOp(gs);

		return err;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		if (this.sqlUnaryOp == null )
			this.initVisitor(sa);

		// add operator to combined operator
		if (!this.sqlUnaryOp.addAggregation(sa)) {
			this.stop();
			return err;
		}

		// check if operator is last operator
		this.checkLastOp(sa);

		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		if (this.sqlUnaryOp == null )
			this.initVisitor(gp);

		// add operator to combined operator
		this.sqlUnaryOp.addProjection(gp);

		// projection is always last operator
		this.lastUnaryOp = gp;
		this.stop();
		
		return err;
	}

	@Override
	public Error visitRename(Rename renameOp) {
		if (this.sqlUnaryOp == null )
			this.initVisitor(renameOp);

		// add operator to combined operator
		if (!this.sqlUnaryOp.addRename(renameOp)) {
			this.stop();
			return err;
		}

		// check if operator is last operator
		this.checkLastOp(renameOp);

		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		return err;
	}


	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		this.resetVisitor();
		return err;
	}
	
	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		this.resetVisitor();
		return err;
	}
	
	@Override
	public Error visitSQLUnary(SQLUnary sqlOp) {
		this.resetVisitor();
		return err;
	}


	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		this.resetVisitor();
		return err;
	}

	/**
	 * Make sure that we only combine operator above joins up to the next
	 * projection or aggregation
	 */
	private void resetVisitor(){
		if(this.sqlUnaryOp != null){
			this.plan.removeOperator(this.sqlUnaryOp.getOperatorId());
			this.sqlUnaryOp = null;
		}
	}

	/**
	 * Initializes combined operator
	 * @param op
	 */
	private void initVisitor(AbstractUnaryOperator op) {
		if(this.sqlUnaryOp!=null){
			this.plan.removeOperator(this.sqlUnaryOp.getOperatorId());
		}
		
		this.sqlUnaryOp = new SQLUnary(op.getChild());
		this.plan.addOperator(this.sqlUnaryOp, false);
		this.sqlUnaryOp.cut();
		this.parentIdx = op.getChild().findParent(op);
		this.toRemoveOps.clear();
	}

	@Override
	public void stop(){
		if (this.sqlUnaryOp.countOperators() > 1) {
			this.paste(this.lastUnaryOp);
		}
		
		super.stop();
	}
	
	/**
	 * Checks if we need to stop at the current operator
	 * @param op
	 */
	private void checkLastOp(AbstractUnaryOperator op) {
		this.lastUnaryOp = op;
		
		if (op.getResult().materialize()) {
			this.stop();
		}
		else{
			this.toRemoveOps.add(op.getOperatorId());
		}
	}
	
	/**
	 * Paste new SQLUnary operator to position of given unary operator
	 * and use result of this operator
	 * @param unaryOp
	 */
	private void paste(AbstractUnaryOperator unaryOp){
		// add result description
		this.sqlUnaryOp.setResult(unaryOp.getResult());
		
		// replace unaryOp by combined operator
		plan.replaceOperator(unaryOp.getOperatorId(), this.sqlUnaryOp);
		
		// paste to parents
		for (AbstractCompileOperator parent : unaryOp.getParents()) {
			int childIdx = parent.findChild(unaryOp);
			parent.replaceChild(childIdx, this.sqlUnaryOp);
		}
		this.sqlUnaryOp.addParents(unaryOp.getParents());
	
		// remove link to old parent 
		this.sqlUnaryOp.getChild().replaceParent(parentIdx, this.sqlUnaryOp);
		
		//remove other operators form plan
		for(Identifier opId: this.toRemoveOps){
			this.plan.removeOperator(opId);
		}
		
		// reset SQLUnary operator
		this.sqlUnaryOp = null;
	}
}
