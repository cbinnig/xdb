package org.xdb.benchmark.tpch;

public abstract class ExecuteTPCHBenchmark {
	
	

	
	public ExecuteTPCHBenchmark(int numberoftimes) {
		prepare();
		execute(numberoftimes);
		cleanup();
	}

	protected abstract void prepare();
	protected abstract void execute(int numberoftimes);
	protected abstract void cleanup();
}
