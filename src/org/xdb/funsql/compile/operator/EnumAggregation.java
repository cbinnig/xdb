package org.xdb.funsql.compile.operator;

import java.io.Serializable;

import org.xdb.utils.StringTemplate;

public enum EnumAggregation implements Serializable{
	NO_AGG(new StringTemplate("(<EXP>)")), 
	AVG(new StringTemplate("AVG(<EXP>)")), 
	CNT(new StringTemplate("COUNT(<EXP>)")),
	MIN(new StringTemplate("MIN(<EXP>)")),
	MAX(new StringTemplate("MAX(<EXP>)")),
	SUM(new StringTemplate("SUM(<EXP>)"));
	
	/*
	 * string representation template - must contain <EXP>
	 * at position of aggregating expression
	 */
	private final StringTemplate repr;

	private EnumAggregation(final StringTemplate repr) {
		this.repr = repr;
	}	
	
	public StringTemplate getSqlRepresentation() {
		return repr;
	}
	
	public static EnumAggregation valueOfSql(String value){
		if(value.equals("AVG"))
			return AVG;
		else if(value.equals("COUNT"))
			return CNT;
		else if(value.equals("MIN"))
			return MIN;
		else if(value.equals("MAX"))
			return MAX;
		else if(value.equals("SUM"))
			return SUM;
		
		return null;	
	}
}
