package com.example.demo.controller;

import com.example.demo.exception.PlayerNotFoundException;
import com.example.demo.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayersExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  protected ResponseEntity<Object> handleInternalServiceException(
          ServiceException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

  @ExceptionHandler(PlayerNotFoundException.class)
  protected ResponseEntity<Object> handlePlayerNotFoundException(
          PlayerNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }
}
