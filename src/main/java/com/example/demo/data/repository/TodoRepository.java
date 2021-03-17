package com.example.demo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.data.model.Todo;
import com.example.demo.data.model.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{

	//native sql query
	@Query(value = "SELECT * FROM to_do", nativeQuery = true)
	public List<Todo> getAllTodos();
	
	
	//jpql query
	@Query("Select t from Todo t where t.task = ?1")
	public Todo getTaskByName(String name);

}
