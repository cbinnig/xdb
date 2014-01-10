package org.xdb.funsql.compile.tokens;

public class TokenStar extends AbstractTokenOperand {

	public TokenStar() {
		super(EnumOperandType.STAR);
	}

	private static final long serialVersionUID = -2686289284467631593L;

	@Override
	public String toSqlString() {
		return "*";
	}

	@Override
	public boolean isAttribute() {
		return false;
	}

	@Override
	public AbstractTokenOperand clone() {
		return this;
	}

}
