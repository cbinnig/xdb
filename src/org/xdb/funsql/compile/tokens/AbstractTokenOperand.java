package org.xdb.funsql.compile.tokens;


public abstract class AbstractTokenOperand extends AbstractToken implements Cloneable{

	private static final long serialVersionUID = 2264534201670998109L;
	
	private EnumOperandType type;

	public AbstractTokenOperand(EnumOperandType type) {
		super();
		this.type = type;
	}

	public EnumOperandType getType() {
		return type;
	}

	public void setType(EnumOperandType type) {
		this.type = type;
	}
	
	public abstract boolean isAttribute();
	
	public abstract AbstractTokenOperand clone();
}
