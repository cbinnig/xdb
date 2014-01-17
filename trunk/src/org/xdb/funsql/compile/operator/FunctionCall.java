package org.xdb.funsql.compile.operator;

import java.util.Vector;
import java.util.Map;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
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
		this.function = function;
		
		this.type = EnumOperator.FUNCTION_CALL;
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
		
		this.type = EnumOperator.FUNCTION_CALL;
	}
	
	//getters and setters
	public void addResult(ResultDesc result) {
		this.results.add(result);
	}
	
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
		return this.function.toSqlString();
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
		if (Config.TRACE_COMPILE_PLAN_FOOTER) {
			StringBuffer footer = new StringBuffer();
			footer.append(this.function.getName().toString());
			if (node.getInfo().getFooter() != null) {
				footer.append(AbstractToken.NEWLINE);
				footer.append(node.getInfo().getFooter());
			}
			node.getInfo().setFooter(footer.toString());
		}
		return err;
	}

	@Override
	public void renameTableOfAttributes(String oldId, String newId) {
		//Nothing to do at the moment
	}
}
