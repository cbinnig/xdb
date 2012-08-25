package org.xdb.funsql.compile.operator;

public abstract class AbstractBinaryOperator extends AbstractOperator {

	private static final long serialVersionUID = -7213914407896295638L;
	
	//attributes
	protected AbstractOperator leftChild;
	protected AbstractOperator rightChild;
	
	//constructors
	public AbstractBinaryOperator(AbstractOperator leftChild, AbstractOperator rightChild) {
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
}
