package org.xdb.funsql.compile.analyze.operator;


import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

public abstract class AbstractTopDownTreeVisitor extends AbstractTreeVisitor {

	public AbstractTopDownTreeVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visit(AbstractCompileOperator absOp) {
		Error e = new Error();
		
		e = super.visit(absOp);
		
		if(this.stop)
			return e;
		
		for(int i = 0; i < absOp.getChildren().size(); i ++){
			e = this.visit(absOp.getChildren().get(i));
			if(e.isError())
				return e;
		}
		return e;
	}
}
