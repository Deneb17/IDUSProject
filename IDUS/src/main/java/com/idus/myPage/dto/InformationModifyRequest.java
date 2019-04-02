package com.idus.myPage.dto;

import com.idus.auth.dto.Member;
import com.idus.common.util.SecurityManager;

public class InformationModifyRequest {

	private String email;
	private String password;
	private String confPassword;
	private String name;
	private String phone;
	private String address;
	private String introduction;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = SecurityManager.hashBySha(confPassword);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean validatePassword() {
		if (this.password.equals(this.confPassword)) {
			return true;
		}
		return false;
	}

	public void getMemberInformation(Member member) {
		this.email = member.getEmail();
		this.name = member.getName();
		this.phone = member.getPhone();
		this.address = member.getAddress();
		this.introduction = member.getIntroduction();

	}

}
