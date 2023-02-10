package com.youtube.jwt.entity;

public class JwtResponseForOfficer {
	private Officer officer;
	private String jwtToken;

	public Officer getOfficer() {
		return officer;
	}

	public JwtResponseForOfficer(Officer officer, String jwtToken) {
		super();
		this.officer = officer;
		this.jwtToken = jwtToken;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
