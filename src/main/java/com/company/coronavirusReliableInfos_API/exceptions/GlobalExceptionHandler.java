package com.company.coronavirusReliableInfos_API.exceptions;

import com.company.coronavirusReliableInfos_API.LoggerConfiguration;
import com.company.coronavirusReliableInfos_API.controller.ArticleController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = Logger.getLogger(ArticleController.class.getName());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException (ResourceNotFoundException ex, WebRequest request) {
        System.out.println("ResourceNot");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        String messageToLog = String.format("%s, Resource not found exception invoked\nCaused by: %s", LoggerConfiguration.dtf.format(LocalDateTime.now()), ex.toString());
        log.info(messageToLog);

        System.out.println(messageToLog);

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

        String messageToLog = String.format("%s, Global exception invoked\nCaused by: %s", LoggerConfiguration.dtf.format(LocalDateTime.now()), ex.toString());
        log.info(messageToLog);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
