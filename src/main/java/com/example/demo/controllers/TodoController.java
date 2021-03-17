package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.data.model.Todo;
import com.example.demo.services.TodoService;

@RestController
@RequestMapping(path = "/todo")
@CrossOrigin
public class TodoController {
	private TodoService todoService;
	
	@Autowired
	public TodoController (TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping
	public ResponseEntity<List<TodoDTO>>getAllUsers(){
		List<TodoDTO> data = todoService.readAllTodos();
		return new ResponseEntity<List<TodoDTO>>(data, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TodoDTO> createTodo(@RequestBody Todo todo){
		TodoDTO newTodo = todoService.createTodo(todo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newTodo.getTodoId()));
		
		return new ResponseEntity<TodoDTO>(newTodo, headers, HttpStatus.CREATED);
	}
}
