package com.tutorial.Tutorial.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {
        log.error("TutorialAlreadyExistException: {}", e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT
                , HttpStatus.CONFLICT.value()
                , e.getMessage()
                , LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentException(MethodArgumentNotValidException e) {

        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
        {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("ResourceNotFoundException: {}", e.getMessage(), e);
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND
                , HttpStatus.NOT_FOUND.value()
                , e.getMessage()
                , LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundByGivenID.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundByGivenID(ResourceNotFoundByGivenID e) {
        log.error("ResourceNotFoundByGivenID: {}", e.getMessage(), e);
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND
                , HttpStatus.NOT_FOUND.value()
                , e.getMessage()
                , LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TutorialNotPublishedException.class)
    public ResponseEntity<ErrorResponse> handleTutorialNotPublished(TutorialNotPublishedException e) {
        log.error("TutorialNotPublishedException: {}", e.getMessage(), e);
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST
                , HttpStatus.BAD_REQUEST.value()
                , e.getMessage()
                , LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
