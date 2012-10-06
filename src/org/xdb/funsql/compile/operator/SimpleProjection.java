package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class SimpleProjection extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;
	
	private Vector<SimpleExpression> expressions;
	final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <ATTR_LIST> FROM `<<OP1>>` AS `<OP1>`");
	
	//constructors
	public SimpleProjection(AbstractOperator child, int size) {
		super(child);
		
		expressions = new Vector<SimpleExpression>(size);
		type = EnumOperator.SIMPLE_PROJECTION;
	}
	
	//getters and setters
	public void setExpression(int i, SimpleExpression expression){
		expressions.add(i, expression);
	}
	
	public SimpleExpression getExpression(int i){
		return expressions.get(i);
	}

	public Vector<SimpleExpression> getExpressions() {
		return expressions;
	}
	
	@Override
	public String toSqlString() {
		
		final StringBuffer attrBuf = new StringBuffer();
		for(SimpleExpression attr : expressions) {
			if(attrBuf.length() != 0)
				attrBuf.append(", ");
			attrBuf.append("`<OP1>`.`"+attr.toSqlString());
		}
		return sqlTemplate.toString(new HashMap<String, String>() {{
			put("ATTR_LIST", attrBuf.toString());
			put("OP1", getChild().getOperatorId().toString());
		}});
	}

	@Override
	void accept(TreeVisitor v) {
		v.visitSimpleProjection(this);
	}
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = super.traceGraph(g, nodes);
		if(err.isError())
			return err;
		
		GraphNode node = nodes.get(this.operatorId);
		node.getInfo().setFooter(this.expressions.toString());
		return err;
	}
}
