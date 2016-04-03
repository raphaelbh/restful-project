package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {
	
	private static final long serialVersionUID = 3196218134327299978L;

	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
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
	
	@Override
	public String toString() {
		return "User [id=".concat(this.getId().toString()).concat(", email=".concat(this.getEmail()).concat("]"));
	}
	
}
