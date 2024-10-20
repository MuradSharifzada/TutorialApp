package com.tutorial.Tutorial.Model.Entity;

import com.tutorial.Tutorial.Validation.UniqueTutorialTitle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Data
@Table(name = "tutorial_table")
@Entity
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    @UniqueTutorialTitle(message = "Title must be unique")
    @NotBlank(message = "title must not be empty")
    @Size(min = 1, max = 200, message = "title must be between 1 and 200 characters")
    @Schema(description = "Title of the entity", example = "Some title", required = true, maxLength = 200)
    private String title;

    @NotBlank(message = "content must not be empty ")
    private String content;

    @Column(name = "published")
    private Boolean isPublished;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

}
