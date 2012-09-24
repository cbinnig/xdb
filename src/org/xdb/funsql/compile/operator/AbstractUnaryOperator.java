package org.xdb.funsql.compile.operator;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractUnaryOperator extends AbstractOperator {
	
	private static final long serialVersionUID = 2144601298204477490L;

	//attributes
	protected AbstractOperator child;
	protected int inputNumber=0;

	//constructors
	public AbstractUnaryOperator(AbstractOperator child){
		super(1);
		
		this.child = child;
	}
	
	public Set<AbstractOperator> getSourceOperators() {
		return new HashSet<AbstractOperator>() {{
			add(child);
		}};
	}
	
	//getters and setters
	public AbstractOperator getChild() {
		return child;
	}

	public void setChild(AbstractOperator child) {
		this.child = child;
	}

	public int getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(int inputNumber) {
		this.inputNumber = inputNumber;
	}
}
