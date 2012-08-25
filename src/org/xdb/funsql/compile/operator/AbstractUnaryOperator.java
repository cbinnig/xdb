package org.xdb.funsql.compile.operator;

public abstract class AbstractUnaryOperator extends AbstractOperator {
	
	private static final long serialVersionUID = 2144601298204477490L;

	//attributes
	protected AbstractOperator child;

	//constructors
	public AbstractUnaryOperator(AbstractOperator child){
		this.child = child;
	}
	
	//getters and setters
	public AbstractOperator getChild() {
		return child;
	}

	public void setChild(AbstractOperator child) {
		this.child = child;
	}
}
