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

public class TodoDTOTest {
	private TodoDTO todoDto;
	
	
	@BeforeEach
	public void init() {
		
		todoDto = new TodoDTO(1, "something", "please do something");
		
	}
	@Test
	public void testConstructors() {
		assertNotNull(todoDto);
		
	}
	
	@Test
	public void getInfo() {
		assertEquals(todoDto.getInfo(), "please do something");
	}
	
	@Test
	public void setInfo() {
		todoDto.setInfo("do another thing");
		assertEquals(todoDto.getInfo(), "do another thing");
	}

}
