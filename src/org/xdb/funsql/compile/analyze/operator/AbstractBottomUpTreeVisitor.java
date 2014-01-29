package org.xdb.funsql.compile.analyze.operator;


import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

/**
 * visits a compile plan in a bottom up fashion
 * @author cbinnig
 *
 */
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
		
		//check if visitor needs to stop
		if(this.stopped())
			return e;
		
		//first visit all children
		for(AbstractCompileOperator child: absOp.getChildren()){
			e = this.visit(child);
			if(e.isError())
				return e;
		}
		
		//then visit operator
		e = super.visit(absOp);
		return e;
	}
}
