package com.rts.tap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "ThirdPartyCredentials")
public class ThirdPartyCredentitals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long thirdPartyCredentialsId;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "credential_Role_Id")
	private ThirdPartyRole role;

	public ThirdPartyCredentitals() {
		super();
	}

	public long getThirdPartyCredentialsId() {
		return thirdPartyCredentialsId;
	}

	public void setThirdPartyCredentialsId(long thirdPartyCredentialsId) {
		this.thirdPartyCredentialsId = thirdPartyCredentialsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public ThirdPartyRole getRole() {
		return role;
	}

	public void setRole(ThirdPartyRole role) {
		this.role = role;
	}
}