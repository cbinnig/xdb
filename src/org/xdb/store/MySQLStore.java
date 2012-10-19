package org.xdb.store;

import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.store.JDBCStore;

public class MySQLStore extends JDBCStore {
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	public MySQLStore() {
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
	public String generateQuery(AbstractOperator operator) {
		return null;
	}

}
