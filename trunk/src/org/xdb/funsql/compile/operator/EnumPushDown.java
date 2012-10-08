package org.xdb.funsql.compile.operator;

public enum EnumPushDown {
	NO_PUSHDOWN,
	STOP_PUSHDOWN,
	SELECTION_PUSHDOWN,
	PROJECTION_PUSHDOWN
}
