package com.br.barbershop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.barbershop.config.JWTTokenUtil;
import com.br.barbershop.models.JWTRequest;
import com.br.barbershop.models.JWTResponse;
import com.br.barbershop.services.JWTUserDetailsService;

@RestController
@CrossOrigin
public class JWTAuthenticationController {

@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private JWTTokenUtil jwtTokenUtil;

@Autowired
private JWTUserDetailsService userDetailsService;

@RequestMapping(value = "api/barbershop/login", method = RequestMethod.POST)
public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
	authenticate(authenticationRequest.getUsername(), authenticationRequest.getSenha());
	final UserDetails userDetails = userDetailsService
			.loadUserByUsername(authenticationRequest.getUsername());
	final String token = jwtTokenUtil.generateToken(userDetails);
return ResponseEntity.ok(new JWTResponse(token));
}

private void authenticate(String username, String password) throws Exception {
try {
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
} catch (DisabledException e) {
	throw new Exception("USER_DISABLED", e);
} catch (BadCredentialsException e) {
	throw new Exception("INVALID_CREDENTIALS", e);
}
}
}
