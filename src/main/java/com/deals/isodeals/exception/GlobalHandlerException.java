package com.deals.isodeals.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidationDealException.class)
    public ResponseEntity<?> FxResourceNotFoundException(ValidationDealException fxException, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(fxException.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> GlobalExceptionHandler(Exception e, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMethodValidationException(MethodValidationException exception,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage("Validation Failed", exception.getAllValidationResults().toString(), new Date());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
