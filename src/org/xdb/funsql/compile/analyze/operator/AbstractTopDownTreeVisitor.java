package org.xdb.funsql.compile.analyze.operator;


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
		
		for(AbstractCompileOperator child: absOp.getChildren()){
			e = this.visit(child);
			if(e.isError())
				return e;
		}
		
		return e;
	}
}
