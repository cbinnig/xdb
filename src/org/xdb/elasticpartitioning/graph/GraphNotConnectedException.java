package org.xdb.elasticpartitioning.graph;

public class GraphNotConnectedException extends Exception {
	private static final long serialVersionUID = -2079240038204424823L;

	public GraphNotConnectedException(String explanation) {
		super(explanation);
	}

}
