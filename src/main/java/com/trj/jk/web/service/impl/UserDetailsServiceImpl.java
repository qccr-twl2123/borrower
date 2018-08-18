package com.trj.jk.web.service.impl;

import java.util.Collections;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.service.UserBasicService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private String defaultSecret;
	private String defaultPassword;
	private Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	private UserBasicService userBasicService;
	
	public UserDetailsServiceImpl(UserBasicService userBasicService) {
		Assert.notNull(userBasicService, "userBasicService must not be null.");
		this.userBasicService = userBasicService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBasic user = userBasicService.queryByMobile(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User doesn't exist: username=%s", username));
		}
		return new User(username, getDefaultSecret(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}
	
	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = StringUtils.hasText(defaultPassword) ? defaultPassword : "0a7c07f5be907e6165f2b0fa827be0c8f2898f98377ea8a410fed9236175f4f8";
		setDefaultSecret(encoder.encodePassword(this.defaultPassword, null));
	}

	public String getDefaultSecret() {
		if (defaultSecret == null) {
			setDefaultPassword(null);
		}
		return defaultSecret;
	}

	public void setDefaultSecret(String defaultSecret) {
		this.defaultSecret = defaultSecret;
	}

}
