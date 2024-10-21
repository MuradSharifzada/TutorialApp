package com.tutorial.Tutorial.Exception;

import lombok.Getter;

@Getter
public class TutorialNotPublishedException extends RuntimeException{
    private String message;
    public TutorialNotPublishedException(String message){
        super(message);
        this.message=message;
    }
}
