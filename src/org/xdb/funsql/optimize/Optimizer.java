package org.xdb.funsql.optimize;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.error.Error;

public class Optimizer {
	CompilePlan compilePlan;

	public Optimizer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
	}

	public Error optimize() {
		Error err = new Error();
		
		//selection pushdown
		SelectionPushDownVisitor pushDownVisitor = new SelectionPushDownVisitor(null, compilePlan);
		for(AbstractOperator root: this.compilePlan.getRoots()){
			boolean modified = true;
			
			while(modified){
				pushDownVisitor.reset(root);
				err = pushDownVisitor.visit();
				modified = pushDownVisitor.modifiedPlan();
				
				if(err.isError())
					return err;
				
				this.compilePlan.traceGraph(this.getClass().getName());
			}
		}
		
		return err;
	}
}
