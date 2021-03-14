package com.example.demo.DTO;

public class UserDTO {
	
	private int userId;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String email;
	
	private String password;
	
	public UserDTO(int userId, String firstName, String lastName, String userName ,String email){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
	}
	
	public UserDTO(int userId, String firstName, String lastName, String userName ,String email, String password){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public String getIdentification() {
		return userId + " " + userName + " " + email;
		
	}
	
	public void setIdentification(String in) {
		String[] identification = in.split(" ");
		this.userId = Integer.parseInt(identification[0]);
		this.userName = identification[1];
		this.email = identification[2];
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "[userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", email=" + email + "]";
	}
}
