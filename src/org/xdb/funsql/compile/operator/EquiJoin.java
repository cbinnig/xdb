package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class EquiJoin extends AbstractBinaryOperator {
	private static final long serialVersionUID = -7557353401586940253L;

	private TokenAttribute leftTokenAttribute;
	private TokenAttribute rightTokenAttribute;
	
	private final StringTemplate sqlTemplate = new StringTemplate(
			"SELECT <RESULT> FROM <<OP1>> AS `<OP1>` INNER JOIN `<<OP2>>` AS `<OP2>`" +
			" ON `<LOP1>` = `<LOP2>`");

	//constructors
	public EquiJoin(AbstractOperator leftChild, AbstractOperator rightChild,
			TokenAttribute leftTokenAttribute, TokenAttribute rightTokenAttribute) {
		super(leftChild, rightChild);
		
		this.leftTokenAttribute = leftTokenAttribute;
		this.rightTokenAttribute = rightTokenAttribute;
		this.type = EnumOperator.EQUI_JOIN;
	}

	//getters and setters
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
		
		final List<String> lAttributes = getLeftChild().getResultTableAttributes();
		final List<String> rAttributes = getRightChild().getResultTableAttributes();
		final List<String> aliasVec = getResultAttributes();
		
		final List<String> lAliases = aliasVec.subList(0, lAttributes.size());
		final List<String> rAliases = aliasVec.subList(lAttributes.size(), aliasVec.size());
		
		vars.put("RESULT", SetUtils.buildAliasString(lAttributes, lAliases)+","+SetUtils.buildAliasString(rAttributes, rAliases));
		
		vars.put("OP1", getLeftChild().getOperatorId().toString());
		vars.put("OP2", getRightChild().getOperatorId().toString());
		
		vars.put("LOP1", getLeftTokenAttribute().toSqlString());
		vars.put("LOP2", getRightTokenAttribute().toSqlString());
		return sqlTemplate.toString(vars);
	}

	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = super.traceGraph(g, nodes);
		if(err.isError())
			return err;
		
		GraphNode node = nodes.get(this.operatorId);
		node.getInfo().setFooter(this.leftTokenAttribute.toString()+"="+this.rightTokenAttribute.toString());
		return err;
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		Vector<TokenAttribute> atts = new Vector<TokenAttribute>(2);
		atts.add(this.leftTokenAttribute);
		atts.add(this.rightTokenAttribute);
		TokenAttribute.renameTable(atts, oldId, newId);
	}
	
	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts, int childIdx) {
		TokenAttribute.renameTable(selAtts, this.getChild(childIdx).getOperatorId().toString());
	}
}
