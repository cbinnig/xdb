package org.xdb.utils;

/**
 * Mutable Integer Object, that can be increased or decreased, starting at 1
 * Used to put it into Maps, because we do not longer need the second put at increasing Map values.
 * 
 * @author Timo Jacobs
 * 
 */
public class MutableInteger extends Number {
	private static final long serialVersionUID = 1040473005526566826L;

	private int value;

	public MutableInteger(final int value) {
		this.value = value;
	}

	public MutableInteger(final MutableInteger mutableInteger) {
		value = mutableInteger.value;
	}

	public MutableInteger inc() {
		value++;
		return this;
	}

	public MutableInteger dec() {
		value--;
		return this;
	}


	@Override
	public int intValue() {
		return value;
	}

	public void setValue(final int value) {
		this.value = value;
	}

	public void setValue(final MutableInteger newValue) {
		value = newValue.value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public float floatValue() {
		return value;
	}

	@Override
	public long longValue() {
		return value;
	}

	public MutableInteger add(final Integer value) {
		this.value+=value;
		return this;
	}

	public MutableInteger substract(final Integer value) {
		this.value-=value;
		return this;
	}

	@Override
	public MutableInteger clone() {
		return new MutableInteger(this);
	}

}
