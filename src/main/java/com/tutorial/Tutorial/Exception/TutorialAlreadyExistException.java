package com.tutorial.Tutorial.Exception;

import lombok.Getter;

@Getter
public class TutorialAlreadyExistException extends RuntimeException {
    private String message;

    public TutorialAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
