package org.xdb.funsql.compile.operator;

import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.ITreeVisitor;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class EquiJoin extends AbstractBinaryOperator {
	private static final long serialVersionUID = -7557353401586940253L;

	private TokenAttribute leftTokenAttribute;
	private TokenAttribute rightTokenAttribute;
	
	private final StringTemplate sqlTemplate = new StringTemplate(
			"SELECT <RESULTS> FROM <<OP1>> AS `<OP1>` INNER JOIN `<<OP2>>` AS `<OP2>`" +
			"ON `<LOP1>` = `<LOP2>`");

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
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("RESULTS", getResultAttributeString());
		
		vars.put("OP1", getLeftChild().getOperatorId().toString());
		vars.put("OP2", getRightChild().getOperatorId().toString());
		
		vars.put("LOP1", getLeftTokenAttribute().toSqlString());
		vars.put("LOP2", getRightTokenAttribute().toSqlString());
		return sqlTemplate.toString(vars);
	}

	/**
	 * @param kind of pushdown you want to do
	 * @return true if pushdown possible 
	 */
	@Override
	public boolean isPushDownAllowed(EnumPushDown pd) {
		switch(pd){
		case STOP_PUSHDOWN:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void accept(ITreeVisitor v) {
		v.visitEquiJoin(this);
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
}