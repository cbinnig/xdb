package org.xdb.error;

import java.io.Serializable;

/**
 * Error implementation
 * 
 * @author cbinnig
 * 
 */
public class Error implements Serializable {
	private static final long serialVersionUID = -3552277353377571981L;
	public static final Error NO_ERROR = new Error();

	private EnumError type = EnumError.NO_ERROR;
	private String[] args = null;

	public Error() {
	}

	public Error(EnumError type, String[] args) {
		this.type = type;
		this.args = args;
	}

	public boolean isError() {
		return this.type != EnumError.NO_ERROR;
	}

	public String toString() {
		return EnumError.toString(type, args);
	}

	@Override
	public boolean equals(Object o) {
		Error err = (Error) o;
		return this.type == err.type;
	}
}
