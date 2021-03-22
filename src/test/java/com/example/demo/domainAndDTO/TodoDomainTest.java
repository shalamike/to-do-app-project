package com.example.demo.domainAndDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull	;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.data.model.Todo;
import com.example.demo.data.model.User;

public class TodoDomainTest {
	private Todo todo1;
	private User user;
	private TodoDTO todoDto;
	
	
	@BeforeEach
	public void init() {
		
		todo1 = new Todo(1, "something", "please do something", user);
		
	}
	@Test
	public void testConstructors() {
		assertNotNull(todo1);
		
	}
	
	@Test
	public void getInfo() {
		assertEquals(todo1.getInfo(), "please do something");
	}
	
	@Test
	public void setInfo() {
		todo1.setInfo("do another thing");
		assertEquals(todo1.getInfo(), "do another thing");
	}

}
