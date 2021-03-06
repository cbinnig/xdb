package org.xdb.utils;

import java.util.List;
import java.util.Vector;

import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * Container class for helper functions operating on data sets.
 *
 */
public class SetUtils {

	public static String buildAliasString(final List<String> attrNameList,
			final List<String> aliasList) {
		assert(attrNameList.size() == aliasList.size());
		
		final StringBuffer attributeListBuffer = new StringBuffer();
		
		for(int i = 0; i < attrNameList.size(); i++) {
			if(i != 0)
				attributeListBuffer.append(", ");
			attributeListBuffer.append(attrNameList.get(i) + " AS " + aliasList.get(i));
		}
		
		return attributeListBuffer.toString();
	}

	
	public static String buildString(final List<String> attrList) {
		final StringBuffer attributeListBuffer = new StringBuffer();
		
		for(int i = 0; i < attrList.size(); i++) {
			if(i != 0)
				attributeListBuffer.append(", ");
			attributeListBuffer.append(attrList.get(i));
		}
		
		return attributeListBuffer.toString();
	}
	
	public static Vector<String> attributesToSQLString(final Vector<TokenAttribute> attrs) {
		final Vector<String> stringAttrs = new Vector<String>(attrs.size());
		for(TokenAttribute attr : attrs) {
			stringAttrs.add(attr.getName().toSqlString());
		}
		
		return stringAttrs;
	}
	
	public static Vector<String> attributesWithTableToSQLString(final Vector<TokenAttribute> attrs) {
		final Vector<String> stringAttrs = new Vector<String>(attrs.size());
		for(TokenAttribute attr : attrs) {
			stringAttrs.add(attr.toSqlString());
		}
		
		return stringAttrs;
	}

	
	/**
	 * Generate String containing all Attributes ready to use in MySQL-Select statements.
	 * @return String with output of TokenAttribute.toSqlString(), separated by comma.
	 */
	public static String stringifyExprVec(Vector<AbstractExpression> exprs) {
		final StringBuffer attributeListBuffer = new StringBuffer();
		
		for(int i = 0; i < exprs.size(); i++) {
			if(i != 0){
				attributeListBuffer.append(AbstractToken.COMMA);
				attributeListBuffer.append(AbstractToken.BLANK);
			}
			attributeListBuffer.append(exprs.get(i).toSqlString());
		}
		
		return attributeListBuffer.toString();
	}
}
