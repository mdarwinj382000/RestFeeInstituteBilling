package spring.restproject.restfeeinstitutebilling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.restproject.restfeeinstitutebilling.modeldto.AuthUser;
import spring.restproject.restfeeinstitutebilling.modeldto.TokenAuth;
import spring.restproject.restfeeinstitutebilling.security.JwtUtils;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping("/")
	public String getrequestcheck() {
		return "Hello Friend I Arrived";
	}

	@PostMapping("/signin")
	public ResponseEntity<TokenAuth> postrequestcheck(@RequestBody AuthUser authuser) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authuser.getUsername(), authuser.getPassword()));
		String token = jwtUtils.generateJwtToken(userDetailsService.loadUserByUsername(authuser.getUsername()));
		return new ResponseEntity<>(new TokenAuth(token), HttpStatus.OK);
	}

}
