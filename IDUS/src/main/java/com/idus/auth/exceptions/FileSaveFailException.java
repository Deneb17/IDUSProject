package com.idus.auth.exceptions;

public class FileSaveFailException extends RuntimeException {

	private static final long serialVersionUID = 1124145938120270560L;

	public FileSaveFailException() {}

	public FileSaveFailException(String message) {
		super(message);
	}
}
