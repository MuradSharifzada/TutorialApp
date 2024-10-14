package com.tutorial.Tutorial.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(TutorialAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleTutorialAlreadyExistException(TutorialAlreadyExistException e) {
        log.error("TutorialAlreadyExistException: {}", e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT
                , HttpStatus.CONFLICT.value()
                , e.getMessage()
                , LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TutorialNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTutorialNotFoundException(TutorialNotFoundException e) {
        log.error("TutorialNotFoundException: {}", e.getMessage(), e);
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND
                , HttpStatus.NOT_FOUND.value()
                , e.getMessage()
                , LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TutorialNotFoundByGivenID.class)
    public ResponseEntity<ErrorResponse> handleTutorialNotFindByGivenID(TutorialNotFoundByGivenID e) {
        log.error("TutorialNotFoundByGivenID: {}", e.getMessage(), e);
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
