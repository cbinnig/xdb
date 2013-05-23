package org.xdb.funsql.compile.analyze.operator;

import org.xdb.Config;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

public abstract class AbstractAnnotationVisitor extends AbstractBottomUpTreeVisitor{

	public AbstractAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}

	public static AbstractAnnotationVisitor createAnnotationVisitor(AbstractCompileOperator root){
		switch(Config.QUERYTRACKER_STRATEGY){
		case PERFORMANCE:
			return new PerfOrientedAnnotationVisitor(root);
		case ROBUST:
			return new RobustnessOrientedAnnotationVisitor(root);
		default:
			return new PerfOrientedAnnotationVisitor(root);
		}
	}
}
