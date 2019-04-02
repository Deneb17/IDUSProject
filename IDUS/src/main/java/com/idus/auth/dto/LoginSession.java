package com.idus.auth.dto;

public class LoginSession {
	
	private String sessionId;
	private int memberNo;

	public LoginSession() {}
	
	public LoginSession(String sessionId, int memberNo) {
		this.sessionId = sessionId;
		this.memberNo = memberNo;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
}