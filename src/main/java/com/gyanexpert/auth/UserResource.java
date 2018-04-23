package com.gyanexpert.auth;

import java.security.Principal;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * Expose /user URL for client to get logged in user's principal
 */
@Configuration
@RestController
@EnableResourceServer
public class UserResource extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
	    	.antMatcher("/user")
	        .authorizeRequests().anyRequest().authenticated();
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
