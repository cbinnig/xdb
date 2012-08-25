package org.xdb.store.postgres;

import org.xdb.funsql.compile.operator.AbstractOperator;
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
		case EQUI_SELECTION:
		case SIMPLE_PROJECTION:
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
