package com.trj.jk.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("trj-jk-web").stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.and()
			.requestMatcher(new AntPathRequestMatcher("/v3/**"))
			.authorizeRequests()
//				.antMatchers("/v3/apply/**").access("#oauth2.hasScope('public')")
				.antMatchers("/v3/order/**").access("#oauth2.hasScope('write')")
				.antMatchers("/v3/card/**").access("#oauth2.hasScope('write')")
				.antMatchers("/v3/user/**").access("#oauth2.hasScope('write')")
				//TODO: add more
				;
	}
	
}
