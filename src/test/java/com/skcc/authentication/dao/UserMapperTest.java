package com.skcc.authentication.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skcc.authentication.vo.Authority;
import com.skcc.authentication.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserMapperTest {
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void selectUser() {
		User user = userMapper.selectUser("kilsoo");
		assertThat("kilsoo", is(user.getUsername()));
		assertThat("Kilsoo Kang", is(user.getName()));
//		assertThat("1234", is(user.getPassword()));
		assertTrue(passwordEncoder.matches("1234", user.getPassword()));
	}
	
	@Test
	public void insertUser() {
		User user = new User();
		user.setName("Minseo Kang");
		user.setUsername("minseo");
		user.setPassword("1234");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(Authority.ADMIN.name()));
		authorities.add(new SimpleGrantedAuthority(Authority.USER.name()));
		user.setAuthorities(authorities);
		
		userMapper.insertUser(user);
		for (GrantedAuthority authority : user.getAuthorities()) {
			userMapper.insertAuthority(user.getUsername(), authority.getAuthority());
		}
		
		user.setName("Minseo Good");
		authorities.remove(0);
		
		userMapper.updateUser(user);
		userMapper.deleteAuthorities(user.getUsername());
		for (GrantedAuthority authority : user.getAuthorities()) {
			userMapper.insertAuthority(user.getUsername(), authority.getAuthority());
		}
		
		userMapper.deleteAuthorities(user.getUsername());
		userMapper.deleteUser(user.getUsername());
	}

}
