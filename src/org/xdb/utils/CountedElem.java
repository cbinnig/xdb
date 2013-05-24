package org.xdb.utils;

public class CountedElem<T> implements Comparable<CountedElem<T>>{
	private T elem;
	private int count=1;
	
	public CountedElem(T elem) {
		super();
		this.elem = elem;
	}

	public T getElem() {
		return elem;
	}
	
	public void inc(){
		this.count++;
	}
	
	public void dec(){
		this.count--;
	}

	@Override
	public int compareTo(CountedElem<T> elem) {
		return (this.count>elem.count)?1:-1;
	}
	

}
