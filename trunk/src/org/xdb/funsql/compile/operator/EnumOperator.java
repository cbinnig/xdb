package org.xdb.funsql.compile.operator;

import java.io.Serializable;

public enum EnumOperator implements Serializable {
	EQUI_JOIN,
	EQUI_SELECTION,
	GENERIC_SELECTION,
	SIMPLE_AGGREGATION,
	SIMPLE_PROJECTION,
	TABLE
}