package org.xdb.funsql.compile.analyze.operator;


import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractOperator;

public abstract class AbstractBottomUpTreeVisitor extends AbstractTreeVisitor {

	public AbstractBottomUpTreeVisitor(AbstractOperator root) {
		super(root);
	}

	@Override
	public Error visit(AbstractOperator absOp) {
		Error e = new Error();
		
		for(AbstractOperator child: absOp.getSourceOperators()){
			e = this.visit(child);
			if(e.isError())
				return e;
		}
		
		e = super.visit(absOp);
		return e;
	}
}
