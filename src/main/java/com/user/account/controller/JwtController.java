package com.user.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.account.entity.JwtRequest;
import com.user.account.entity.JwtResponse;
import com.user.account.entity.JwtUsername;
import com.user.account.helper.JwtUtil;
import com.user.account.services.AccountServiceImpl;





@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AccountServiceImpl customUserDetails;
	
	@PostMapping("/account/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		UserDetails userDetails=customUserDetails.loadUserByUsername(jwtRequest.getEmail());
		String token=jwtUtil.generateToken(userDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping("/account/token/get")
	public ResponseEntity<?> getUsername(@RequestBody String token) {
		String email= jwtUtil.extractUsername(token);
		return ResponseEntity.ok(new JwtUsername(email));
	}
}
