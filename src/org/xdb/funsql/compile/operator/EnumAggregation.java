package org.xdb.funsql.compile.operator;

import java.io.Serializable;

public enum EnumAggregation implements Serializable{
	AVG, 
	CNT,
	CNT_DISTINCT,
	MIN,
	MAX, 
	SUM
}
