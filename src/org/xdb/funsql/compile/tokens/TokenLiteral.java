package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.types.EnumLiteralType;
import org.xdb.funsql.types.EnumSimpleType;

public abstract class TokenLiteral extends AbstractTokenOperand{

	private static final long serialVersionUID = 6851539756854439958L;
	
	protected EnumLiteralType type;
	
	public TokenLiteral() {
		super(EnumOperandType.LITERAL);
	}

	public EnumLiteralType getLiteralType() {
		return type;
	}
	
	public EnumSimpleType getDataType() {
		return this.type.getSimpleType();
	}
	
	@Override
	public boolean isAttribute() {
		return false;
	}
	
	@Override
	public boolean isLiteral() {
		return true;
	}
}
