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
	 * Optimizes a graph-based plan
	 * 
	 * @return
	 */
	public Error optimize(BitSet optimizeRule ) {
		Error err = new Error();
	
		// tracing
		if (Config.TRACE_COMPILE_PLAN)
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_COMPILED");

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
		if (Config.TRACE_OPTIMIZED_PLAN)
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_OPTIMIZED");

		// rewrite: combine joins
		if (optimizeRule.get(3)) {
			err = combineJoins();
			if (err.isError())
				return err;
		}
		
		// rewrite: combine unary operators
		if (optimizeRule.get(2)) {
			err = combineUnaryOps();
			if (err.isError())
				return err;
		}
	
		if (Config.TRACE_OPTIMIZED_PLAN)
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_NOTMERGED");

		// rewrite combine unary and sqljoin operators into sqlcombined operator
		if (optimizeRule.get(4)) {
			err = combineSQLOps();
				if(err.isError())
					return err;
		}
		
		// tracing
		if (Config.TRACE_OPTIMIZED_PLAN)
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_COMBINED");

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
		for (AbstractCompileOperator root : this.compilePlan.getRootsCollection()) {
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
		for (AbstractCompileOperator root : this.compilePlan.getRootsCollection()) {
			SelectionCombineVisitor combineVisitor = new SelectionCombineVisitor(
					root);
			err = combineVisitor.visit();

			if (err.isError())
				return err;
		}
		return err;
	}

	/**
	 * Combines join operators in plan
	 * 
	 * @return
	 */
	private Error combineJoins() {
		Error err = new Error();
		for (AbstractCompileOperator root :  this.compilePlan.getRootsCollection()) {
			JoinCombineVisitor combineVisitor = new JoinCombineVisitor(
					root, this.compilePlan);
			err = combineVisitor.visit();

			if (err.isError())
				return err;
		}
		return err;
	}
	
	
	/**
	 * Combines selection operators in plan
	 * 
	 * @return
	 */
	private Error combineUnaryOps() {
		Error err = new Error();
		for (AbstractCompileOperator root : this.compilePlan.getRootsCollection()) {
			SQLUnaryCombineVisitor combineVisitor = new SQLUnaryCombineVisitor(
					root, this.compilePlan);
			err = combineVisitor.visit();

			if (err.isError())
				return err;
		}
		return err;
	}
	
	/**
	 * Combines selection operators in plan
	 * 
	 * @return
	 */
	private Error combineSQLOps() {
		Error err = new Error();
		for (AbstractCompileOperator root : this.compilePlan.getRootsCollection()) {
			SQLCombineVisitor combineVisitor = new SQLCombineVisitor(
					root, this.compilePlan);
			err = combineVisitor.visit();

			if (err.isError())
				return err;
		}
		return err;
	}
}
