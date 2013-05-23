package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.TokenPair;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

/**
 * Abstract class which is the super class of n-way joins
 * 
 * @author A.C.Mueller
 * 
 */
public abstract class AbstractJoinOperator extends AbstractCompileOperator {

	private static final long serialVersionUID = -1718233853455161907L;
	protected Vector<TokenPair> jointokens = new Vector<TokenPair>();

	public AbstractJoinOperator(AbstractCompileOperator toCopy) {
		super(toCopy);

	}

	/**
	 * Copy Constructor
	 * 
	 * @param toCopy
	 *            Element to copy
	 */

	public AbstractJoinOperator(AbstractJoinOperator toCopy) {
		super(toCopy);
		this.jointokens = new Vector<TokenPair>();

		for (TokenPair tp : toCopy.jointokens) {
			this.jointokens.add(new TokenPair(tp));

		}

	}

	public Vector<TokenPair> getJointokens() {
		return jointokens;
	}

	public void addChildren(AbstractCompileOperator child) {
		this.children.add(child);
	}

	public AbstractCompileOperator getChild(int idx) {
		return this.children.get(idx);
	}

	public abstract void renameForPushDown(Collection<TokenAttribute> selAtts,
			int child);

	@Override
	public Error traceOperator(Graph g, Map<Identifier, GraphNode> nodes) {
		Error err = super.traceOperator(g, nodes);

		// edges and children
		GraphNode node = nodes.get(this.operatorId);
		AbstractCompileOperator childOp;
		for (int i = 0; i < this.children.size(); i++) {
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

	public void renameJoinTokens(HashMap<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		String newName;
		for (TokenPair tP : getJointokens()) {
			// rename left Token
			if (renamedOps.contains(tP.getLeftTokenAttribute().getTable()
					.getName())) {
				newName = renamedAttributes.get(tP.getLeftTokenAttribute()
						.getName());
				tP.getLeftTokenAttribute().setName(newName);
			}
			// rename right Token
			if (renamedOps.contains(tP.getRightTokenAttribute().getTable()
					.getName())) {
				newName = renamedAttributes.get(tP.getRightTokenAttribute()
						.getName());
				tP.getRightTokenAttribute().setName(newName);
			}
		}
	}

	@Override
	public boolean renameOperator(HashMap<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		boolean renamed = false;
		// call super Method
		renamed = super.renameOperator(renamedAttributes, renamedOps);
		// rename Join Tokens
		this.renameJoinTokens(renamedAttributes, renamedOps);
		// return
		return renamed;
	}
}
