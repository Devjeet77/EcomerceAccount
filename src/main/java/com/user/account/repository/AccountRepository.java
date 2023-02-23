package com.user.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByEmail(String email);
}
