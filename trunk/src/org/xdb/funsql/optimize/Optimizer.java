package org.xdb.funsql.optimize;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractOperator;
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
	public Error optimize() {
		Error err = new Error();

		// rewrite: push down selection
		err = pushSelections();
		if(err.isError())
			return err;

		// rewrite: combine selections
		err = combineSelections();
		if(err.isError())
			return err;
		
		// TODO: other rewrites which eliminate projections

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
		for (AbstractOperator root : this.compilePlan.getRoots()) {
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
	 * @return
	 */
	private Error combineSelections() {
		Error err = new Error();
		for (AbstractOperator root : this.compilePlan.getRoots()) {
			SelectionCombineVisitor combineVisitor = new SelectionCombineVisitor(root);
			err = combineVisitor.visit();
			
			if (err.isError())
				return err;
		}
		return err;
	}

}
