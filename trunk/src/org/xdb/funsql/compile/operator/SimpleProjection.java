package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.StringTemplate;

public class SimpleProjection extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;
	
	private Vector<TokenAttribute> attributes;
	final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <ATTR_LIST> FROM `<<OP1>>` AS `<OP1>`");
	
	//constructors
	public SimpleProjection(AbstractOperator child, int size) {
		super(child);
		
		attributes = new Vector<TokenAttribute>(size);
		type = EnumOperator.SIMPLE_PROJECTION;
	}
	
	//getters and setters
	public void setAttribute(int i, TokenAttribute attribute){
		attributes.set(i, attribute);
	}
	
	public TokenAttribute getAttribtue(int i){
		return attributes.get(i);
	}

	public Vector<TokenAttribute> getAttributes() {
		return attributes;
	}
	
	@Override
	public String toSqlString() {
		
		final StringBuffer attrBuf = new StringBuffer();
		for(TokenAttribute attr : attributes) {
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
}
