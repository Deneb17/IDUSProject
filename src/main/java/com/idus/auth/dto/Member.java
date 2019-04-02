package com.idus.auth.dto;

public class Member {
	
	private int memberNo;
	private String email;
	private String password;
	private String name;
	private String nickName;
	private String phone;
	private String introduction;
	private String zoneCode;
	private String address;
	private String detailAddress;
	private String thumbnail;
	private int point;
	private int follower;
	private boolean isSocial;
	
	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
		this.password = password;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public boolean isSocial() {
		return isSocial;
	}

	public void setSocial(boolean isSocial) {
		this.isSocial = isSocial;
	}
	
	
	// password 비교
	public boolean comparePasswordWith(String password) {
		
		boolean isEqual = false;
		
		if(this.password.equals(password)) {
			isEqual = true;
		}
		
		return isEqual;
		
	}
	
	
	// 회원가입 요청으로 부터 정보 이동
	public void getInformation(JoinRequest joinRequest) {
		this.email = joinRequest.getEmail();
		this.password = joinRequest.getPassword();
		this.name = joinRequest.getName();
		this.nickName = joinRequest.getNickName();
		this.phone = joinRequest.getPhone();
		this.introduction = joinRequest.getIntroduction();
		this.zoneCode = joinRequest.getZoneCode();
		this.address = joinRequest.getAddress();
		this.detailAddress = joinRequest.getDetailAddress();
		this.isSocial = false;
	}
	
}
