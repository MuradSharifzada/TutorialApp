package com.tutorial.Tutorial.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Table(name = "tutorial_table")
@Entity
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @NotBlank
    private String title;
    private String content;
    private String author;
    @Column(name = "published")
    private Boolean isPublished;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
