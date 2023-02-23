package com.user.account.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NotNull(message="Name should not be null")
	private String name;
//	private Long phoneNo;
//	private String bloodGroup;
	@Email(message="invalid email address")
	private String email;
	@NotNull(message="Phone number should not be null")
	private Long phoneNo;
	private String address;
	@NotNull(message="password should not be empty")
	private String password;
	
	public Account() {
		
	}
}
