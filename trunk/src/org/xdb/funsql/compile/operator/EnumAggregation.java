package org.xdb.funsql.compile.operator;

import java.io.Serializable;

import org.xdb.funsql.types.EnumLiteralType;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.utils.StringTemplate;

public enum EnumAggregation implements Serializable{
	AVG(new StringTemplate("AVG(<EXP>)")), 
	CNT(new StringTemplate("COUNT(<EXP>)")),
	CNT_DISTINCT(new StringTemplate("COUNT(DISTINCT <EXP>)")),
	MIN(new StringTemplate("MIN(<EXP>)")),
	MAX(new StringTemplate("MAX(<EXP>)")),
	SUM(new StringTemplate("SUM(<EXP>)"));
	
	/*
	 * string representation template - must contain <EXP>
	 * at position of aggregating expression
	 */
	private final StringTemplate repr;
	private EnumSimpleType type;

	private EnumAggregation(final StringTemplate repr) {
		this.repr = repr;
	}	
	
	public StringTemplate getSqlRepresentation() {
		return repr;
	}
	
	public EnumSimpleType getType() {
		return type;
	}
}
