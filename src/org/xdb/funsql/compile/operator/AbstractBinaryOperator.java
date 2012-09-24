package org.xdb.funsql.compile.operator;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBinaryOperator extends AbstractOperator {

	private static final long serialVersionUID = -7213914407896295638L;
	
	//attributes
	protected AbstractOperator leftChild;
	protected AbstractOperator rightChild;
	
	protected int leftInputNumber=0;
	protected int rightInputNumber=0;
	
	public Set<AbstractOperator> getSourceOperators() {
		return new HashSet<AbstractOperator>() {{
			add(leftChild);
			add(rightChild);
		}};
	}
	
	//constructors
	public AbstractBinaryOperator(AbstractOperator leftChild, AbstractOperator rightChild) {
		super(1);
		
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	//getters and setters
	public AbstractOperator getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(AbstractOperator leftChild) {
		this.leftChild = leftChild;
	}

	public AbstractOperator getRightChild() {
		return rightChild;
	}

	public void setRightChild(AbstractOperator rightChild) {
		this.rightChild = rightChild;
	}

	public int getLeftInputNumber() {
		return leftInputNumber;
	}

	public void setLeftInputNumber(int leftInputNumber) {
		this.leftInputNumber = leftInputNumber;
	}

	public int getRightInputNumber() {
		return rightInputNumber;
	}

	public void setRightInputNumber(int rightInputNumber) {
		this.rightInputNumber = rightInputNumber;
	}	
}
