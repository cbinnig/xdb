package org.xdb.funsql.compile;

import org.xdb.funsql.compile.operator.AbstractOperator;

public abstract class AbstractBottomUpTreeVisitor extends AbstractTreeVisitor {

	public AbstractBottomUpTreeVisitor(AbstractOperator root) {
		super(root);
	}

	public void visit(){
		this.visit(thisRoot);
	}

	@Override
	public void visit(AbstractOperator absOp) {
		for(AbstractOperator child: absOp.getSourceOperators()){
			this.visit(child);
		}
		
		super.visit(absOp);
	}
}
