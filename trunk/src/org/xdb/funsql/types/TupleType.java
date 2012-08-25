package org.xdb.funsql.types;

import java.util.Vector;

public class TupleType extends AbstractType{

	private static final long serialVersionUID = -1943676391557309105L;
	private Vector<AbstractSimpleType> values;
	
	//constructors
	public TupleType(){
		this.values = new Vector<AbstractSimpleType>(); 
		this.setNull(true);
	}
	
	public TupleType(int size){
		this.values = new Vector<AbstractSimpleType>(size);
	}
	
	//getter and setter
	public Vector<AbstractSimpleType> getValues() {
		return values;
	}

	public void addValue(AbstractSimpleType value) {
		this.values.add(value);
		this.setNull(false);
	}

	public AbstractSimpleType getValue(int i){
		return this.values.get(i);
	}
	
	public void setValue(int i, AbstractSimpleType value){
		this.values.set(i, value);
		this.setNull(false);
	}
	
	//methods
	public int size(){
		return this.values.size();
	}
	
	public TupleType append(TupleType tuple){
		this.values.addAll(tuple.values);
		return this;
	}
	
	//helper methods
	@Override
	public boolean equals(Object o){
		TupleType tuple = (TupleType)o;
		boolean isEqual = true;
		
		for(int i=0; i<this.values.size(); ++i){
			if(!tuple.values.get(i).equals(this.values.get(i)))
				return false;
		}
		
		return isEqual;
	}
	
	@Override
	public TupleType clone() {
		TupleType tuple = new TupleType(this.values.size());
		for(AbstractSimpleType value: this.values){
			tuple.addValue(value.clone());
		}
		return tuple;
	}
}
