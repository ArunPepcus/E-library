package com.pepcus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
  }

//  @ExceptionHandler(javax.validation.ConstraintViolationException.class)
//  protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
//    logger.error("Costraits validatio error", ex);
//    Map<String, String> errors = new HashMap<>();
//
//    ex.getConstraintViolations().forEach(e -> {
//      errors.put("Message", e.getConstraintDescriptor().getMessageTemplate());
//    });
//
//    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//  }

//  @ExceptionHandler(javax.validation.ConstraintViolationException.class)
//  protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
//    logger.error("Costraits validatio error", ex);
//    Map<String, String> errors = new HashMap<>();
//    ex.getConstraintViolations().forEach((error) -> {
//      String fieldName ="msg";
//      String message = error.getConstraintDescriptor().getMessageTemplate();
//      errors.put(fieldName, message);
//    });
//    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//  }
}
