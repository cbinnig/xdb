package org.xdb.funsql.compile.operator;

import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;


public abstract class AbstractUnaryOperator extends AbstractOperator {
	
	private static final long serialVersionUID = 2144601298204477490L;

	//attributes
	protected int inputNumber=0;

	//constructors
	public AbstractUnaryOperator(AbstractOperator child){
		super(1);
		
		this.children.add(child);
		child.addDestinationOperators(this);
	}
	
	//getters and setters
	public AbstractOperator getChild() {
		return this.children.get(0);
	}

	public void setChild(AbstractOperator child) {
		this.children.set(0, child);
	}

	public int getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(int inputNumber) {
		this.inputNumber = inputNumber;
	}
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		if(this.results.size()==1)
			node.getInfo().setHeader(this.results.get(0).toSqlString());
		node.getInfo().setCaption(this.toString());
		AbstractOperator childOp = this.children.get(0);
		
		if(!nodes.containsKey(childOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(childOp.operatorId, child);
			childOp.traceGraph(g, nodes);
		}
		return err;
	}
}
