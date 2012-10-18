package org.xdb.funsql.compile.operator;

import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;


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
		
		leftChild.addDestinationOperators(this);
		rightChild.addDestinationOperators(this);
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
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		
		//node.getInfo().setHeader(this.parents.toString());
		node.getInfo().setCaption(this.toString());
		
		AbstractOperator leftChildOp = this.children.get(0);
		AbstractOperator rightChildOp = this.children.get(1);
		
		if(!nodes.containsKey(leftChildOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(leftChildOp.operatorId, child);
			leftChildOp.traceGraph(g, nodes);
		}
		if(!nodes.containsKey(rightChildOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(rightChildOp.operatorId, child);
			rightChildOp.traceGraph(g, nodes);
		}
		return err;
	}
}
