package com.idus.auth.exceptions;

public class FailToCreateSessionException extends RuntimeException {

	private static final long serialVersionUID = -386519801355191132L;

	public FailToCreateSessionException() {}

	public FailToCreateSessionException(String message) {
		super(message);
	}
	
}
