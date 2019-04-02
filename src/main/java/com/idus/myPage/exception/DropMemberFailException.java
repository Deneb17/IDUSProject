package com.idus.myPage.exception;

public class DropMemberFailException extends RuntimeException {

	private static final long serialVersionUID = -1039035851816919032L;

	public DropMemberFailException() {}

	public DropMemberFailException(String message) {
		super(message);
	}
}
