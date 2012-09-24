package org.xdb.funsql.compile.operator;


public abstract class AbstractBinaryOperator extends AbstractOperator {

	private static final long serialVersionUID = -7213914407896295638L;
	
	//attributes
	protected int leftInputNumber=0;
	protected int rightInputNumber=0;
	
	//constructors
	public AbstractBinaryOperator(AbstractOperator leftChild, AbstractOperator rightChild) {
		super(1);
		
		this.children.add(leftChild);
		this.children.add(rightChild);
	}

	//getters and setters
	public AbstractOperator getLeftChild() {
		return this.children.get(0);
	}

	public void setLeftChild(AbstractOperator leftChild) {
		this.children.set(0,leftChild);;
	}

	public AbstractOperator getRightChild() {
		return this.children.get(1);
	}

	public void setRightChild(AbstractOperator rightChild) {
		this.children.set(1,rightChild);;
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
