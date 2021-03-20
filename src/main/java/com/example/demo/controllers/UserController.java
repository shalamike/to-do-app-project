package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.hateoas.EntityModel;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;



import com.example.demo.DTO.UserDTO;
import com.example.demo.data.model.User;
import com.example.demo.hateoas.assemblers.UserDTOModelAssembler;
import com.example.demo.services.UserService;


@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {
	
	private UserService userService;
	
	private UserDTOModelAssembler userDTOModelAssembler;
	
	@Autowired
	public UserController(UserService userService, UserDTOModelAssembler userDTOModelAssembler) {
		this.userService = userService;
		this.userDTOModelAssembler = userDTOModelAssembler;
		
		
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<UserDTO>>> getAllUsers(){
		List<UserDTO> data = userService.readAllUsers();
		
		List<EntityModel<UserDTO>> entityModels = data.stream().map(user -> userDTOModelAssembler.toModel(user)).collect(Collectors.toList()); 
		
		CollectionModel<EntityModel<UserDTO>> collectionModel = CollectionModel.of(entityModels, linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
		
		
		return new ResponseEntity<CollectionModel<EntityModel<UserDTO>>>(collectionModel, HttpStatus.OK);
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
		// @RequestParam grabs a query parameter from our path
		// - In this case, it is called `id` and MUST BE SUPPLIED
		// - We can make it optional like so: @RequestParam(name = "id", required = false)
		//   - Or @RequestParam(name = "id", defaultValue = "")
		
		
		UserDTO user = userService.readById(id);
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@GetMapping("/userName/{name}")
	public ResponseEntity<UserDTO> readByUserName(@PathVariable("name") String name) {
		// @RequestParam grabs a query parameter from our path
		// - In this case, it is called `id` and MUST BE SUPPLIED
		// - We can make it optional like so: @RequestParam(name = "id", required = false)
		//   - Or @RequestParam(name = "id", defaultValue = "")
		
		
		UserDTO user = userService.readByUserName(name);
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user){
		UserDTO newUser = userService.createUser(user);
		
		EntityModel<UserDTO> model = userDTOModelAssembler.toModel(newUser);
		
		//ResponseEntity<?> response = ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", String.valueOf(newUser.getUserId()));
		return new ResponseEntity<UserDTO>(newUser, headers, HttpStatus.CREATED);
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User user){
		UserDTO updatedUser = userService.updateUser(id, user);
	
		
		EntityModel<UserDTO> model = userDTOModelAssembler.toModel(updatedUser);
		
		HttpHeaders headers = new HttpHeaders();
		// Link from model is turned into URI, and then into String
		headers.add("Location", model.getRequiredLink(IanaLinkRelations.SELF).toUri().toString());
		
		return new ResponseEntity<EntityModel<UserDTO>>(model, headers, HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
		UserDTO user = userService.readById(id);
		
		return new ResponseEntity<Boolean>(userService.deleteUser(id), HttpStatus.NO_CONTENT);
	}
	
	

}
