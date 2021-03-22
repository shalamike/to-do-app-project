package com.example.demo.DTO;

import java.util.List;


public class UserDTO {
	
	private int userId;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String email;
	
	
	private List<TodoDTO> todos;
	
	public UserDTO() {
		
	}
	
	public UserDTO(int userId, String firstName, String lastName, String userName ,String email, List<TodoDTO> todos){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.todos = todos;
	}
	
	public UserDTO(int userId, String firstName, String lastName, String userName ,String email){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
	}
	
	public UserDTO(String firstName, String lastName, String userName ,String email, List<TodoDTO> todos){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
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

	
	public List<TodoDTO> getTodos() {
		return todos;
	}

	public void setTodos(List<TodoDTO> todos) {
		this.todos = todos;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		result = prime * result + ((todos == null) ? 0 : todos.hashCode());
//		result = prime * result + userId;
//		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		UserDTO other = (UserDTO) obj;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
//		if (firstName == null) {
//			if (other.firstName != null)
//				return false;
//		} else if (!firstName.equals(other.firstName))
//			return false;
//		if (lastName == null) {
//			if (other.lastName != null)
//				return false;
//		} else if (!lastName.equals(other.lastName))
//			return false;
//		if (todos == null) {
//			if (other.todos != null)
//				return false;
//		} else if (!todos.equals(other.todos))
//			return false;
//		if (userId != other.userId)
//			return false;
//		if (userName == null) {
//			if (other.userName != null)
//				return false;
//		} else if (!userName.equals(other.userName))
//			return false;
//		return true;
//	}


}
