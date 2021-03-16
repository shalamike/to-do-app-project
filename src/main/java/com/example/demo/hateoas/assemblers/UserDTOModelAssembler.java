package com.example.demo.hateoas.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.UserDTO;
import com.example.demo.controllers.UserController;
import com.example.demo.mappers.UserMapper;
@Component
public class UserDTOModelAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {
	
	private UserMapper userMapper;
	
	@Autowired
	public UserDTOModelAssembler(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public EntityModel<UserDTO> toModel(UserDTO entity) {
		EntityModel<UserDTO> entityModel = EntityModel.of(entity,
				linkTo(methodOn(UserController.class).getUserById(entity.getUserId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
		);
		
		return entityModel;
	}


}
