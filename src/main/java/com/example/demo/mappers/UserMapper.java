package com.example.demo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.UserDTO;
import com.example.demo.data.model.User;

@Component
public class UserMapper {
	
	private ModelMapper modelMapper;
	
	@Autowired
	public UserMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public UserDTO mapToDTO(User user) {
		return this.modelMapper.map(user, UserDTO.class);
	}
	
	public User mapToUser(UserDTO userDTO) {
		return this.modelMapper.map(userDTO, User.class);
	}
}
