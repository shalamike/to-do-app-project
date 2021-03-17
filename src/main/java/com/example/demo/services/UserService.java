package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.data.model.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.mappers.UserMapper;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private UserMapper userMapper;
	
	@Autowired
	public UserService(UserRepository userRepo, UserMapper userMapper) {
		this.userRepository = userRepo;
		this.userMapper = userMapper;
	}
	
	public List<UserDTO> readAllUsers(){
		List<User> users = userRepository.findAll();
		List<UserDTO> userDtos = new ArrayList<UserDTO>();
		
		users.forEach(user -> userDtos.add(userMapper.mapToDTO(user)));
		
		return userDtos;
	}
	
	public UserDTO readById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return userMapper.mapToDTO(user.get());
//		if (user.isPresent()) {
//			return usermapper.mapToDTO(user.get());
//		}//add an exception here
//		return null;
	}
	
	public UserDTO createUser(User user) {
		User newUser = userRepository.save(user);
		
		return userMapper.mapToDTO(newUser);
	}
	
	public UserDTO updateUser(Integer id, User user) throws EntityNotFoundException{
		Optional<User> userInDbOpt = userRepository.findById(id);
		
		User userInDb;
		userInDb = userInDbOpt.get();
		//need to do the userNotFoundException below
//		if (userInDbOpt.isPresent()) {
//			userInDb = userInDbOpt.get();
//		} else {
//
//		}	
		userInDb.setFirstName(user.getFirstName());
		userInDb.setLastName(user.getLastName());
		userInDb.setUserName(user.getUserName());
		userInDb.setEmail(user.getEmail());
		userInDb.setPassword(user.getPassword());
		
		User updatedUser = userRepository.save(userInDb);
		
		return userMapper.mapToDTO(updatedUser);
	}
	
	public UserDTO readByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		return userMapper.mapToDTO(user);
	}
	
	public boolean deleteUser(Integer id) {
//		if(!userRepository.existsById(id)) {
//		}
		
		userRepository.deleteById(id);
		
		boolean exists = userRepository.existsById(id);
		
		return !exists;
	}

}
