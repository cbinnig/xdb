package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class GenericProjection extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;

	private Vector<AbstractExpression> expressions;
	private Vector<TokenIdentifier> aliases;

	final StringTemplate sqlTemplate = new StringTemplate(
			"SELECT <RESULTS> FROM <<OP1>> AS <OP1>");

	// constructors
	public GenericProjection(AbstractCompileOperator child) {
		super(child);

		expressions = new Vector<AbstractExpression>();
		aliases = new Vector<TokenIdentifier>();
		type = EnumOperator.GENERIC_PROJECTION;
	}

	// copy-constructor
	public GenericProjection(GenericProjection toCopy) {
		super(toCopy);

		Vector<AbstractExpression> aev = new Vector<AbstractExpression>();
		for (AbstractExpression ta : toCopy.expressions) {
			aev.add(ta.clone());
		}
		this.expressions = aev;

		Vector<TokenIdentifier> alias = new Vector<TokenIdentifier>();

		for (TokenIdentifier ti : toCopy.aliases) {
			alias.add(ti);
		}
		this.aliases = alias;

		type = EnumOperator.GENERIC_PROJECTION;
	}

	// getters and setters
	public void addExpression(AbstractExpression expression) {
		expressions.add(expression);
	}

	public AbstractExpression getExpression(int i) {
		return expressions.get(i);
	}

	public Vector<AbstractExpression> getExpressions() {
		return expressions;
	}

	public void addAlias(TokenIdentifier alias) {
		aliases.add(alias);
	}

	public TokenIdentifier getAlias(int i) {
		return aliases.get(i);
	}

	public Vector<TokenIdentifier> getAliases() {
		return aliases;
	}

	// methods
	@Override
	public String toSqlString() {
		final HashMap<String, String> vars = new HashMap<String, String>();
		final Vector<String> expressionVec = new Vector<String>(
				expressions.size());
		for (AbstractExpression exp : expressions) {
			expressionVec.add(exp.toSqlString());
		}

		vars.put("RESULTS",
				SetUtils.buildAliasString(expressionVec, getResultAttributes()));
		vars.put("OP1", getChild().getOperatorId().toString());
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
			footer.append("Expressions: ");
			footer.append(this.expressions.toString());
			footer.append(AbstractToken.NEWLINE);
			footer.append("Aliases: ");
			footer.append(this.aliases.toString());
			if (node.getInfo().getFooter() != null) {
				footer.append(AbstractToken.NEWLINE);
				footer.append(node.getInfo().getFooter());
			}
			node.getInfo().setFooter(footer.toString());
		}
		return err;
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		Vector<TokenAttribute> atts = new Vector<TokenAttribute>();
		for (AbstractExpression expr : this.expressions) {
			atts.addAll(expr.getAttributes());
		}
		TokenAttribute.renameTable(atts, oldId, newId);
	}

	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		HashMap<TokenIdentifier, TokenIdentifier> renameMap = new HashMap<TokenIdentifier, TokenIdentifier>();
		for (int i = 0; i < this.expressions.size(); ++i) {
			AbstractExpression expr = this.expressions.get(i);
			if (expr.isAttribute()) {
				renameMap.put(this.aliases.get(i), expr.getAttribute()
						.getName());
			}
		}
		TokenAttribute.rename(selAtts, this.getChild().getOperatorId()
				.toString(), renameMap);
	}

	@Override
	public GenericProjection clone() throws CloneNotSupportedException {

		GenericProjection gp = (GenericProjection) super.clone();

		Vector<AbstractExpression> aev = new Vector<AbstractExpression>();
		for (AbstractExpression ta : this.expressions) {
			aev.add(ta.clone());
		}
		gp.expressions = aev;

		Vector<TokenIdentifier> alias = new Vector<TokenIdentifier>();

		for (TokenIdentifier ti : this.aliases) {
			alias.add(ti);
		}
		gp.aliases = alias;
		return gp;
	}
}
