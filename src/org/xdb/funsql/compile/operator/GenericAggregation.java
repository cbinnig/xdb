package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.ITreeVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class GenericAggregation extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;
	
	private Vector<AbstractExpression> groupExprs;
	private Vector<AbstractExpression> aggExprs;
	
	private final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <AGG_ATTRS>,<GROUP_ATTRS> FROM <<OP1>> AS <OP1>"+
					"GROUP BY <GROUP_ATTRS>");
	
	//constructors
	public GenericAggregation(AbstractOperator child) {
		super(child);
		
		this.groupExprs = new Vector<AbstractExpression>();
		this.aggExprs = new Vector<AbstractExpression>();
		
		this.type = EnumOperator.GENERIC_AGGREGATION;
	}
	
	//getters and setters
	public void addGroupExpression(AbstractExpression expr){
		this.groupExprs.add(expr);
	}
	
	public AbstractExpression getGroupExpression(int i){
		return this.groupExprs.get(i);
	}

	public Vector<AbstractExpression> getGroupExpressions() {
		return groupExprs;
	}
	
	public void addAggregationExpression(AbstractExpression expr){
		this.aggExprs.add(expr);
	}
	
	public AbstractExpression getAggregationExpression(int i){
		return this.groupExprs.get(i);
	}

	public Vector<AbstractExpression> getAggregationExpressions() {
		return this.aggExprs;
	}
	
	@Override
	public String toSqlString() {
		HashMap<String, String>  vars = new HashMap<String, String>();
		vars.put("OP1", getChild().getOperatorId().toString());
		vars.put("AGG_ATTRS", SetUtils.stringifyExprVec(aggExprs));
		vars.put("GROUP_ATTRS", SetUtils.stringifyExprVec(groupExprs));
		
		return sqlTemplate.toString(vars);
	}

	@Override
	public boolean isPushDownAllowed(EnumPushDown pd) {
		switch(pd){
		case SELECTION_PUSHDOWN:
		case STOP_PUSHDOWN:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes) {
		Error err = super.traceGraph(g, nodes);
		if (err.isError())
			return err;

		GraphNode node = nodes.get(this.operatorId);
		StringBuffer footer = new StringBuffer();
		footer.append(this.aggExprs.toString());
		footer.append(AbstractToken.BLANK);
		footer.append(this.groupExprs.toString());
		node.getInfo().setFooter(footer.toString());
		return err;
	}

	@Override
	public void accept(ITreeVisitor v) {
		v.visitGenericAggregation(this);
	}
}
