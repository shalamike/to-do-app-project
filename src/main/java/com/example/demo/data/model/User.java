package com.example.demo.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "first_name")
	@NotNull
	private String firstName;
	
	@Column(name = "last_name")
	@NotNull
	private String lastName;
	
	@Column(name = "user_name")
	@NotNull
	private String userName;
	
	@Column(name = "email_address")
	@NotNull
	private String email;
	
	@Column(name = "password")
	@NotNull
	private String password;
	
	public User() {
		
	}
	
	public User(int userId, String firstName, String lastName, String userName, String email, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String userName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public User(int userId, String firstName, String lastName, String userName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}
	
	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	

	
	
}
