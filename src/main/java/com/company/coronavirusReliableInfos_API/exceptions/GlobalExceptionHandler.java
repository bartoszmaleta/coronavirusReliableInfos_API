package com.company.coronavirusReliableInfos_API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException (ResourceNotFoundException ex, WebRequest request) {
        System.out.println("ResourceNot");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler (Exception ex, WebRequest request) {
        System.out.println("Exce");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        System.out.println(ex.getLocalizedMessage());
        System.out.println(ex.getStackTrace());
        System.out.println(ex.toString());
        System.out.println(ex.getCause());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
