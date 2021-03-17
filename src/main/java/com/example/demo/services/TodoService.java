package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.data.model.Todo;
import com.example.demo.data.repository.TodoRepository;
import com.example.demo.mappers.TodoMapper;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	private TodoMapper todoMapper;
	
	@Autowired
	public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
		this.todoRepository = todoRepository;
		this.todoMapper  = todoMapper;
	}
	
	public List<TodoDTO> readAllTodos(){
		List<Todo> todos = todoRepository.findAll();
		List<TodoDTO> todoDTOs = new ArrayList<TodoDTO>();
		
		todos.forEach(todo -> todoDTOs.add(todoMapper.mapToDTO(todo)));
		
		return todoDTOs;
	}
	public TodoDTO readByID(Integer todoId) {
		Optional<Todo> todo = todoRepository.findById(todoId);
		return todoMapper.mapToDTO(todo.get());
	}
	
	public TodoDTO readByTask(String task) {
		Todo todo = todoRepository.getTaskByName(task);
		
		return todoMapper.mapToDTO(todo)
	}
	
	public TodoDTO createTodo(Todo todo) {
		Todo newTodo = todoRepository.save(todo);
		
		return todoMapper.mapToDTO(newTodo);
	}
	
	public TodoDTO updateTodo(Integer todoId, Todo todo) {
		Optional<Todo> TodoInDbOpt = todoRepository.findById(todoId);
		Todo todoIndb = TodoInDbOpt.get();
		todoIndb.setTask(todo.getTask());
		todoIndb.setUser(todo.getUser());
		todoIndb.setInfo(todo.getInfo());
		todoIndb.setStartDate(todo.getStartDate());
		todoIndb.setDueDate(todo.getDueDate());
		todoIndb.setDateComplete(todo.getDateComplete());
		
		Todo updatedTodo = todoRepository.save(todoIndb);
		return todoMapper.mapToDTO(updatedTodo);
	}
	
	public boolean deleteTodo(Integer id) {
		todoRepository.deleteById(id);
		
		boolean exists = todoRepository.existsById(id);
		
		return !exists;
	}
	
}
