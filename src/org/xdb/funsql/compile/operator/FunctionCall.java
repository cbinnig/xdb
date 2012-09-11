package org.xdb.funsql.compile.operator;

import java.util.Vector;

import org.xdb.funsql.compile.tokens.TokenFunction;

public class FunctionCall extends AbstractOperator {

	private static final long serialVersionUID = -7332127330583927641L;
	private Vector<AbstractOperator> children;
	private TokenFunction function;
	
	//constructors
	public FunctionCall(TokenFunction function, int inputNumber, int resultNumber) {
		super(resultNumber);
		
		this.children = new Vector<AbstractOperator>(inputNumber);
		this.function = function;
	}
	
	//getters and setters
	public TokenFunction getFunction() {
		return function;
	}

	public void setFunction(TokenFunction function) {
		this.function = function;
	}
	
	public void addChild(int i, AbstractOperator child){
		this.children.set(i, child);
	}
	
	public AbstractOperator getChild(int i){
		return this.children.get(i);
	}
	
	public Vector<AbstractOperator> getChildren(){
		return this.children;
	}
}
