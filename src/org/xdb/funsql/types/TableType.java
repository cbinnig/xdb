package org.xdb.funsql.types;

import java.util.Vector;

public class TableType extends AbstractType {

	private static final long serialVersionUID = -7490767317632711085L;

	private Vector<TupleType> tuples = new Vector<TupleType>();
	
	//constructors
	public TableType(){
		this.tuples = new Vector<TupleType>(); 
		this.setNull(true);
	}
	
	public TableType(int size){
		this.tuples = new Vector<TupleType>(size);
	}
	
	//getters and setters
	public Vector<TupleType> getTuples() {
		return tuples;
	}

	public void addTuple(TupleType tuple) {
		this.tuples.add(tuple);
		this.setNull(false);
	}
	
	public TupleType getTuple(int i){
		return this.tuples.get(i);
	}
	
	public void setTuple(int i, TupleType tuple){
		this.tuples.set(i, tuple);
		this.setNull(false);
	}

	//methods
	public int size(){
		return this.tuples.size();
	}
	
	public void append(TableType table){
		this.tuples.addAll(table.tuples);
	}
	
	//helper methods
	@Override
	public TableType clone() {
		TableType table = new TableType();
		for(TupleType tuple: this.tuples){
			table.addTuple(tuple.clone());
		}
		return table;
	}
}
