package org.xdb.funsql.compile.analyze.operator;


import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

public abstract class AbstractBottomUpTreeVisitor extends AbstractTreeVisitor {

	public AbstractBottomUpTreeVisitor(){
		super();
	}
	
	public AbstractBottomUpTreeVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visit(AbstractCompileOperator absOp) {
		Error e = new Error();
		
		if(this.stop)
			return e;
		
		if(absOp==null)
			System.out.println("STOP");
		
		for(AbstractCompileOperator child: absOp.getChildren()){
			e = this.visit(child);
			if(e.isError())
				return e;
		}
		
		e = super.visit(absOp);
		return e;
	}
}
