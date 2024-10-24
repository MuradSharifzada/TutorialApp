package com.tutorial.Tutorial.exception;

import lombok.Getter;

@Getter
public class TutorialNotFoundException extends RuntimeException {
    private  String message;

    public TutorialNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}