package com.tutorial.Tutorial.model.dto;

import com.tutorial.Tutorial.validation.UniqueTitle;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TutorialDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    @NotBlank(message = "content must not be empty ")
    private String content;
    private Boolean isPublished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //private User user;

    @NotBlank(message = "title must not be empty")
    @UniqueTitle(message = "Title name must be unique,this title name already exist")
    private String title;
}
