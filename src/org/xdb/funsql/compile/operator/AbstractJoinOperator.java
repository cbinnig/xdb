package org.xdb.funsql.compile.operator;


import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;
import org.xdb.utils.TokenPair;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public abstract class AbstractJoinOperator extends AbstractCompileOperator  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1718233853455161907L;
	protected Vector<TokenPair> jointokens = new Vector<TokenPair>();

	public AbstractJoinOperator(AbstractCompileOperator toCopy) {
		super(toCopy);
		
	
	}
	
	public void addChildren(AbstractCompileOperator child){
		this.children.add(child);
	}
	public AbstractCompileOperator getChild(int idx) {
		return this.children.get(idx);
	}
	public abstract void renameForPushDown(Collection<TokenAttribute> selAtts, int child);
	
	@Override
	public Error traceOperator(Graph g, HashMap<Identifier, GraphNode> nodes) {
		Error err = super.traceOperator(g, nodes);
	
		// edges and children
		GraphNode node = nodes.get(this.operatorId);
		AbstractCompileOperator childOp;
		for(int i = 0; i < this.children.size(); i++){
			childOp = this.children.get(i);

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

}
