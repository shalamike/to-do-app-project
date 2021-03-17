package com.example.demo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
//	derived query
	public User findByUserName(String name);
	
// jpql query
	@Query("SELECT u from User u where u.userId = ?1")
	public User findUserById(Integer user_id);
//	need to fix one
	
	//selecting everything from user
//native query
	@Query(value = "SELECT * FROM user", nativeQuery = true)
	public List<User> getAllUsersSQL();
}
