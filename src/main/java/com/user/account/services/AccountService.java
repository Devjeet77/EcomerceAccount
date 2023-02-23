package com.user.account.services;

import java.util.List;

import com.user.account.entity.Account;

public interface AccountService {

	public Account addUser(Account account) throws Exception;

	public Account signUser(String email, String password) throws Exception;

	public List<Account> getUsers();

	public Account getUser(long parseLong) throws Exception;

	public Account updateUser(long parseLong, Account account) throws Exception;

	public Account getUserByEmail(String email) throws Exception;

}
