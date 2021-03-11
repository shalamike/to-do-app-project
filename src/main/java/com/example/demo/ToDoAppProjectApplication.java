package com.example.demo;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class ToDoAppProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ToDoAppProjectApplication.class, args);
		
		// Getting some beans from the application context
		LocalTime time = context.getBean("localTime", LocalTime.class);
		LocalTime time2 = context.getBean("localTime", LocalTime.class);
		
		// Logging and comparing the beans for equality
		System.out.println(time);
		System.out.println(time2);
		System.out.println(time.equals(time2));
	}
	@Bean // by default these are a singleton so therefore there is only a single instance. however we can change this to a prototype to have multiple instances
	@Scope("singleton") //or we can label this as a prototype
	public static LocalTime localTime() {
		return LocalTime.now();//
	}

}
