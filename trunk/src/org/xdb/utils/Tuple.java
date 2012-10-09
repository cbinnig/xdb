package org.xdb.utils;

/**
 * Data Class to Store two (different) Objects.
 * Useful to let a method return more than one object.
 * @author Timo Jacobs
 *
 * @param <S>
 * @param <T>
 */
public class Tuple<S extends Object, T extends Object> {

	private final S object1;
	private final T object2;

	public Tuple(final S object1, final T object2) {
		this.object1 = object1;
		this.object2 = object2;
	}

	public S getObject1() {
		return object1;
	}

	public T getObject2() {
		return object2;
	}
}
