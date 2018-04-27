package com.gyanexpert.auth.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * User Data ORM entity class 
 */

@Entity
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String firstName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
	}

	@Column(nullable = false, unique = true)
    private String username;
	
	private String firstName;
 
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 
    
}
