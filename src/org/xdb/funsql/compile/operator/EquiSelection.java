package org.xdb.funsql.compile.operator;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenLiteral;

public class EquiSelection extends AbstractUnaryOperator {
	private static final long serialVersionUID = 5178586492851005421L;
	
	private TokenAttribute attribtue;
	private TokenLiteral value;
	
	//constructors
	public EquiSelection(AbstractOperator child) {
		super(child);
		this.type = EnumOperator.EQUI_SELECTION;
	}

	//getters and setters
	public TokenAttribute getAttribtue() {
		return attribtue;
	}

	public void setAttribtue(TokenAttribute attribtue) {
		this.attribtue = attribtue;
	}

	public TokenLiteral getValue() {
		return value;
	}

	public void setValue(TokenLiteral value) {
		this.value = value;
	}
	
	@Override
	public String toSqlString() {
		// TODO: generate sql
		return null;
	}
}
