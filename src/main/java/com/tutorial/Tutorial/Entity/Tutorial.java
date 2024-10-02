package com.tutorial.Tutorial.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
