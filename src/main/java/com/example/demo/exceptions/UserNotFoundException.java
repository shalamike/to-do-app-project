package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundException {
	
//	@ExceptionHandler(value = UserNotFoundException.class)
//	public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException dnfe){
//		return new ResponseEntity<String>(dnfe., HttpStatus.NOT_FOUND);
//	}


}
