package com.user.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.account.entity.Account;
import com.user.account.services.AccountService;

@RestController
@CrossOrigin
public class MyController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/account")
	public Account addUser(@RequestBody @Valid Account account) throws Exception {
		return accountService.addUser(account);
	}
	
	@PostMapping("/account/login")
	public Account signUser(@RequestBody Account account) throws Exception {
		return accountService.signUser(account.getEmail(),account.getPassword());
	}
	
	@GetMapping("/account/users")
	public List<Account> getUsers(){
		return accountService.getUsers();
	}	
	
	@GetMapping("/account/user/{id}")
	public Account getUser(@PathVariable String id) throws Exception {
		return accountService.getUser(Long.parseLong(id));
	}
	@GetMapping("/account/email/{email}")
	public Account getUserByEmail(@PathVariable String email) throws Exception{
		return accountService.getUserByEmail(email);
	}
	
	@PutMapping("/account/{id}")
	public Account updateUser(@PathVariable String id, @RequestBody @Valid Account account) throws Exception{
		return accountService.updateUser(Long.parseLong(id),account);
	}
}
