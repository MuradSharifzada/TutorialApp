package com.tutorial.Tutorial.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "tutorial_table")
@Entity
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String title;
    private String content;
    private String author;
    @Column(name = "published")
    private Boolean isPublished;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
