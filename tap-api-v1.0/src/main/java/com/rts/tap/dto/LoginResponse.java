package com.rts.tap.dto;

public class LoginResponse {
	
	private Long id;
	private String status;
    private String role;
    
	public LoginResponse(Long aid, String status, String role) {
		super();
		this.id = aid;
		this.status = status;
		this.role = role;
	}
	
	public LoginResponse(String status, String role) {
		super();
		this.status = status;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
