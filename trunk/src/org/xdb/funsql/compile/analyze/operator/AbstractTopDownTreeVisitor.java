package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

/**
 * visits a compile plan in a bottom up fashion
 * 
 * @author cbinnig
 * 
 */
public abstract class AbstractTopDownTreeVisitor extends AbstractTreeVisitor {

	public AbstractTopDownTreeVisitor(){
		super();
	}
	
	public AbstractTopDownTreeVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visit(AbstractCompileOperator absOp) {
		Error e = new Error();

		// first visit operator
		e = super.visit(absOp);

		// then check if visitor needs to stop
		if (this.stopped())
			return e;

		// finally, visit all children
		for (int i = 0; i < absOp.getChildren().size(); i++) {
			e = this.visit(absOp.getChildren().get(i));
			if (e.isError())
				return e;
		}
		return e;
	}
}
