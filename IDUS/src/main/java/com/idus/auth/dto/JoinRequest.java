package com.idus.auth.dto;

import org.springframework.web.multipart.MultipartFile;

import com.idus.common.util.SecurityManager;

public class JoinRequest {
	
	private String email;
	private String password;
	private String name;
	private String nickName;
	private String phone;
	private String zoneCode;
	private String address;
	private String detailAddress;
	private String introduction;
	private MultipartFile thumbnail;
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getZoneCode() {
		return zoneCode;
	}
	
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDetailAddress() {
		return detailAddress;
	}
	
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public MultipartFile getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}