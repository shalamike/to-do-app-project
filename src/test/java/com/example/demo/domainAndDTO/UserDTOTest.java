package com.example.demo.domainAndDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull	;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.DTO.UserDTO;

public class UserDTOTest {
	private UserDTO userDTO1;
	private UserDTO userDTO2;
	private UserDTO userDTO3;
	
	
	@BeforeEach
	public void init() {
		
		userDTO1 = new UserDTO(1,"Joyee", "Johnson","jojo","jo@email.com", new ArrayList<TodoDTO>());
		userDTO2 = new UserDTO("larry", "larryson", "larramy","lar@email.com", new ArrayList<TodoDTO>());
		
		
	}
	@Test
	public void testConstructors() {
		assertNotNull(userDTO1);
		assertNotNull(userDTO2);
		
	}
	
	@Test
	public void setIdentification() {
		userDTO1.setIdentification("1 uname u@email.com");
		assertEquals(userDTO1.getIdentification(), "1 uname u@email.com");
	}
	

}
