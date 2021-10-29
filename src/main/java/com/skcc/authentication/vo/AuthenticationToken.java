package com.skcc.authentication.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken {
	private String username;
	private Collection<GrantedAuthority> authorities;
	private String token;

	@SuppressWarnings("unchecked")
	public AuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities, String token) {
		this.username = username;
		this.authorities = (Collection<GrantedAuthority>) authorities;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenticationToken [username=");
		builder.append(username);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

}
