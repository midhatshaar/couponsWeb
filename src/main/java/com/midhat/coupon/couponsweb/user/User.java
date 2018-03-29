package com.midhat.coupon.couponsweb.user;

import javax.xml.bind.annotation.XmlRootElement;

import enums.ClientType;

@XmlRootElement
public class User {
	private String userName;
	private String UserPassword;
	private ClientType userType;
	
	public User() {
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public ClientType getUserType() {
		return userType;
	}
	public void setUserType(ClientType userType) {
		this.userType = userType;
	}
	
	

}
