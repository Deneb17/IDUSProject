package com.idus.auth.dto;

public class KakaoLoginRequest {
	
	private String id;
	private String nickName;
	private String email;
	private String thumbnail;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public boolean hasThumbnail() {
		if(this.thumbnail == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hasEmail() {
		if(this.email == null) {
			return false;
		} else {
			return true;
		}
	}
	
}