package com.skcc.authentication.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skcc.authentication.vo.Authority;
import com.skcc.authentication.vo.User;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void getUser() {
		User user = userService.getUser("kilsoo");
		assertNotNull(user);
		assertTrue(user.getAuthorities().size() > 1);
	}
	
	@Test
	public void addUser() {
		User user = new User();
		user.setName("Minseo Kang");
		user.setUsername("minseo");
		user.setPassword("1234");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(Authority.ADMIN.name()));
		authorities.add(new SimpleGrantedAuthority(Authority.USER.name()));
		user.setAuthorities(authorities);

		userService.addUser(user);
		
		user = userService.getUser(user.getUsername());
		
		assertNotNull(user);
		
		user.setPassword("1234");
		
		userService.modifyUser(user);
		
		userService.removeUser(user.getUsername());
	}
}
