package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class FunctionCall extends AbstractCompileOperator {

	private static final long serialVersionUID = -7332127330583927641L;
	private Vector<AbstractCompileOperator> children;
	private TokenFunction function;
	
	//constructors
	public FunctionCall(TokenFunction function, int inputNumber, int resultNumber) {
		super(resultNumber);
		
		this.children = new Vector<AbstractCompileOperator>(inputNumber);
		this.function = function;
	}
	
	//getters and setters
	public TokenFunction getFunction() {
		return function;
	}

	public void setFunction(TokenFunction function) {
		this.function = function;
	}
	
	public void addChild(int i, AbstractCompileOperator child){
		this.children.set(i, child);
		child.addDestinationOperators(this);
	}
	
	public AbstractCompileOperator getChild(int i){
		return this.children.get(i);
	}
	
	public Vector<AbstractCompileOperator> getChildren(){
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
