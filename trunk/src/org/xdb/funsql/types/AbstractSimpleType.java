package org.xdb.funsql.types;

public abstract class AbstractSimpleType extends AbstractType {

	private static final long serialVersionUID = 2275148107330947006L;
	private EnumSimpleType type;
	
	public AbstractSimpleType(EnumSimpleType type){
		this.type = type;
	}

	public EnumSimpleType getType() {
		return type;
	}

	public void setType(EnumSimpleType type) {
		this.type = type;
	}
	
	@Override
	public abstract AbstractSimpleType clone();
}
