package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

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
		child.addDestinationOperators(this);
	}
	
	public AbstractOperator getChild(int i){
		return this.children.get(i);
	}
	
	public Vector<AbstractOperator> getChildren(){
		return this.children;
	}
	
	@Override
	public String toSqlString() {
		return null;
	}
	
	@Override
	public boolean isLeaf(){
		return false;
	}
	
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		
		return err;
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		//Nothing to do
	}
}
