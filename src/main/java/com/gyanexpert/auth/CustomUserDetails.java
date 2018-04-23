package com.gyanexpert.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/*
 * Custom User Details
 */
public class CustomUserDetails extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstName;

	public CustomUserDetails(com.gyanexpert.auth.User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), authorities);
		firstName = user.getFirstName();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
