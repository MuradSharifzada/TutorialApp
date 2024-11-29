package com.tutorial.Tutorial.dto;

import com.tutorial.Tutorial.validation.UniqueTitle;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorialDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    @NotBlank(message = "content must not be empty ")
    private String content;
    private Boolean isPublished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //private Client user;

    @NotBlank(message = "title must not be empty")
    @UniqueTitle(message = "Title name must be unique,this title name already exist")
    private String title;
}
