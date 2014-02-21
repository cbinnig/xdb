package org.xdb.elasticpartitioning.database;

public class DatabaseNotInitializedException extends Exception {
	private static final long serialVersionUID = 4091973014845582082L;

	public DatabaseNotInitializedException() {
		super("Please first call 'initialize' method, and then get an instance");
	}

}
