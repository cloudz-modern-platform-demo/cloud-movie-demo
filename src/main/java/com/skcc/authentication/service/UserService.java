package com.skcc.authentication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.skcc.authentication.vo.User;

public interface UserService extends UserDetailsService {
	User getUser(String username);
	
	void addUser(User user);
	
	void modifyUser(User user);
	
	void removeUser(String username);
	
	List<User> getUsers();
}
