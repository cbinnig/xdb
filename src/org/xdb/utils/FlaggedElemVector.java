package org.xdb.utils;

import java.util.Vector;

public class FlaggedElemVector<T>{
	private Vector<T> elems;
	private Vector<Boolean> flags;
	/** Constructor where every flag is set on false
	 * @param elems Vector of elements which should be flaged
	 */
	public FlaggedElemVector(Vector<T> elems) {
		super();
		this.elems = elems;
		this.flags = new Vector<Boolean>();
		for(int i = 0; i < this.elems.size(); i++){
			this.flags.add(false);
		}
	}
	
	/**
	 * Constructor where a custom flag vector has to be given
	 * @param elems vector of elems 
	 * @param flags vector of flags for elems
	 */
	public FlaggedElemVector(Vector<T> elems, Vector<Boolean> flags) {
		super();
		this.elems = elems;
		this.flags = flags;
	}
	
	/** return a Flag Elem of type T which contains the elem and the corresponding Flag
	 * @param index in the vector
	 * @return
	 */
	public FlagElem<T> getFlaggedElem(int index){
		return new FlagElem<T>(elems.get(index), flags.get(index));
	}
	public boolean hasfalseElems(){
		return this.flags.contains(false);
	}
	
	public boolean hastrueElems(){
		return this.flags.contains(false);
	}
	
	public int getSize(){
		return elems.size();
	}
	public void setUsed(int index){
		this.flags.set(index, true);
	}
	
}