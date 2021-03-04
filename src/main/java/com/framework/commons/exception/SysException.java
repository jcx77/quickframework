package com.framework.commons.exception;

public class SysException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SysException(String message) {
		super(message);
	}
}