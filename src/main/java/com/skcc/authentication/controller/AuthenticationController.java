package com.skcc.authentication.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.skcc.authentication.service.UserService;
import com.skcc.authentication.vo.AuthenticationRequest;
import com.skcc.authentication.vo.AuthenticationToken;
import com.skcc.authentication.vo.User;

@RestController
@RequestMapping(value = "/api/user")
public class AuthenticationController extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
		
		User user = userService.getUser(username);
		
		return new AuthenticationToken(user.getUsername(), user.getAuthorities(), session.getId());
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<?> login(HttpSession session) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			logger.info("Authentication does not exist in the session");
			return ResponseEntity.badRequest().build();
		} else {
			SecurityContextHolder.getContext().setAuthentication(null);
			return ResponseEntity.ok().build();
		}
	}

}
