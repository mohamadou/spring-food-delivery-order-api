package com.mohamadou.springfooddeliveryorderapi.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        // Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());


        body.put("errors", errors);
        return new ResponseEntity<>(body, headers,status);
    }
}
