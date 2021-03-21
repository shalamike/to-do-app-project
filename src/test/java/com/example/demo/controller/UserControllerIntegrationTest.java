package com.example.demo.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.DTO.UserDTO;
import com.example.demo.controllers.UserController;
import com.example.demo.data.model.User;
import com.example.demo.mappers.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@WebMvcTest(UserController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ObjectMapper objectMapper; //converts objects to json and back
	
	private User validUser = new User(1, "leroy", "jenkins", "lrjkens", "lrj@email.com", "password");
	private UserDTO validUserDTO = new UserDTO(1, "leroy", "jenkins", "lrjkens", "lrj@email.com");
	
	private List<User> validUsers = List.of(validUser);
	private List<UserDTO> validUserDTOs= List.of(validUserDTO);
	
	@BeforeEach
	public void init() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/user");
		mockRequest.contentType(MediaType.APPLICATION_JSON);//type of data to mimic
		mockRequest.content(objectMapper.writeValueAsString(validUser)); //sending a user in 	
	}
	
	@Test
	public void createUserTest() throws Exception{
		User userToSave = new User("adam", "adamson", "adson", "ad@email.com", "notpassword");
		UserDTO expectedUser = new  UserDTO(1, "adam", "adamson", "adson", "ad@email.com");
		
		//create a mock to send a post request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/user");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);//type of data to mimic
		mockRequest.content(objectMapper.writeValueAsString(userToSave)); //sending a user in 
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher =  MockMvcResultMatchers.status().isCreated();
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedUser)); 
		//expecting a user back as a json object written as a string
		
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "1"); //id of where the user is located, in this case 
		
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher).andExpect(headerMatcher);
		
	}
	
	@Test
	public void getAllUsersTest() throws Exception{
		MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders.request(HttpMethod.GET, "/user");
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(validUserDTOs));
		
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		
	}
	
	@Test
	public void getUserByIdTest() throws Exception{
		MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders.request(HttpMethod.GET, "/user/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(validUserDTO));
		
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		
	}
	
	@Test
	public void getUserByUserNameTest() throws Exception{
		
		MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders.request(HttpMethod.GET, "/user/userName/lrjkens");
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(validUserDTO));
		
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	
	@Test
	public void updateUserTest() throws Exception {
		User updatedUser = new User(1, "Charlie", "charlson", "charlsssf", "ch@email.com", "notpassword");
		UserDTO expectedUser = new UserDTO(1, "Charlie", "charlson", "charlsssf", "ch@email.com");
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/user/1");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON); // Mime-Type
		mockRequest.content(objectMapper.writeValueAsString(updatedUser)); // sending user in
		
		// Specify what data type we expect in response
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedUser)); // expecting UserDTO back
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
}
