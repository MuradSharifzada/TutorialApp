package com.tutorial.Tutorial.exception;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistException extends RuntimeException {
    private String message;

    public ResourceAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
