package com.usuario.exceptiom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobaExceptionHandler {
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<String> handleResposnseStatusException(
			ResponseStatusException exception){
		return ResponseEntity.status(exception.getStatusCode())
				.body(exception.getReason());
	}
	
}
