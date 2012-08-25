package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.types.EnumLiteralType;

public abstract class TokenLiteral extends AbstractToken{
	protected EnumLiteralType type;
	
	public EnumLiteralType getType() {
		return type;
	}
}
