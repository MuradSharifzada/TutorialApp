package com.tutorial.Tutorial.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundByGivenID extends RuntimeException {
    private String message;

    public ResourceNotFoundByGivenID(String message) {
        super(message);
        this.message = message;
    }
}
