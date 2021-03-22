package com.example.demo.domainAndDTO;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull	;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.data.model.User;

public class UserDomainTest {
	
	User user1;
	User user2;
	User user3;
	
	
	@BeforeEach
	public void init() {
		user1 = new User(1, "larry", "jones", "Larjones", "lar@email.com", "password" );
		user2 = new User(2, "mike", "ronson", "mikeRon", "email", "password", new ArrayList());
		user3 = new User(3, "andy", "holmes", "aHolme");
	}
	
	@Test
	public void testingConstructors() {
		assertNotNull(user1);
		assertNotNull(user2);
		assertNotNull(user3);
	
	}
}
