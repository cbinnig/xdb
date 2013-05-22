package org.xdb.funsql.compile.operator;

import java.util.Vector;
import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class FunctionCall extends AbstractCompileOperator implements Cloneable {

	private static final long serialVersionUID = -7332127330583927641L;
	private Vector<AbstractCompileOperator> children;
	private TokenFunction function;
	
	//constructors
	public FunctionCall(TokenFunction function,int resultNumber) {
		super(resultNumber);		
		//this.children = children; TODO
		this.function = function;
		
		this.setType(EnumOperator.FUNCTION_CALL);
	}
	/**
	 * Copy Constructor
	 * @param toCopy Element to copy
	 */
	public FunctionCall(FunctionCall toCopy){
		super(toCopy);
		try {
			this.function = toCopy.function.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		this.children = toCopy.children;
		
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
		child.addParent(this);
	}
	
	public AbstractCompileOperator getChild(int i){
		return this.children.get(i);
	}
	
	public Vector<AbstractCompileOperator> getChildren(){
		return this.children;
	}
	
	@Override
	public String toSqlString() {
		return this.function.toSqlString();//TODO
	}
	
	@Override
	public boolean isLeaf(){
		return false;
	}
	
	
	@Override
	public Error traceOperator(Graph g, Map<Identifier,GraphNode> nodes){
		Error err = super.traceOperator(g, nodes);
		if(err.isError())
			return err;
		
		GraphNode node = nodes.get(this.operatorId);
		node.getInfo().setFooter(this.function.getName().toString());
		return err;
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		//Nothing to do
	}


}
