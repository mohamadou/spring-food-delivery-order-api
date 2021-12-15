package com.mohamadou.springfooddeliveryorderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class AppResourceExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundException exc) {
        ResourceErrorResponse resourceErrorResponse = new ResourceErrorResponse();

        resourceErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        resourceErrorResponse.setMessage(exc.getMessage());
        resourceErrorResponse.setError(HttpStatus.NOT_FOUND.toString());
        resourceErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(resourceErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleException(Exception exc) {
        ResourceErrorResponse resourceErrorResponse = new ResourceErrorResponse();

        resourceErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        resourceErrorResponse.setMessage(exc.getMessage());
        resourceErrorResponse.setError(HttpStatus.BAD_REQUEST.toString());
        resourceErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(resourceErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleException(HttpServerErrorException.InternalServerError exc) {
        ResourceErrorResponse resourceErrorResponse = new ResourceErrorResponse();

        resourceErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resourceErrorResponse.setMessage(exc.getMessage());
        resourceErrorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        resourceErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(resourceErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
