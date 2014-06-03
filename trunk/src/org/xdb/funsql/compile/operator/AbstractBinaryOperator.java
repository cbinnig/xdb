package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;


public abstract class AbstractBinaryOperator extends AbstractCompileOperator implements Cloneable{

	private static final long serialVersionUID = -7213914407896295638L;
	
	//attributes
	protected int leftInputNumber=0;
	protected int rightInputNumber=0;
	
	//constructors
	public AbstractBinaryOperator(AbstractCompileOperator leftChild, AbstractCompileOperator rightChild) {
		super(1);
		
		this.children.add(leftChild);
		this.children.add(rightChild);
		
		leftChild.addParent(this);
		rightChild.addParent(this);
	}
	
	public AbstractBinaryOperator(){
		
	}
	
	/** Copy Constructor
	 * @param toCopy
	 */
	public AbstractBinaryOperator (AbstractBinaryOperator toCopy){
		super(toCopy);
		this.leftInputNumber = toCopy.leftInputNumber;
		this.rightInputNumber = toCopy.rightInputNumber;
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
	public Error traceOperator(Graph g, Map<Identifier,GraphNode> nodes){
		Error err = super.traceOperator(g, nodes);
		
		//edges and children
		GraphNode node = nodes.get(this.operatorId);
		AbstractCompileOperator leftChildOp = this.children.get(0);
		AbstractCompileOperator rightChildOp = this.children.get(1);
		
		if(!nodes.containsKey(leftChildOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(leftChildOp.operatorId, child);
			leftChildOp.traceOperator(g, nodes);
		}
		else{
			GraphNode child = nodes.get(leftChildOp.operatorId);
			g.addEdge(node, child);
		}
		
		if(!nodes.containsKey(rightChildOp.operatorId)){
			GraphNode child = g.addNode();
			g.addEdge(node, child);
			nodes.put(rightChildOp.operatorId, child);
			rightChildOp.traceOperator(g, nodes);
		}
		else{
			GraphNode child = nodes.get(rightChildOp.operatorId);
			g.addEdge(node, child);
		}
		return err;
	}



	@Override
	public AbstractBinaryOperator clone() throws CloneNotSupportedException {
	
		AbstractBinaryOperator obj = (AbstractBinaryOperator) super.clone();
		obj.leftInputNumber = this.leftInputNumber;
		obj.rightInputNumber = this.rightInputNumber;
		return obj;
	}
	
	
}
