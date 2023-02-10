package com.youtube.jwt.entity;

public class JwtRequestForOfficer {
	private String officerId;
    private String officerPassword;
    
	public String getOfficerId() {
		return officerId;
	}
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	public String getOfficerPassword() {
		return officerPassword;
	}
	public void setOfficerPassword(String officerPassword) {
		this.officerPassword = officerPassword;
	}

   
}
