package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

public class Rename extends AbstractUnaryOperator {

	private static final long serialVersionUID = -2350193145633977329L;
	
	private final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <RESULTS> FROM <<OP1>> AS <OP1>");
	
	public Rename(AbstractOperator child, ResultDesc result) {
		super(child);
		this.addResult(result);
		this.type = EnumOperator.RENAME;
	}

	@Override
	public String toSqlString() {
		HashMap<String, String> vars = new HashMap<String, String>();
		final String opDummy = getOperatorId().toString()+"_TABLE";
		vars.put("OP1", opDummy);
		
		//get all table attributes and match them in order
		final Vector<String> attrs = new Vector<String>();
		for(TokenAttribute attr : this.results.get(0).getAttributes()) {
			attrs.add(attr.getName().toString());
		}
		vars.put("RESULTS", SetUtils.buildAliasString(opDummy, attrs, getResultAttributes()));
		return sqlTemplate.toString(vars);
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		//nothing to do
	}

	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		TokenAttribute.renameTable(selAtts, this.getChild().getOperatorId().toString());
	}
}
