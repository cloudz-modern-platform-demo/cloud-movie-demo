package com.skcc.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skcc.authentication.service.UserService;
import com.skcc.authentication.vo.Authority;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeRequests()
//				.anyRequest().authenticated()
//				.and()
//				.formLogin();
		http
			.csrf().disable()
			.authorizeRequests()
				/** static **/
				.antMatchers("/data/**").permitAll()
				.antMatchers("/dist/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/less/**").permitAll()
				.antMatchers("/vendor/**").permitAll()
				/** rest api **/
				.antMatchers("/actuator/info").permitAll()
				.antMatchers("/actuator/health").permitAll()
				.antMatchers("/actuator/**").hasAnyAuthority(Authority.ADMIN.name())
//				.antMatchers("/api/user/**").hasAuthority(Authority.ADMIN.name())
				/** rest api **/
				.antMatchers("/api/user/login").permitAll()
				.antMatchers("/api/user/**").hasAuthority(Authority.ADMIN.name())
				.antMatchers("/api/contents/**").hasAnyAuthority(Authority.ADMIN.name(), Authority.USER.name())
				/** api for web **/
				.antMatchers("/contents/**").hasAuthority(Authority.ADMIN.name())
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error").permitAll()
				.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");
	}
}
