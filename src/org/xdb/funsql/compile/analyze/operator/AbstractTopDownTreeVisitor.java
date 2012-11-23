package org.xdb.funsql.compile.analyze.operator;


import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractOperator;

public abstract class AbstractTopDownTreeVisitor extends AbstractTreeVisitor {

	public AbstractTopDownTreeVisitor(AbstractOperator root) {
		super(root);
	}

	@Override
	public Error visit(AbstractOperator absOp) {
		Error e = new Error();
		
		e = super.visit(absOp);
		
		if(this.stop)
			return e;
		
		for(AbstractOperator child: absOp.getSourceOperators()){
			e = this.visit(child);
			if(e.isError())
				return e;
		}
		
		return e;
	}
}
