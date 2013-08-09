package org.xdb.funsql.codegen;

import java.util.List;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
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
 * Extracts operators which should be materialized from leaves to roots (i.e.,
 * operators that have multiple consumers or that are explicitly marked as
 * materialized). Compile plan is analyzed using a bottom-up traversal.
 * 
 * @author cbinnig
 * 
 */
public class SplitPlanVisitor extends AbstractBottomUpTreeVisitor {

	// roots of sub-plans
	private Vector<Identifier> splitOpIds = new Vector<Identifier>();
	
	// last error
	private Error err = new Error();

	// constructor
	public SplitPlanVisitor(AbstractCompileOperator root) {
		super(root);
	}

	// getter and setter
	public List<Identifier> getSplitOpIds() {
		return splitOpIds;
	}

	// methods
	public void reset(AbstractCompileOperator root) {
		this.treeRoot = root;
	}

	/**
	 * Analyze parents of operator and decide if split is required
	 * 
	 * @param op
	 */
	private void doSplit(AbstractCompileOperator op) {
		/* 
		 * Split plan if materialize flag is set. This has do be done
		 * before.
		 */
		if(op.getResult().isMaterialized()) {
			if (!this.splitOpIds.contains(op.getOperatorId()))
				this.splitOpIds.add(op.getOperatorId());
		}
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		doSplit(ej);
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		doSplit(gs);
		return err;
	}

	@Override
	public Error visitFunctionCall(FunctionCall fc) {
		String[] args = { "Codegen: Split plan for function call not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		doSplit(sa);
		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		doSplit(gp);
		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		doSplit(to);
		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		doSplit(ro);
		return err;
	}

	@Override
	public Error visitSQLUnary(SQLUnary sqlOp) {
		doSplit(sqlOp);
		return err;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		doSplit(ej);
		return err;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		doSplit(absOp);
		return err;
	}
}
