package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.DTO.UserDTO;
import com.example.demo.data.model.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.mappers.UserMapper;
import com.example.demo.services.UserService;

@SpringBootTest
public class UserServiceIntegrationTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	private List<User> users;
	private List<UserDTO> userDTOs;
	
	private User validUser;
	private UserDTO validUserDTO;
	
	@BeforeEach
	public void init() {
		//user to be saved in our DB
		validUser = new User("andrew", "smyth", "smythee", "asmythe@email.com", "password" );
		
		users = new ArrayList<User>();
		userDTOs = new ArrayList<UserDTO>();
		
		userRepository.deleteAll();
		
		validUser = userRepository.save(validUser);
		
		validUserDTO = userMapper.mapToDTO(validUser);
		
		users.add(validUser);
		
		userDTOs.add(validUserDTO);	
	}
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Test
	public void readAllUsersTest() {
		List<UserDTO> usersInDb = userService.readAllUsers();
		
		assertThat(userDTOs).isEqualTo(usersInDb);
	}

}
