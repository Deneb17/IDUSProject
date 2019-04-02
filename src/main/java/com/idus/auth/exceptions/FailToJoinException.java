package com.idus.auth.exceptions;

public class FailToJoinException extends RuntimeException {

	private static final long serialVersionUID = -2286473079784646020L;

	public FailToJoinException() {}

	public FailToJoinException(String message) {
		super(message);
	}
	
}
