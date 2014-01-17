package org.xdb.funsql.compile.analyze.operator;

import org.xdb.Config;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

public abstract class AbstractAnnotationVisitor extends AbstractBottomUpTreeVisitor{

	public AbstractAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}

	protected void applyGlobalMaterializeRules(final AbstractCompileOperator op) {
		/*
		 * Force materialize if 
		 * - an operator has no parent (i.e, it is a root) 
		 * - an operator has > 1 parents
		 */
		if (op.getParents().size() != 1) {
			op.getResult().materialize(true);
		}
	}
	
	public static AbstractAnnotationVisitor createAnnotationVisitor(AbstractCompileOperator root){
		switch(Config.QUERYTRACKER_STRATEGY){
		case PERFORMANCE:
			return new PerfOrientedAnnotationVisitor(root);
		case ROBUST:
			return new RobustnessOrientedAnnotationVisitor(root);
		default:
			return null;
		}
	}
}
