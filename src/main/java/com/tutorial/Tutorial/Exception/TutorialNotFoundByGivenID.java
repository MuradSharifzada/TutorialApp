package com.tutorial.Tutorial.Exception;

import lombok.Getter;

@Getter
public class TutorialNotFoundByGivenID extends RuntimeException {
    private String message;

    public TutorialNotFoundByGivenID(String message) {
        super(message);
        this.message = message;
    }
}
