package com.tutorial.Tutorial.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TutorialDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Boolean isPublished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
