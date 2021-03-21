package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.controllers.TodoController;
import com.example.demo.controllers.UserController;
import com.example.demo.data.model.Todo;
import com.example.demo.data.model.User;
import com.example.demo.data.repository.TodoRepository;
import com.example.demo.mappers.TodoMapper;
import com.example.demo.services.TodoService;

@SpringBootTest // Given us an Application Context with our beans
@ExtendWith(value = {SpringExtension.class})
//@WebMvcTest(TodoController.class)
public class TodoControllerUnitTest {
	
	@Autowired
	private TodoController todoController;
	
	@MockBean
	private TodoService todoService;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoMapper todoMapper;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	
	private Todo validTodo;
	private TodoDTO validTodoDTO;
	
	private User user;
	
	
	@BeforeEach
	public void init() {
		user = new User();
		validTodo = new Todo(1, "something", "do something", user);
		validTodoDTO = new TodoDTO(1, "something", "doSomething");
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
		
		todos.add(validTodo);
		todoDTOs.add(validTodoDTO);
	}
	
	@Test
	public void getTodoByIdTest() {
		when(todoService.readByID(Mockito.any(Integer.class))).thenReturn(validTodoDTO);
		
		ResponseEntity<TodoDTO> response = new ResponseEntity<TodoDTO>(validTodoDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(todoController.getTodoById(validTodo.getTodoId()));
		
		verify(todoService, times(1)).readByID(Mockito.any(Integer.class));
		
	}
	
	@Test
	public void createUserTest() {
		when(todoService.createTodo(Mockito.any(Todo.class))).thenReturn(validTodoDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validTodoDTO.getTodoId()));
		
		ResponseEntity<TodoDTO> response = 
				new ResponseEntity<TodoDTO>(validTodoDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(todoController.createTodo(validTodo));
		
		verify(todoService, times(1)).createTodo(Mockito.any(Todo.class));
	}
	
	@Test
	public void DeleteUserTest() {
		// We only need to mock deleteDuck
		when(todoService.deleteTodo(Mockito.any(Integer.class))).thenReturn(true);
		
		// expected response
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		
		// Checking our expected response is equal to the actual response
		// of the controller.deleteDuck method
		assertThat(response).isEqualTo(todoController.deleteTodo(validTodo.getTodoId()));
		
		verify(todoService, times(1)).deleteTodo(Mockito.any(Integer.class));
	}
	
	@Test
	public void updateTodoTest() {
		// mock the update duck method
		when(todoService.updateTodo(Mockito.any(Integer.class), Mockito.any(Todo.class)))
			.thenReturn(validTodoDTO);
		
		// expected response
		ResponseEntity<TodoDTO> response =
				new ResponseEntity<TodoDTO>(validTodoDTO, HttpStatus.OK);
		
		// check our response
		assertThat(response).isEqualTo(todoController.updateTodo(validTodo.getTodoId(), validTodo));
		
		// verify the response
		verify(todoService, times(1)).updateTodo(Mockito.any(Integer.class), Mockito.any(Todo.class));
	}
	
	@Test
	public void getAllUserTestt() {
		EntityModel<TodoDTO> todoEntityModel = EntityModel.of(validTodoDTO, 
				linkTo(methodOn(UserController.class).getUserById(validTodoDTO.getTodoId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAllUsers()).withRel("todos"));
		
		List<EntityModel<TodoDTO>> entityModels = List.of(todoEntityModel );
		
		CollectionModel<EntityModel<TodoDTO>> collectionModel = CollectionModel.of(entityModels, linkTo(methodOn(TodoController.class).getAllTodos()).withSelfRel());
		
		when(todoService.readAllTodos()).thenReturn(todoDTOs);

		ResponseEntity<CollectionModel<TodoDTO>> response1 = 
				new ResponseEntity<CollectionModel<TodoDTO>>(HttpStatus.OK);
		
		
		
		assertThat(response1).isEqualTo(todoController.getAllTodos());
		
		verify(todoService, times(1)).readAllTodos();
	}
	
}
