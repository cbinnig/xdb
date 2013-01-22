package org.xdb.funsql.compile.operator;

import java.io.Serializable;

public enum EnumOperator implements Serializable {
	EQUI_JOIN,
	GENERIC_SELECTION,
	GENERIC_AGGREGATION,
	GENERIC_PROJECTION,
	TABLE,
	FUNCTION_CALL,
	RENAME,
	SQL_UNARY,
	SQL_JOIN
}