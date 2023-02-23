package com.user.account.entity;

import lombok.Data;

@Data
public class JwtUsername {
	
	String email;
	public JwtUsername(String email) {
		this.email=email;
	}
}
