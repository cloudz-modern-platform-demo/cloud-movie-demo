package com.skcc.authentication.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.skcc.authentication.vo.User;

@Mapper
public interface UserMapper {
	List<User> selectUsers();

	User selectUser(String username);
	
	void insertUser(User user);
	
	void insertAuthority(@Param("username") String username, @Param("authority") String authority);
	
	void updateUser(User user);
	
	void deleteUser(String username);
	
	void deleteAuthorities(String username);
}
