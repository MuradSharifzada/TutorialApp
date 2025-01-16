package com.tutorial.Tutorial.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Table(name = "tutorial_table")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    @Size(min = 1, max = 200, message = "title must be between 1 and 200 characters")
    @Schema(description = "Title of the entity", example = "Some title", required = true, maxLength = 200)
    private String title;
    private String content;
    @Column(name = "published")
    private Boolean isPublished;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;


//    @Column(name = "author_name", length = 100)
//    private String authorName;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Client user;

}
