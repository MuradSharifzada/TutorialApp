package com.tutorial.Tutorial.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "Category name might not be empty or empty quotes ")
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
