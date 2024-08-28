package com.example.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.entity.Example2;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@ControllerAdvice
public class GlobalExceptions {
	@Autowired
	  private Validator validator;
	
	  @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(ConstraintViolationException ex) {
		  
	        Map<String, String> errors = new HashMap<>();
	        
	        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	            String fieldName = violation.getPropertyPath().toString();
	            String errorMessage = violation.getMessage();
	            errors.put(fieldName, errorMessage);
	        }
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	  
	  

	  public void validateEntity(Example2 example) {
	      Set<ConstraintViolation<Example2>> violations = validator.validate(example);
	      if (!violations.isEmpty()) {
	          for (ConstraintViolation<Example2> violation : violations) {
	              System.out.println(violation.getMessage());
	          }
	      }
	  }

	  
	  
	  @ExceptionHandler(NullPointerException.class)
	    public ResponseEntity<Map<String, String>>NullPointerException(NullPointerException ex) {
		  
	        
	        
	        Map<String, String> errors = new HashMap<>();
 
	        ex.printStackTrace();
 
	        errors.put("error", "A null pointer exception occurred. Please check your request and ensure all required fields are provided.");
	        
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
