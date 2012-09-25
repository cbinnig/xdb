package org.xdb.tracker.signals;

import java.io.Serializable;

public class RegisterSignal<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -4487814618914464842L;

	private final T description;

	public RegisterSignal(final T description) {
		this.description = description;
	}

	public T getDescription() {
		return description;
	}
}
