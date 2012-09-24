package org.xdb.funsql.compile.operator;


public abstract class AbstractUnaryOperator extends AbstractOperator {
	
	private static final long serialVersionUID = 2144601298204477490L;

	//attributes
	protected int inputNumber=0;

	//constructors
	public AbstractUnaryOperator(AbstractOperator child){
		super(1);
		
		this.children.add(child);
	}
	
	//getters and setters
	public AbstractOperator getChild() {
		return this.children.get(0);
	}

	public void setChild(AbstractOperator child) {
		this.children.set(0, child);
	}

	public int getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(int inputNumber) {
		this.inputNumber = inputNumber;
	}
}
