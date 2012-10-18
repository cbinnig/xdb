package org.xdb.funsql.compile.operator;

import java.io.Serializable;

public enum EnumOperator implements Serializable {
	EQUI_JOIN,
	GENERIC_SELECTION,
	SIMPLE_AGGREGATION,
	GENERIC_PROJECTION,
	TABLE
}