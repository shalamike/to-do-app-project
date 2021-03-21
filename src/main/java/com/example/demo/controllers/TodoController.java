package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TodoDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.data.model.Todo;
import com.example.demo.data.model.User;
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
	public ResponseEntity<List<TodoDTO>>getAllTodos(){
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
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") Integer id) {
		// @RequestParam grabs a query parameter from our path
		// - In this case, it is called `id` and MUST BE SUPPLIED
		// - We can make it optional like so: @RequestParam(name = "id", required = false)
		//   - Or @RequestParam(name = "id", defaultValue = "")
		
		
		TodoDTO todo = todoService.readByID(id);
		
		return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
	}
	
	@GetMapping("/task/{name}")
	public ResponseEntity<TodoDTO> readByTaskName(@PathVariable("name") String name) {
		// @RequestParam grabs a query parameter from our path
		// - In this case, it is called `id` and MUST BE SUPPLIED
		// - We can make it optional like so: @RequestParam(name = "id", required = false)
		//   - Or @RequestParam(name = "id", defaultValue = "")
		
		
		TodoDTO Todo = todoService.readByTask(name);
		
		return new ResponseEntity<TodoDTO>(Todo, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") int id, @RequestBody Todo todo){
		TodoDTO updatedTodo = todoService.updateTodo(id, todo);
		
		HttpHeaders headers = new HttpHeaders();
		// Link from model is turned into URI, and then into String
		headers.add("Location", String.valueOf(updatedTodo.getTodoId()));
		
		return new ResponseEntity<TodoDTO>(updatedTodo, headers, HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTodo(@PathVariable("id") int id) {		
		return new ResponseEntity<Boolean>(todoService.deleteTodo(id), HttpStatus.OK);
	}
}
