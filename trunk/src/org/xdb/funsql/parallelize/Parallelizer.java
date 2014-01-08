package org.xdb.funsql.parallelize;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

/**
 * Creates a parallelized plan from a compile plan
 * by adding repartitioning information to each result
 * 
 * @author cbinnig
 *
 */
public class Parallelizer {
	// compile plan
	private CompilePlan compilePlan;

	// constructors
	public Parallelizer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
	}

	public Error parallelize() {
		Error err = new Error();

		// repartition
		err = repartitionOps();
		if (err.isError())
			return err;
		
		// tracing
		if (Config.TRACE_PARALLEL_PLAN) {
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_PARALLELIZED");
		}

		return err;
	}

	private Error repartitionOps() {
		Error err = new Error();
		for (AbstractCompileOperator root : this.compilePlan
				.getRootsCollection()) {
			CreatePartitionDescVisitor repartVisitor = new CreatePartitionDescVisitor(this.compilePlan,
					root);
			err = repartVisitor.visit();

			if (err.isError())
				return err;
		}
		return err;
	}
}
