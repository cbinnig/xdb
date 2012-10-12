package org.xdb.utils;

import java.util.Vector;

import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * Container class for helper functions operating on data sets.
 *
 */
public class SetUtils {

	/**
	 * Generate String containing all Attributes ready to use in MySQL-Select statements.
	 * @return String with output of TokenAttribute.toSqlString(), seperated by commata.
	 */
	public static String stringifyAttrVec(final Vector<TokenAttribute> attrVec) {
		final StringBuffer attributeListBuffer = new StringBuffer();
		
		for(int i = 0; i < attrVec.size(); i++) {
			if(i != 0)
				attributeListBuffer.append(", ");
			attributeListBuffer.append(attrVec.get(i).toSqlString());
		}
		
		return attributeListBuffer.toString();
	}
	
}
