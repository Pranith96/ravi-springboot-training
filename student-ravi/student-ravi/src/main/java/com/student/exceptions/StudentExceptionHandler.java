package com.student.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleStudentNotFoundException(StudentNotFoundException ex){
		ExceptionResponse exception = new ExceptionResponse();
		exception.setDetails("Data is not present");
		exception.setMessage(ex.getMessage());
		exception.setStatusCode("404");
		exception.setTimeStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.OK).body(exception);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		ExceptionResponse exception = new ExceptionResponse();
		exception.setDetails("Method Not Allowed");
		exception.setMessage(ex.getMessage());
		exception.setStatusCode("405");
		exception.setTimeStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.OK).body(exception);
	}
}
