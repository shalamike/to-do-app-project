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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.data.model.Todo;
import com.example.demo.data.model.User;
import com.example.demo.data.repository.TodoRepository;
import com.example.demo.mappers.TodoMapper;
import com.example.demo.services.TodoService;


@ExtendWith(MockitoExtension.class)
public class TodoServiceUnitTest {
	
	@InjectMocks
	private TodoService todoService;
	
	@Mock
	private TodoRepository todoRepository;
	
	@Mock
	private TodoMapper todoMapper;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	
	private Todo validTodo;
	private TodoDTO validTodoDTO;
	
	private User validUser;
	
	@BeforeEach
	public void init() {
		validUser = new User();
		
		validTodo = new Todo(1, "something", "please do something", validUser);
		validTodoDTO = new TodoDTO(1, "something", "please do something");
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
		
		todos.add(validTodo);
		todoDTOs.add(validTodoDTO);
	}
	
	@Test
	public void readAllTodosTest() {
		// When a specific method is called on a mock object,
		// we can specify what is returned
		when(todoRepository.findAll()).thenReturn(todos);
		when(todoMapper.mapToDTO(validTodo)).thenReturn(validTodoDTO);
		
		// When the service is called, this will initiate the
		// when-then returns
		assertThat(todoDTOs).isEqualTo(todoService.readAllTodos()); // true or false
		
		// Verify that our methods on our mock objects were called
		verify(todoRepository, times(1)).findAll();
		verify(todoMapper, times(1)).mapToDTO(validTodo);
	}
	
	
	@Test
	public void readTodoByIdTest() {
		when(todoRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTodo));
		when(todoMapper.mapToDTO(validTodo)).thenReturn(validTodoDTO);
		
		assertThat(validTodo).isEqualTo(todoService.readByID(1));
	}
	
	@Test
	public void readTodoByTaskTest() {
		when(todoRepository.getTaskByName(Mockito.any(String.class))).thenReturn(validTodo);
		when(todoMapper.mapToDTO(validTodo)).thenReturn(validTodoDTO);
		
		assertThat(validTodo).isEqualTo(todoService.readByTask(validTodo.getTask()));
	}
	
	@Test
	public void updateTodoTest() {
		
		Todo updatedTodo = new Todo(1, "something else", "please do something please do someting else", validUser);;
		TodoDTO updatedTodoDTO = new TodoDTO(1, "something else", "please do something please do someting else");
		
		when(todoRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTodo));
		
		when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(updatedTodo);
		
		when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(updatedTodoDTO);
		
		TodoDTO testToDTO = todoService.updateTodo(validTodo.getTodoId(), updatedTodo);
		
		assertThat(updatedTodoDTO).isEqualTo(testToDTO);
	}
	
	@Test 
	public void CreateTodoTest() {
		when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(validTodo);
		when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(validTodoDTO);
		
		assertThat(validTodoDTO).isEqualTo(todoService.createTodo(validTodo));
		
		verify(todoRepository,times(1)).save(Mockito.any(Todo.class));
		verify(todoMapper, times(1)).mapToDTO(Mockito.any(Todo.class));
	}
	@Test
	public void deleteTodoTest() {
		when(todoRepository.existsById(Mockito.any(Integer.class))).thenReturn(true).thenReturn(false);
		
		assertThat(true).isEqualTo(todoService.deleteTodo(1));
		
		verify(todoRepository, times(2)).existsById(Mockito.any(Integer.class));
		verify(todoRepository, times(1)).deleteById(Mockito.any(Integer.class));
	}
	
}
