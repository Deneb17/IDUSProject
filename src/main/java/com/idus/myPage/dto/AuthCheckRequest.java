package com.idus.myPage.dto;

import com.idus.common.util.SecurityManager;

public class AuthCheckRequest {
	
	private String path;
	private String email;
	private String password;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = SecurityManager.hashBySha(password);
	}
	
}