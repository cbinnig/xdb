package org.xdb.funsql.optimize;

import java.util.BitSet;

import org.xdb.Config;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.error.Error;

/**
 * Optimizer which rewrites a graph-based plan
 * 
 * @author cbinnig
 * 
 */
public class Optimizer {

	// compile plan
	private CompilePlan compilePlan;

	// constructors
	public Optimizer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
	}

	/**
	 * Optimizes a graph-based compile plan
	 * 
	 * @return
	 */
	public Error optimize(BitSet optimizeRule) {
		Error err = new Error();

		// tracing
		if (Config.TRACE_COMPILE_PLAN) {
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_COMPILED");
		}

		// rewrite: push down selection
		if (optimizeRule.get(0)) {
			err = pushSelections();
			if (err.isError())
				return err;
		}

		// rewrite: combine selections
		if (optimizeRule.get(1)) {
			err = combineSelections();
			if (err.isError())
				return err;
		}

		// tracing
		if (Config.TRACE_OPTIMIZED_PLAN) {
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_OPTIMIZED");
		}

		return err;
	}

	/**
	 * Pushes down selections in plan
	 * 
	 * @return
	 */
	private Error pushSelections() {
		Error err = new Error();

		SelectionPushDownVisitor pushDownVisitor = new SelectionPushDownVisitor(compilePlan);
		for (AbstractCompileOperator root : this.compilePlan
				.getRootsCollection()) {
			boolean modified = true;

			while (modified) {
				pushDownVisitor.reset(root);
				err = pushDownVisitor.visit();
				modified = pushDownVisitor.modifiedPlan();

				if (err.isError())
					return err;
			}
		}

		return err;
	}

	/**
	 * Combines selection operators in plan
	 * 
	 * @return
	 */
	private Error combineSelections() {
		Error err = new Error();
		SelectionCombineVisitor combineVisitor = new SelectionCombineVisitor();
		
		for (AbstractCompileOperator root : this.compilePlan
				.getRootsCollection()) {
			
			combineVisitor.reset(root);
			err = combineVisitor.visit();

			if (err.isError())
				return err;
		}
		return err;
	}
}
