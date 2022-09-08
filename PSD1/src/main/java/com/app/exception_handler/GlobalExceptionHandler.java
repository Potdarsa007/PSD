package com.app.exception_handler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.app.cust_exception.*;
import dto.ErrorResponse;

@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> map = ex.getFieldErrors().stream() //Stream<FieldError>
		.collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
		return ResponseEntity.badRequest().body(map);
	}
	
	
	@ExceptionHandler(OrderCompletedException.class)
	public ResponseEntity<?> handleOrderCompletedException1(OrderCompletedException e)
	{
		ErrorResponse resp=new ErrorResponse(e.getMessage(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@ExceptionHandler(OrderFeedbackException.class)
	public ResponseEntity<?> handleOrderFeedbackException(OrderFeedbackException e)
	{
		ErrorResponse resp=new ErrorResponse(e.getMessage(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e)
	{
		ErrorResponse resp=new ErrorResponse(e.getMessage(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e)
	{
		ErrorResponse resp=new ErrorResponse(e.getMessage(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}	
	

}
