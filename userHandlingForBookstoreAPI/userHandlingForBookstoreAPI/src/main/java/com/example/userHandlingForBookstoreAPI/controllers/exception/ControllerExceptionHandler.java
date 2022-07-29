package com.example.userHandlingForBookstoreAPI.controllers.exception;

import com.example.userHandlingForBookstoreAPI.exceptions.MatchingOldPasswordException;
import com.example.userHandlingForBookstoreAPI.exceptions.ObjectInvalidException;
import com.example.userHandlingForBookstoreAPI.exceptions.ObjectNotFoundException;
import com.example.userHandlingForBookstoreAPI.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletException request)
    {
        var error = new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ObjectInvalidException.class)
    public ResponseEntity<StandardError> objectInvalidException(ObjectInvalidException e, ServletException request)
    {
        var error = new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<StandardError> tokenExpiredException(TokenExpiredException e, ServletException request)
    {
        var error = new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MatchingOldPasswordException.class)
    public ResponseEntity<StandardError> matchingOldPasswordException(
            MatchingOldPasswordException e, ServletException request)
    {
        var error = new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
