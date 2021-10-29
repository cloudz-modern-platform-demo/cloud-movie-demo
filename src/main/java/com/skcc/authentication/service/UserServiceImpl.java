package com.skcc.authentication.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skcc.authentication.dao.UserMapper;
import com.skcc.authentication.vo.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUser(username);
	}

	@Override
	public User getUser(String username) {
		return userMapper.selectUser(username);
	}

	@Override
	@Transactional
	public void addUser(User user) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		
		logger.debug("Plain password is {}, Encrypted password is {}", user.getPassword(), encryptedPassword);
		
		user.setPassword(passwordEncoder.encode(encryptedPassword));
		
		userMapper.insertUser(user);
		for (GrantedAuthority authority : user.getAuthorities()) {
			userMapper.insertAuthority(user.getUsername(), authority.getAuthority());
		}
	}

	@Override
	@Transactional
	public void modifyUser(User user) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		
		logger.debug("Plain password is {}, Encrypted password is {}", user.getPassword(), encryptedPassword);
		
		user.setPassword(passwordEncoder.encode(encryptedPassword));

		userMapper.updateUser(user);
		userMapper.deleteAuthorities(user.getUsername());
		for (GrantedAuthority authority : user.getAuthorities()) {
			userMapper.insertAuthority(user.getUsername(), authority.getAuthority());
		}
	}

	@Override
	@Transactional
	public void removeUser(String username) {
		userMapper.deleteUser(username);
		userMapper.deleteAuthorities(username);
	}

	@Override
	public List<User> getUsers() {
		return userMapper.selectUsers();
	}

}
