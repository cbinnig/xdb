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

		err = this.preOptimize(optimizeRule);
		if (err.isError())
			return err;

		err = this.preParallelize();
		if (err.isError())
			return err;

		err = this.postOptimize(optimizeRule);
		if (err.isError())
			return err;

		err = this.postParallelize();
		if (err.isError())
			return err;

		// tracing
		if (Config.TRACE_OPTIMIZED_PLAN) {
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_OPTIMIZED");
		}

		return err;
	}

	/**
	 * Algebraic rewrites before inserting re-partition operations
	 * 
	 * @param optimizeRule
	 * @return
	 */
	private Error preOptimize(BitSet optimizeRule) {
		Error err = new Error();

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

		return err;
	}

	/**
	 * Insert re-partition operations into plan
	 * 
	 * @return
	 */
	private Error preParallelize() {
		Error err = new Error();
		return err;
	}

	/**
	 * Additional optimizations for plan with re-partition operations (e.g.,
	 * aggregation push-down)
	 * 
	 * @param optimizeRule
	 * @return
	 */
	private Error postOptimize(BitSet optimizeRule) {
		Error err = new Error();
		return err;
	}

	/**
	 * Create parallel plan for multiple partitions
	 * 
	 * @return
	 */
	private Error postParallelize() {
		Error err = new Error();
		return err;
	}

	/**
	 * Pushes down selections in plan
	 * 
	 * @return
	 */
	private Error pushSelections() {
		Error err = new Error();

		SelectionPushDownVisitor pushDownVisitor = new SelectionPushDownVisitor(
				null, compilePlan);
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
		for (AbstractCompileOperator root : this.compilePlan
				.getRootsCollection()) {
			SelectionCombineVisitor combineVisitor = new SelectionCombineVisitor(
					root);
			err = combineVisitor.visit();

			if (err.isError())
				return err;
		}
		return err;
	}
}
