package org.xdb.funsql.parallelize;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.MaterializationAnnotationVisitor;

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
		
		// add materialization info
		MaterializationAnnotationVisitor visitor = new MaterializationAnnotationVisitor();
		err = compilePlan.applyVisitor(visitor);
		if (err.isError())
			return err;
				
		// add repartition info
		CreatePartitionDescVisitor repartVisitor = new CreatePartitionDescVisitor(this.compilePlan);
		err = compilePlan.applyVisitor(repartVisitor);
		if (err.isError())
			return err;
		
		// tracing
		if (Config.TRACE_PARALLEL_PLAN) {
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_PARALLELIZED");
		}

		return err;
	}
}
