package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.demo.DTO.UserDTO;
import com.example.demo.controllers.UserController;
import com.example.demo.data.model.User;
import com.example.demo.hateoas.assemblers.UserDTOModelAssembler;
import com.example.demo.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
	
	@Autowired
	private UserController userController;
	
	@MockBean //creating a fake object
	private UserService userService;
	
	@MockBean
	private UserDTOModelAssembler userDTOModelAssembler;
	
	
	private List<User> users;
	private List<UserDTO> userDTOs;
	
	private User validUser;
	private UserDTO validUserDTO;
	
	@BeforeEach
	public void init() {
		validUser = new User(1, "freddy", "goodson", "fredson", "frg@email.com", "password");
		validUserDTO = new UserDTO(1, "freddy", "goodson", "fredson", "frg@email.com", "password");
		
		users = new ArrayList<User>();
		userDTOs = new ArrayList<UserDTO>();
		
		
		users.add(validUser);
		userDTOs.add(validUserDTO);
		
	}
	
	@Test
	public void getUserByIdTest() {
		when(userService.readById(Mockito.any(Integer.class))).thenReturn(validUserDTO);
		
		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(validUserDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(userController.getUserById(validUser.getUserId()));
		
		verify(userService, times(1)).readById(Mockito.any(Integer.class));
		
	}
	
	@Test
	public void CreateUserTest() {
		EntityModel<UserDTO> userEntityModel = EntityModel.of(validUserDTO, 
				linkTo(methodOn(UserController.class).getUserById(validUserDTO.getUserId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
		
		when(userService.createUser(validUser)).thenReturn(validUserDTO);
		when(userDTOModelAssembler.toModel(validUserDTO)).thenReturn(userEntityModel);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validUserDTO.getUserId()));
		
		ResponseEntity<EntityModel<UserDTO>> response = ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
		
		assertThat(response).isEqualTo(userController.createUser(validUser));
		
		verify(userService, times(1)).createUser(validUser);
		verify(userDTOModelAssembler, times(1)).toModel(validUserDTO);
	}
	
	@Test //without using HATEOAS
	public void createUserTest2() {
		when(userService.createUser(Mockito.any(User.class))).thenReturn(validUserDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validUserDTO.getUserId()));
		
		ResponseEntity<UserDTO> response = 
				new ResponseEntity<UserDTO>(validUserDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(userController.createUser(validUser));
		
		verify(userService, times(1)).createUser(Mockito.any(User.class));
	}
	
	@Test
	public void deleteDuckTest() {
		// We only need to mock deleteDuck
		when(userService.deleteUser(Mockito.any(Integer.class))).thenReturn(true);
		
		// expected response
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		
		// Checking our expected response is equal to the actual response
		// of the controller.deleteDuck method
		assertThat(response).isEqualTo(userController.deleteUser(validUser.getUserId()));
		
		verify(userService, times(1)).deleteUser(Mockito.any(Integer.class));
	}
	
	@Test
	public void updateDuckTest() {
		// mock the update duck method
		when(userService.updateUser(Mockito.any(Integer.class), Mockito.any(User.class)))
			.thenReturn(validUserDTO);
		
		// expected response
		ResponseEntity<UserDTO> response =
				new ResponseEntity<UserDTO>(validUserDTO, HttpStatus.OK);
		
		// check our response
		assertThat(response).isEqualTo(userController.updateUser(validUser.getUserId(), validUser));
		
		// verify the response
		verify(userService, times(1)).updateUser(Mockito.any(Integer.class), Mockito.any(User.class));
	}
	
}
