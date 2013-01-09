package org.xdb.benchmark.tpch;

public abstract class ExecuteTPCHBenchmark {
	private int numberoftimes = 1;
	
	public ExecuteTPCHBenchmark(int numberoftimes) {
		this.numberoftimes  = numberoftimes;
	}
	
	public void run(){
		prepare();
		execute(this.numberoftimes);
		cleanup();
	}

	protected abstract void prepare();
	protected abstract void execute(int numberoftimes);
	protected abstract void cleanup();
}
