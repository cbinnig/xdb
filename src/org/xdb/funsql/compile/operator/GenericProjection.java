package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.ITreeVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class GenericProjection extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;

	private Vector<AbstractExpression> expressions;
	private Vector<TokenIdentifier> aliases;

	final StringTemplate sqlTemplate = new StringTemplate(
			"SELECT <RESULTS> FROM `<<OP1>>` AS `<OP1>`");

	// constructors
	public GenericProjection(AbstractOperator child) {
		super(child);

		expressions = new Vector<AbstractExpression>();
		aliases = new Vector<TokenIdentifier>();
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
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("RESULTS", getResultAttributeString());
		vars.put("OP1", getChild().getOperatorId().toString());
		return sqlTemplate.toString(vars);
	}

	@Override
	public void accept(ITreeVisitor v) {
		v.visitGenericProjection(this);
	}

	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes) {
		Error err = super.traceGraph(g, nodes);
		if (err.isError())
			return err;

		GraphNode node = nodes.get(this.operatorId);
		StringBuffer footer = new StringBuffer();
		footer.append("Expressions: ");
		footer.append(this.expressions.toString());
		footer.append(AbstractToken.NEWLINE);
		footer.append("Aliases: ");
		footer.append(this.aliases.toString());
		node.getInfo().setFooter(footer.toString());
		return err;
	}

	@Override
	public boolean isPushDownAllowed(EnumPushDown pd) {
		// TODO Auto-generated method stub
		return false;
	}
}
