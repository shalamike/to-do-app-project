package com.example.demo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
//	//try finding the user name using this
//	public User findbyUserName(String name);
	
	//or this
//	@Query("SELECT u from user u where u.user_name = ?1")
//	public User findbyUserNameJPQL(String user_name);
//	need to fix one
	
	//selecting everything from user
	@Query(value = "SELECT * FROM user", nativeQuery = true)
	public List<User> getAllUsersSQL();
}
