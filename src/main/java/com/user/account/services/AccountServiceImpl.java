package com.user.account.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.account.entity.Account;
import com.user.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService,UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Account addUser(Account account) throws Exception{
		// TODO Auto-generated method stub
		Account check=accountRepository.findByEmail(account.getEmail());
		if(check==null) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			return accountRepository.save(account);
		}
		else {
			throw new Exception("email already exist");
		}
	}

	@Override
	public Account signUser(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		Account check=accountRepository.findByEmail(email);
		if(check==null) {
			throw new Exception("invaid email or password");
		}
		else if(passwordEncoder.matches(password, check.getPassword())) {
			return check;
		}
		else {
			throw new Exception("invaid email or password");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account=accountRepository.findByEmail(username);
		
		return new User(account.getEmail(),account.getPassword(),new ArrayList<>());
	}

	@Override
	public List<Account> getUsers() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public Account getUser(long parseLong) throws Exception {
		Account check=accountRepository.findById(parseLong).get();
		if(check!=null) {
			return check;
		}
		else {
			throw new Exception("User not found.");
			
		}
		
	}

	@Override
	public Account updateUser(long parseLong, Account account) throws Exception {
		Account check=accountRepository.findById(parseLong).get();
		if(check==null) {
			throw new Exception("User not found");
		}
		else {
			if(account.getPassword().length()<40) {
				account.setPassword(passwordEncoder.encode(account.getPassword()));
			}
			check.setName(account.getName());
			check.setAddress(account.getAddress());
			check.setEmail(account.getEmail());
			check.setPhoneNo(account.getPhoneNo());
			check.setPassword(account.getPassword());
			return accountRepository.save(check);
		}
		
	}

	@Override
	public Account getUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		Account check=accountRepository.findByEmail(email);
		if(check!=null) {
			return check;
		}
		else {
			throw new Exception("User not found.");
			
		}
	}
}
