package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class GenericAggregation extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;
	
	private Vector<AbstractExpression> groupExprs;
	private Vector<AbstractExpression> aggExprs;
	private Vector<TokenIdentifier> aliases;
	
	private final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <RESULT> FROM <<OP1>> AS <OP1>"+
					" GROUP BY <GROUP_ATTRS>");
	
	//constructors
	public GenericAggregation(AbstractCompileOperator child) {
		super(child);
		
		this.groupExprs = new Vector<AbstractExpression>();
		this.aggExprs = new Vector<AbstractExpression>();
		this.aliases = new Vector<TokenIdentifier>();
		
		this.type = EnumOperator.GENERIC_AGGREGATION;
	}
	
	//getters and setters
	public void addAlias(TokenIdentifier alias) {
		aliases.add(alias);
	}

	public TokenIdentifier getAlias(int i) {
		return aliases.get(i);
	}

	public Vector<TokenIdentifier> getAliases() {
		return aliases;
	}
	
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
		return this.aggExprs.get(i);
	}

	public Vector<AbstractExpression> getAggregationExpressions() {
		return this.aggExprs;
	}
	
	@Override
	public String toSqlString() {
		final HashMap<String, String>  vars = new HashMap<String, String>();
		vars.put("OP1", getChild().getOperatorId().toString());
		vars.put("AGG_ATTRS", SetUtils.stringifyExprVec(aggExprs));
		vars.put("GROUP_ATTRS", SetUtils.stringifyExprVec(groupExprs));
		
		final List<String> aliasVec = getResultAttributes();
		final List<String> aggrAliases = aliasVec.subList(0, aggExprs.size());
		final List<String> grpAliases = aliasVec.subList(aggExprs.size(), aliasVec.size());
		
		final Vector<String> aggrExprVec = new Vector<String>(aggExprs.size());
		for(AbstractExpression exp : aggExprs) {
			aggrExprVec.add(exp.toSqlString());
		}
		final Vector<String> groupExprVec = new Vector<String>(groupExprs.size());
		for(AbstractExpression exp : groupExprs) {
			groupExprVec.add(exp.toSqlString());
		}
		
		vars.put("RESULT", SetUtils.buildAliasString(aggrExprVec, aggrAliases)+","+SetUtils.buildAliasString(groupExprVec, grpAliases));
		
		return sqlTemplate.toString(vars);
	}

	@Override
	public Error traceOperator(Graph g, HashMap<Identifier, GraphNode> nodes) {
		Error err = super.traceOperator(g, nodes);
		if (err.isError())
			return err;

		GraphNode node = nodes.get(this.operatorId);
		StringBuffer footer = new StringBuffer();
		footer.append("Aggregation: ");
		footer.append(this.aggExprs.toString());
		footer.append(AbstractToken.NEWLINE);
		footer.append("Grouping: ");
		footer.append(this.groupExprs.toString());
		footer.append(AbstractToken.NEWLINE);
		footer.append("Aliases: ");
		footer.append(this.aliases.toString());
		node.getInfo().setFooter(footer.toString());
		return err;
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		Vector<TokenAttribute> atts = new Vector<TokenAttribute>();
		for(AbstractExpression expr: this.aggExprs){
			atts.addAll(expr.getAttributes());
		}
		for(AbstractExpression expr: this.groupExprs){
			atts.addAll(expr.getAttributes());
		}
		TokenAttribute.renameTable(atts, oldId, newId);
	}
	
	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		HashMap<TokenIdentifier,TokenIdentifier> renameMap = new HashMap<TokenIdentifier,TokenIdentifier>();
		for(int i=0; i<this.groupExprs.size(); ++i){
			AbstractExpression expr = this.groupExprs.get(i);
			if(expr.isAttribute()){
				renameMap.put(expr.getAttribute().getName(), this.aliases.get(this.aggExprs.size()+i));
			}
		}
		TokenAttribute.rename(selAtts, this.getChild().getOperatorId().toString(), renameMap);
	}
}
