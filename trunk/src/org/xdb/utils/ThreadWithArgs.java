package org.xdb.utils;

public class ThreadWithArgs extends Thread {
	protected Object[] args;
	
	public ThreadWithArgs(Object[] args) {
		super();
		
		this.args = args;
	}
}
