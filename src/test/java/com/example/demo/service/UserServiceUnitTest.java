package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.DTO.UserDTO;
import com.example.demo.data.model.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.mappers.UserMapper;
import com.example.demo.services.UserService;



//@SpringBootTest
//@ExtendWith(value = {SpringExtension.class})
@WebMvcTest(UserService.class)
public class UserServiceUnitTest {
	
	@Autowired
	@InjectMocks
	private UserService userService;
	
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private UserMapper userMapper;
	
	private List <User> users;
	private List <UserDTO> userDTOs;
	
	private User validUser;
	private UserDTO validUserDTO;
	
	@BeforeEach
	public void init() {
		validUser = new User(1, "bob", "ronson", "bobro", "bob@email.com", "password" );
		validUserDTO = new UserDTO(1, "bob", "ronson", "bobro", "bob@email.com");
		
		users = new ArrayList<User>();
		userDTOs = new ArrayList<UserDTO>();
		
		users.add(validUser);
		userDTOs.add(validUserDTO);
	}
	
	@Test
	public void readAllUsersTest() {
		when(userRepository.findAll()).thenReturn(users);
		when(userMapper.mapToDTO(validUser)).thenReturn(validUserDTO);
		
		assertThat(userDTOs).isEqualTo(userService.readAllUsers());
		
		verify(userRepository, times(1)).findAll();
		verify(userMapper, times(1)).mapToDTO(validUser);
	}
	
	@Test
	public void updateUserTest() {
		
		User UpdatedUser = new User(1, "joe", "johnson", "jojoson", "jojo@email.com", "password2");
		UserDTO updatedUserDTO = new UserDTO(1, "joe", "johnson", "jojoson", "jojo@email.com");
		
		when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validUser));
		
		when(userRepository.save(Mockito.any(User.class))).thenReturn(UpdatedUser);
		
		when(userMapper.mapToDTO(Mockito.any(User.class))).thenReturn(updatedUserDTO);
		
		UserDTO testToDTO = userService.updateUser(validUser.getUserId(), UpdatedUser);
		
		assertThat(updatedUserDTO).isEqualTo(testToDTO);
	}
}
