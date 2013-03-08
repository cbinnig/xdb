package org.xdb.funsql.optimize;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractBinaryOperator;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.AbstractJoinOperator;
import org.xdb.funsql.compile.operator.AbstractUnaryOperator;
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
import org.xdb.utils.Identifier;

/**
 * Optimizer rule which pushes down selections in a CompilePlan
 * 
 * @author cbinnig
 * 
 */
public class SelectionPushDownVisitor extends AbstractTreeVisitor {
	private static final int CHILD_IDX = 0;
	private static final int LEFT_CHILD_IDX = 0;
	private static final int RIGHT_CHILD_IDX = 1;

	private HashSet<Identifier> finishedSelections = new HashSet<Identifier>();

	private Map<AbstractCompileOperator, Integer> pasteInfo = new HashMap<AbstractCompileOperator, Integer>();
	private Map<Identifier, Map<Integer, AbstractPredicate>> waitInfo = new HashMap<Identifier, Map<Integer, AbstractPredicate>>();
	private GenericSelection cutSelection = null;
	private AbstractCompileOperator lastOp = null;
	private boolean modifiedPlan = false;
	private int nextChildIdx = 0;
	private boolean doWaitNextVisit = true;
	private CompilePlan plan;

	private Error err = new Error();

	// constructors
	public SelectionPushDownVisitor(AbstractCompileOperator root, CompilePlan plan) {
		super(root);
		this.plan = plan;
	}

	// getter and setter
	/**
	 * Returns true if plan was modified by optimization rule
	 * 
	 * @return
	 */
	public boolean modifiedPlan() {
		return this.modifiedPlan;
	}

	// methods
	public void reset(AbstractCompileOperator root) {
		this.treeRoot = root;
		this.cutSelection = null;
		this.lastOp = null;
		this.modifiedPlan = false;
		this.nextChildIdx = 0;
		this.doWaitNextVisit = true;
		this.err = new Error();
	}

	@Override
	public Error visit(AbstractCompileOperator absOp) {
		// visit this operator
		err = super.visit(absOp);
		if (err.isError() || this.stop)
			return err;

		// set status for next child
		if (!absOp.getType().equals(EnumOperator.GENERIC_SELECTION))
			this.lastOp = absOp;
		this.doWaitNextVisit = true;

		// pushdown: visit next child
		if (this.cutSelection != null && absOp.getChildren().size() > 0) {
			AbstractCompileOperator child = absOp.getChildren().get(
					this.nextChildIdx);
			err = this.visit(child);
		}
		// visit all children to find selection
		else {
			for (AbstractCompileOperator child : absOp.getChildren()) {
				err = this.visit(child);
				if (err.isError())
					return err;
			}
		}

		return err;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		this.pushDown(ej);
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		// pick selection
		if (this.cutSelection == null
				&& !this.finishedSelections.contains(gs.getOperatorId())) {
			this.cutSelection = gs;
			this.pasteInfo = this.cutSelection.cut();
		}
		// push down selection
		else if (this.cutSelection != null) {
			this.pushDown(gs);
		}
		return err;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		this.pushDown(sa);
		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		this.pushDown(gp);
		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		this.paste();
		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		this.pushDown(ro);
		return err;
	}
	
	@Override
	public Error visitSQLUnary(SQLUnary sqlOp) {
		String[] args = { "SQLUnary operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	/**
	 * Wait for push down
	 * 
	 * @param op
	 * @return
	 */
	private boolean wait(AbstractCompileOperator op) {
		if (op.getParents().size() >= 2) {

			// add current selection to wait list
			Integer parentIdx = op.findParent(this.lastOp);
			if (!this.waitInfo.containsKey(op.getOperatorId())) {
				this.waitInfo.put(op.getOperatorId(),
						new HashMap<Integer, AbstractPredicate>(op
								.getParents().size()));
			}
			Map<Integer, AbstractPredicate> waitPreds = this.waitInfo.get(op
					.getOperatorId());

			// add wait predicate to other predicates
			if (waitPreds.containsKey(parentIdx)) {
				AbstractPredicate waitPred = waitPreds.get(parentIdx);
				ComplexPredicate andPred = new ComplexPredicate(
						EnumPredicateType.AND_PREDICATE);
				andPred.setPredicate1(waitPred);
				andPred.addAnd();
				andPred.addPredicate2(this.cutSelection.getPredicate().clone());
				waitPred = andPred;
				waitPreds.put(parentIdx, waitPred);
			} else {
				AbstractPredicate waitPred = this.cutSelection.getPredicate()
						.clone();
				waitPreds.put(parentIdx, waitPred);
			}

			// paste current selection
			this.paste();

			// check if push down new selection is possible
			int count = 0;
			ComplexPredicate orPred = new ComplexPredicate(
					EnumPredicateType.OR_PREDICATE);
			for (AbstractPredicate waitPred2 : waitPreds.values()) {
				if (waitPred2 != null) {
					if (count == 0) {
						orPred.setPredicate1(waitPred2);
					} else {
						orPred.addOr();
						orPred.addPredicate2(waitPred2);
					}
					count++;
				}
			}

			// push down new selection with disjunctive predicate
			if (count == op.getParents().size()) {
				this.treeRoot = op;
				this.lastOp = op;
				this.modifiedPlan = true;
				this.cutSelection = new GenericSelection(op);
				this.cutSelection.addResult(op.getResult().clone());
				this.cutSelection.setPredicate(orPred);
				this.plan.addOperator(this.cutSelection, false);
				this.doWaitNextVisit = false;
				this.cutSelection.cut();
				this.err = this.visit();
			}

			return true;
		}
		return false;
	}

	/**
	 * pushDown selection over binary operator
	 * 
	 * @param op
	 */
	private void pushDown(AbstractBinaryOperator op) {
		// found selection
		if (this.cutSelection != null) {
			Vector<TokenAttribute> leftChildAtts = op.getLeftChild()
					.getResult().getAttributes();
			Vector<TokenAttribute> rightChildAtts = op.getRightChild()
					.getResult().getAttributes();

			AbstractPredicate predicate = this.cutSelection.getPredicate();

			Collection<TokenAttribute> selAtts = predicate.getAttributes();
			Collection<TokenAttribute> newLeftAtts = TokenAttribute
					.clone(selAtts);
			op.renameForPushDown(newLeftAtts, LEFT_CHILD_IDX);
			Collection<TokenAttribute> newRightAtts = TokenAttribute
					.clone(selAtts);
			op.renameForPushDown(newRightAtts, RIGHT_CHILD_IDX);

			// wait
			if (this.doWaitNextVisit && this.wait(op)) {
				return;
			}
			// push down on left side
			else if (leftChildAtts.containsAll(newLeftAtts)) {
				op.renameForPushDown(selAtts, LEFT_CHILD_IDX);
				this.pasteInfo.clear();
				this.pasteInfo.put(op, LEFT_CHILD_IDX);
				this.nextChildIdx = LEFT_CHILD_IDX;
				return;
			}
			// push down on right side
			else if (rightChildAtts.containsAll(newRightAtts)) {
				op.renameForPushDown(selAtts, RIGHT_CHILD_IDX);
				this.pasteInfo.clear();
				this.pasteInfo.put(op, RIGHT_CHILD_IDX);
				this.nextChildIdx = RIGHT_CHILD_IDX;
				return;
			}
			// stop pushdown
			else {
				this.paste();
				return;
			}
		}
	}
	/**
	 * pushDown selection over SQLJoin or SQLCombined
	 * 
	 * @param op
	 */
	private void pushDown(AbstractJoinOperator op) {
		String[] args = {"Operator of type "+op.getType()+" not supported"};
		this.err = new Error(EnumError.COMPILER_GENERIC, args);
		return;
	}
	
	/**
	 * pushDown selection over unary operator
	 * 
	 * @param op
	 */
	private void pushDown(AbstractUnaryOperator op) {
		// found selection
		if (this.cutSelection != null) {
			Vector<TokenAttribute> childAtts = op.getChild()
					.getResult().getAttributes();
			AbstractPredicate predicate = this.cutSelection.getPredicate();
			Collection<TokenAttribute> selAtts = predicate.getAttributes();
			Collection<TokenAttribute> newAtts = TokenAttribute.clone(selAtts);
			op.renameForPushDown(newAtts);

			// wait
			if (this.doWaitNextVisit && this.wait(op)) {
				return;
			}
			// push down
			else if (childAtts.containsAll(newAtts)) {
				op.renameForPushDown(selAtts);
				this.pasteInfo.clear();
				this.pasteInfo.put(op, CHILD_IDX);
				this.nextChildIdx = 0;
				return;
			}
			// stop pushdown
			else {
				this.paste();
				return;
			}
		}
	}

	
	/**
	 * Pastes selection into plan with given paste info
	 */
	private void paste() {
		if (this.cutSelection == null)
			return;

		for (Map.Entry<AbstractCompileOperator, Integer> entry : this.pasteInfo
				.entrySet()) {
			this.cutSelection.paste(entry.getKey(), entry.getValue());
		}

		this.finishedSelections.add(this.cutSelection.getOperatorId());
		this.pasteInfo.clear();
		this.cutSelection = null;
		this.modifiedPlan = true;
		this.stop();
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		this.pushDown(ej);
		return err;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		String[] args = { "SQLCombined operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}
}
