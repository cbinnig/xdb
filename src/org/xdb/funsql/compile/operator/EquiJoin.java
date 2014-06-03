package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class EquiJoin extends AbstractBinaryOperator {
	private static final long serialVersionUID = -7557353401586940253L;

	private TokenAttribute leftTokenAttribute;
	private TokenAttribute rightTokenAttribute;

	// constructors
	public EquiJoin(AbstractCompileOperator leftChild,
			AbstractCompileOperator rightChild,
			TokenAttribute leftTokenAttribute,
			TokenAttribute rightTokenAttribute) {
		super(leftChild, rightChild);

		this.leftTokenAttribute = leftTokenAttribute;
		this.rightTokenAttribute = rightTokenAttribute;
		this.type = EnumOperator.EQUI_JOIN;
	}
    
	public EquiJoin(){
	}
	// copy-constructor
	public EquiJoin(EquiJoin ej) {
		super(ej);
		this.leftTokenAttribute = ej.leftTokenAttribute.clone();
		this.rightTokenAttribute = ej.rightTokenAttribute.clone();
		this.type = EnumOperator.EQUI_JOIN;
	}

	// getters and setters
	public TokenAttribute getLeftTokenAttribute() {
		return leftTokenAttribute;
	}

	public void setLeftTokenAttribute(TokenAttribute leftTokenAttribute) {
		this.leftTokenAttribute = leftTokenAttribute;
	}

	public TokenAttribute getRightTokenAttribute() {
		return rightTokenAttribute;
	}

	public void setRightTokenAttribute(TokenAttribute rightTokenAttribute) {
		this.rightTokenAttribute = rightTokenAttribute;
	}

	@Override
	public String toSqlString() {
		final HashMap<String, String> vars = new HashMap<String, String>();

		final List<String> lAttributes = getLeftChild()
				.resultAttributesWOTableToSQL();
		final List<String> rAttributes = getRightChild()
				.resultAttributesWOTableToSQL();
		final List<String> aliasVec = resultAttributesToSQL();

		final List<String> lAliases = aliasVec.subList(0, lAttributes.size());
		final List<String> rAliases = aliasVec.subList(lAttributes.size(),
				aliasVec.size());

		vars.put("RESULT", SetUtils.buildAliasString(lAttributes, lAliases)
				+ "," + SetUtils.buildAliasString(rAttributes, rAliases));

		HashMap<String, String> joinParams = new HashMap<String, String>();
		joinParams.put(getLeftChild().getOperatorId().toString(),
				getLeftTokenAttribute().toString());
		joinParams.put(getRightChild().getOperatorId().toString(),
				getRightTokenAttribute().toString());

		String templateString = "";
		vars.put("RESULT", SetUtils.buildAliasString(lAttributes, lAliases)
				+ "," + SetUtils.buildAliasString(rAttributes, rAliases));

		int idx = 1;
		for (AbstractCompileOperator child : this.getChildren()) {
			vars.put("OP" + idx, child.getOperatorId().toString());
			vars.put("JATT" + idx,
					joinParams.get(child.getOperatorId().toString()));
			if (idx > 1) {
				if (child.getType().equals(EnumOperator.TABLE)) {
					templateString = templateString + " INNER JOIN <<OP"
							+ (idx) + ">> AS <OP" + (idx) + "> ON <JATT"
							+ (idx - 1) + "> = <JATT" + (idx) + ">";
				} else {
					templateString = templateString + " INNER JOIN (<<OP"
							+ (idx) + ">>) AS <OP" + (idx) + "> ON <JATT"
							+ (idx - 1) + "> = <JATT" + (idx) + ">";
				}

			} else {
				if (child.getType().equals(EnumOperator.TABLE)) {
					templateString = templateString
							+ "SELECT <RESULT> FROM <<OP1>> AS <OP1>";
				} else {
					templateString = templateString
							+ "SELECT <RESULT> FROM (<<OP1>>) AS <OP1>";
				}
			}
			idx++;
		}

		StringTemplate sqlTemplate = new StringTemplate(templateString);

		return sqlTemplate.toString(vars);
	}

	@Override
	public Error traceOperator(Graph g, Map<Identifier, GraphNode> nodes) {
		Error err = super.traceOperator(g, nodes);
		if (err.isError())
			return err;

		GraphNode node = nodes.get(this.operatorId);
		if (Config.TRACE_COMPILE_PLAN_FOOTER) {
			StringBuffer footer = new StringBuffer();
			footer.append(this.leftTokenAttribute.toString());
			footer.append(AbstractToken.EQUAL1);
			footer.append(this.rightTokenAttribute.toString());
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
		Vector<TokenAttribute> atts = new Vector<TokenAttribute>(2);
		atts.add(this.leftTokenAttribute);
		atts.add(this.rightTokenAttribute);
		TokenAttribute.renameTable(atts, oldId, newId);
	}

	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts,
			int childIdx) {
		TokenAttribute.renameTable(selAtts, this.getChild(childIdx)
				.getOperatorId().toString());
	}

	@Override
	public boolean renameAttributes(Map<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		String newName;
		// rename join tokens
		if (renamedOps.contains(getLeftTokenAttribute().getTable().getName())) {
			newName = renamedAttributes.get(getLeftTokenAttribute().getName());
			getLeftTokenAttribute().setName(newName);
		}

		if (renamedOps.contains(getRightTokenAttribute().getTable().getName())) {
			newName = renamedAttributes.get(getRightTokenAttribute().getName());
			getRightTokenAttribute().setName(newName);
		}
		return super.renameAttributes(renamedAttributes, renamedOps);
	}

}
