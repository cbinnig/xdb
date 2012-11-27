package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;


public abstract class AbstractUnaryOperator extends AbstractCompileOperator {
	
	private static final long serialVersionUID = 2144601298204477490L;

	//attributes
	protected int inputNumber=0;

	//constructors
	public AbstractUnaryOperator(AbstractCompileOperator child){
		super(1);
		
		this.children.add(child);
		child.addDestinationOperators(this);
	}
	
	//getters and setters
	public ResultDesc getResult(){
		return this.results.get(0);
	}
	
	public AbstractCompileOperator getChild() {
		return this.children.get(0);
	}

	public void setChild(AbstractCompileOperator child) {
		this.children.set(0, child);
	}

	public int getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(int inputNumber) {
		this.inputNumber = inputNumber;
	}
	
	@Override
	public boolean isLeaf(){
		return false;
	}
	
	//methods
	public abstract void renameForPushDown(Collection<TokenAttribute> selAtts);
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		if(this.results.size()==1)
			node.getInfo().setHeader(this.results.get(0).toString());
		node.getInfo().setCaption(this.toString());
		AbstractCompileOperator childOp = this.children.get(0);
		
		if(!nodes.containsKey(childOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(childOp.operatorId, child);
			childOp.traceGraph(g, nodes);
		}
		else{
			GraphNode child = nodes.get(childOp.operatorId);
			g.addEdge(node, child);
		}
		return err;
	}
}
