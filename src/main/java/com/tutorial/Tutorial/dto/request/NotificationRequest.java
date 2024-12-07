package com.tutorial.Tutorial.dto.request;

import lombok.Data;

@Data
public class NotificationRequest {
    private String recipient;
    private String subject;
    private String message;
}