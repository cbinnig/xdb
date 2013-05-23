package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

public class Rename extends AbstractUnaryOperator {

	private static final long serialVersionUID = -2350193145633977329L;

	private final StringTemplate sqlTemplate = new StringTemplate(
			"SELECT <RESULTS> FROM (<<OP1>>) AS <OP1>");

	// constructor
	public Rename(AbstractCompileOperator child) {
		super(child);
		this.type = EnumOperator.RENAME;
	}

	// copy-constructor
	public Rename(Rename toCopy) {
		super(toCopy);
	}

	@Override
	public String toSqlString() {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("OP1", this.getChild().getOperatorId().toString());

		// get all table attributes and match them in order
		final Vector<String> attrs = new Vector<String>();
		for (TokenAttribute attr : this.getChild().getResult().getAttributes()) {
			attrs.add(attr.getName().toString());
		}
		vars.put("RESULTS",
				SetUtils.buildAliasString(attrs, getResultAttributes()));
		return sqlTemplate.toString(vars);
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		// Nothing to do
	}

	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		HashMap<TokenIdentifier, TokenIdentifier> renameMap = new HashMap<TokenIdentifier, TokenIdentifier>();
		for (int i = 0; i < this.getResult().size(); ++i) {
			renameMap.put(this.getResult().getAttribute(i).getName(), this
					.getChild().getResult().getAttribute(i).getName());
		}
		TokenAttribute.rename(selAtts, this.getChild().getOperatorId()
				.toString(), renameMap);
	}

	@Override
	public Rename clone() throws CloneNotSupportedException {

		return (Rename) super.clone();
	}
}
