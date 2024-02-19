package com.smartClinic.smartclinic.Exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(EmailDuplicadoException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<StandartError> handleGenericException(Exception e){
		StandartError erro = new StandartError();
		erro.setTimeStamp(Instant.now());
		erro.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		erro.setError("Internal Erro 500");
		erro.setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(erro);
	}
	


	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<StandartError> handleNotFoundException(NotFoundException e){
		StandartError erro = new StandartError();
		erro.setTimeStamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("Not Found 400");
		erro.setMessage(e.getMessage()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<?>> handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<String> errorString = new ArrayList<>();
		result.getFieldErrors().forEach(error -> errorString.add(error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorString);
	}
}
