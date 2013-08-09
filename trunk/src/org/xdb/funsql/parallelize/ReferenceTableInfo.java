package org.xdb.funsql.parallelize;

import java.io.Serializable;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenTable;

/**
 * This class encapsulates the information needed for "source table" in 
 * reference partitioning
 * @author Erfan Zamanian
 *
 */
public class ReferenceTableInfo implements Serializable {
	
	private static final long serialVersionUID = -2080381215559335315L;
	private TokenTable referenceTable;
	private TokenAttribute  referenceAttribute;
	
	public ReferenceTableInfo(TokenTable referenceTable, TokenAttribute referenceAttribute) {
		this.referenceTable = referenceTable;
		this.referenceAttribute = referenceAttribute;
	}

}
