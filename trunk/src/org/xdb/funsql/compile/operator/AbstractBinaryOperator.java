package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;


public abstract class AbstractBinaryOperator extends AbstractCompileOperator {

	private static final long serialVersionUID = -7213914407896295638L;
	
	//attributes
	protected int leftInputNumber=0;
	protected int rightInputNumber=0;
	
	//constructors
	public AbstractBinaryOperator(AbstractCompileOperator leftChild, AbstractCompileOperator rightChild) {
		super(1);
		
		this.children.add(leftChild);
		this.children.add(rightChild);
		
		leftChild.addDestinationOperators(this);
		rightChild.addDestinationOperators(this);
	}

	//getters and setters
	public ResultDesc getResult(){
		return this.results.get(0);
	}
	
	public AbstractCompileOperator getChild(int idx) {
		return this.children.get(idx);
	}
	
	public AbstractCompileOperator getLeftChild() {
		return this.children.get(0);
	}

	public void setLeftChild(AbstractCompileOperator leftChild) {
		this.children.set(0,leftChild);;
	}

	public AbstractCompileOperator getRightChild() {
		return this.children.get(1);
	}

	public void setRightChild(AbstractCompileOperator rightChild) {
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
	public boolean isLeaf(){
		return false;
	}
	
	//methods
	public abstract void renameForPushDown(Collection<TokenAttribute> selAtts, int child);
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		
		if(this.results.size()==1)
			node.getInfo().setHeader(this.results.get(0).toString());
		node.getInfo().setCaption(this.toString());
		
		AbstractCompileOperator leftChildOp = this.children.get(0);
		AbstractCompileOperator rightChildOp = this.children.get(1);
		
		if(!nodes.containsKey(leftChildOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(leftChildOp.operatorId, child);
			leftChildOp.traceGraph(g, nodes);
		}
		else{
			GraphNode child = nodes.get(leftChildOp.operatorId);
			g.addEdge(node, child);
		}
		
		if(!nodes.containsKey(rightChildOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(rightChildOp.operatorId, child);
			rightChildOp.traceGraph(g, nodes);
		}
		else{
			GraphNode child = nodes.get(rightChildOp.operatorId);
			g.addEdge(node, child);
		}
		return err;
	}
}
