package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public abstract class AbstractUnaryOperator extends AbstractCompileOperator {

	private static final long serialVersionUID = 2144601298204477490L;

	// constructors
	public AbstractUnaryOperator(AbstractCompileOperator child) {
		super(1);

		this.children.add(child);
		child.addParent(this);
	}

	/**
	 * Copy Constructor
	 * @param toCopy Element to copy
	 */
	public AbstractUnaryOperator(AbstractUnaryOperator toCopy){
		super(toCopy);
	}

	public AbstractUnaryOperator(Vector<AbstractCompileOperator> fchildren, int resultNumber) {
		super(resultNumber);
		for (AbstractCompileOperator child: fchildren){
			this.children.add(child);
			child.addParent(this);
		}		
	}


	// getters and setters
	public AbstractCompileOperator getChild() {
		return this.children.get(0);
	}

	public void setChild(AbstractCompileOperator child) {
		this.children.set(0, child);
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	// methods
	public abstract void renameForPushDown(Collection<TokenAttribute> selAtts);

	/**
	 * Remove operator from plan
	 */
	public Map<AbstractCompileOperator, Integer> cut(){
		HashMap<AbstractCompileOperator, Integer> cutInfo = new HashMap<AbstractCompileOperator, Integer>();
		
		//Modify child
		AbstractCompileOperator child = this.getChild();
		child.removeParent(this);
		child.addParents(this.parents);
		
		//Modify parents
		for(AbstractCompileOperator p: this.parents){
			int childIdx = p.children.indexOf(this);
			p.children.set(childIdx, child);
			p.renameTableOfAttributes(this.getOperatorId().toString(), child.getOperatorId().toString());
			cutInfo.put(p, childIdx);
		}
		
		//Modify operator
		this.parents.clear();
		
		return cutInfo;
	}
	
	/**
	 * Add operator to plan below given parent
	 * 
	 * @param parent
	 */
	public void paste(AbstractCompileOperator parent, Integer childIdx) {
		// Get old child
		AbstractCompileOperator child = parent.children.get(childIdx);

		// modify parent
		parent.renameTableOfAttributes(child.getOperatorId().toString(),
				this.operatorId.toString());
		parent.children.set(childIdx, this);

		// modify selection
		this.setChild(child);
		this.parents.add(parent);
		ResultDesc newResult = child.getResult().clone();
		TokenAttribute.renameTable(newResult.getAttributes(), this
				.getOperatorId().toString());
		this.replaceResult(newResult);

		// modify child
		int parentIdx = child.parents.indexOf(parent);
		child.parents.set(parentIdx, this);
	}

	@Override
	public Error traceOperator(Graph g, Map<Identifier,GraphNode> nodes) {
		Error err = super.traceOperator(g, nodes);

		// edges and children
		GraphNode node = nodes.get(this.operatorId);

		for (int i = 0; i < this.children.size(); i++) {
			AbstractCompileOperator childOp = this.children.get(i);
			if (!nodes.containsKey(childOp.operatorId)) {
				GraphNode child = g.addNode();
				g.addEdge(node, child);
				nodes.put(childOp.operatorId, child);
				childOp.traceOperator(g, nodes);
			} else {
				GraphNode child = nodes.get(childOp.operatorId);
				g.addEdge(node, child);
			}
		}
		
		return err;
	}

	@Override
	public AbstractUnaryOperator clone() throws CloneNotSupportedException {
		AbstractUnaryOperator abs = (AbstractUnaryOperator) super.clone();
		return abs;
	}
	
}
