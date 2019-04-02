package com.idus.myPage.exception;

public class InformationUpdateFailException extends RuntimeException {
	
	private static final long serialVersionUID = -9160641605690113368L;
	
	public InformationUpdateFailException() {}

	public InformationUpdateFailException(String message) {
		super(message);
	}

}
