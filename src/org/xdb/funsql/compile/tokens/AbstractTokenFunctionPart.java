package org.xdb.funsql.compile.tokens;

/**
 * @author Larissa Schmidt
 *
 */
public abstract class AbstractTokenFunctionPart extends AbstractToken {
	private static final long serialVersionUID = -4055343509672759524L;
	protected EnumFunctionPartType type;

	public AbstractTokenFunctionPart(EnumFunctionPartType type) {
		super();
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see org.xdb.funsql.compile.tokens.AbstractToken#toSqlString()
	 */
	public abstract String toSqlString();
	
	
	//getter and setter	
	public EnumFunctionPartType getType() {
		return type;
	}

	public void setType(EnumFunctionPartType type) {
		this.type = type;
	}

}
