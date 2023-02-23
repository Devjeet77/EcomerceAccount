package com.user.account.entity;

import lombok.Data;

@Data
public class JwtRequest {
	
	String email;
	String password;
}
