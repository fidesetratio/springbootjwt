package com.app.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.JwtRequest;
import com.app.model.JwtResponse;
import com.app.utils.JwtTokenUtil;

@RestController
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${app.jwt.secreet}")
	private String secreet;
	

	@Value("${app.jwt.expired}")
	private long expired;
	

	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(secreet, expired);
	  	    final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
			return ResponseEntity.ok(new JwtResponse(token));
	    }
	  
	  private void authenticate(String username, String password) throws Exception {
			Objects.requireNonNull(username);
			Objects.requireNonNull(password);
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			} catch (DisabledException e) {
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}
		}
}
