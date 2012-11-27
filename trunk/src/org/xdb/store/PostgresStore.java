package org.xdb.store;

import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.store.JDBCStore;

public class PostgresStore extends JDBCStore {
	private static final String DRIVER_CLASS = "org.postgresql.Driver";

	public PostgresStore() {
		super(DRIVER_CLASS);
	}

	@Override
	public boolean supportsOperator(EnumOperator operator) {
		switch (operator) {
		case GENERIC_AGGREGATION:
		case GENERIC_SELECTION:
		case GENERIC_PROJECTION:
		case EQUI_JOIN:
			return true;
		default:
			return false;
		}
	}

	@Override
	public String generateQuery(AbstractCompileOperator operator) {
		return null;
	}

}
