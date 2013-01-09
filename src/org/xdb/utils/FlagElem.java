package org.xdb.utils;

public class FlagElem<T>{
	private T elem;
	private boolean flag;
	
	public FlagElem(T elem, boolean flag) {
		super();
		this.elem = elem;
		this.flag = flag;
	}

	public T getElem() {
		return elem;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setflag(boolean flag) {
		this.flag = flag;
	}
	
}