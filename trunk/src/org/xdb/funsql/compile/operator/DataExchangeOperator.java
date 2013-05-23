package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.parallelize.PartitionInfo;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

/**
 * Logical operator, which is used to indicate the need for repartitioning in
 * the current data flow
 * 
 * @author Alexander C. Mueller
 * 
 */
public class DataExchangeOperator extends AbstractUnaryOperator {

	private static final long serialVersionUID = 6320968568439747158L;

	private PartitionInfo inputPartitioning;

	/**
	 * Constructor used when exactly one partition column can be used
	 * 
	 * @param child
	 * @param result
	 * @param outputPartioning
	 */
	public DataExchangeOperator(AbstractCompileOperator child,
			ResultDesc result, PartitionInfo outputPartioning) {
		super(child);
		this.partitionOutputInfo = outputPartioning;
		this.results.add(result);
		this.type = EnumOperator.DATA_EXCHANGE;
	}

	/**
	 * Copy Constructor
	 * 
	 * @param toCopy
	 *            Element to copy
	 */
	public DataExchangeOperator(DataExchangeOperator toCopy) {
		super(toCopy);
		if (toCopy.inputPartitioning != null) {
			this.inputPartitioning = new PartitionInfo(toCopy.inputPartitioning);
		}
		this.type = EnumOperator.DATA_EXCHANGE;
	}

	/**
	 * Constructor used when exactly one partition column can be used
	 * 
	 * @param child
	 * @param result
	 * @param outputPartioning
	 */
	public DataExchangeOperator(AbstractCompileOperator child, ResultDesc result) {
		super(child);
		this.results.add(result);
		this.type = EnumOperator.DATA_EXCHANGE;

	}

	// getters + setters
	public PartitionInfo getInputPartitioning() {
		return inputPartitioning;
	}

	public void setInputPartitioning(PartitionInfo inputPartitioning) {
		this.inputPartitioning = inputPartitioning;
	}

	public boolean hasInputPartitioning() {
		return this.inputPartitioning != null;
	}

	// methods
	@Override
	public String toSqlString() {
		Vector<AbstractCompileOperator> children = getChildren();
		if (children.size() == 1)
			return "<" + children.get(0).getOperatorId().toString() + ">";

		AbstractCompileOperator union = new Union(children.get(0),
				children.get(1));
		for (int i = 2; i < children.size(); i++) {
			union.addChild(children.get(i));
		}

		return union.toSqlString();
	}

	@Override
	public void renameTableOfAttributes(String oldChildId, String newChildId) {
		// TODO check if needed

	}

	@Override
	public boolean isLeaf() {
		// can never be a leaf node
		return false;
	}

	@Override
	public Error traceOperator(Graph g, Map<Identifier, GraphNode> nodes) {
		Error err = super.traceOperator(g, nodes);

		GraphNode node = nodes.get(this.operatorId);

		if (Config.TRACE_COMPILE_PLAN_FOOTER) {
			StringBuffer footer = new StringBuffer();
			if (Config.TRACE_COMPILE_PLAN_PARTITIONING
					&& this.hasInputPartitioning()) {
				footer.append("Input partitioning: ");
				footer.append(this.getInputPartitioning().toString());
			}
			if (node.getInfo().getFooter() != null) {
				footer.append(AbstractToken.NEWLINE);
				footer.append(node.getInfo().getFooter());
			}
			node.getInfo().setFooter(footer.toString());
		}

		return err;
	}

	@Override
	public boolean renameAttributes(HashMap<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		boolean renamed = false;

		// rename input partitioning
		String newName = null;
		if (this.hasInputPartitioning()) {
			for (TokenAttribute tA : this.getInputPartitioning()
					.getPartitionAttributes()) {
				newName = renamedAttributes.get(tA.getName().getName());
				if (newName == null)
					continue;
				renamed = true;
				tA.setName(newName);
			}
		}

		return super.renameAttributes(renamedAttributes, renamedOps) || renamed;
	}

	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		TokenAttribute.renameTable(selAtts, this.getChild().getOperatorId()
				.toString());
	}
}