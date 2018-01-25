package com.example.demo;

@SuppressWarnings("serial")
public class PDFValidationException extends RuntimeException{

    public PDFValidationException(String s) {
        super(s);
    }
    
    public PDFValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
