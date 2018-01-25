package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PDFValidatorResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    
    @ExceptionHandler(value = PDFValidationException.class)
    protected ResponseEntity<Object> handleInvalidPdf(RuntimeException ex, WebRequest request){
    	AppCustomRespons o = new AppCustomRespons(request.getDescription(false), "Error validating PDF");	
    	return handleExceptionInternal(ex, o, 
    	          new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }
}
