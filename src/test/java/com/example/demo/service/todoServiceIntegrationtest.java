package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.data.model.Todo;
import com.example.demo.data.model.User;
import com.example.demo.data.repository.TodoRepository;
import com.example.demo.mappers.TodoMapper;
import com.example.demo.services.TodoService;

public class todoServiceIntegrationtest {
	
	@Autowired 
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
	private UserDTO userDTO;
	
	
	@BeforeEach
	public void init() {
		validTodo = new Todo(1, "something", "please do someting", user);
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
		
		todoRepository.deleteAll();
		
		validTodo = todoRepository.save(validTodo);
		validTodoDTO = todoMapper.mapToDTO(validTodo);
		
		todos.add(validTodo);
		
		todoDTOs.add(validTodoDTO);
		
	}
	
	@Test
	public void ReadAllTodosTest() {
		List<TodoDTO> todoInDb = todoService.readAllTodos();
		
		assertThat(todoDTOs).isEqualTo(todoInDb);
	}
	
	
	@Test
	public void createTodoTest() {
		Todo newTodo = new Todo("something aswell", "please do another thing");
		TodoDTO expectedTodoDto = todoMapper.mapToDTO(newTodo);
		
		TodoDTO savedTodo = todoService.createTodo(newTodo);
		expectedTodoDto.setTodoId(savedTodo.getTodoId());
		
		assertThat(savedTodo).isEqualTo(expectedTodoDto);
	}
	
	@Test
	public void deleteTodoTest() {
		assertThat(todoService.deleteTodo(1)).isEqualTo(true);
	}
}
