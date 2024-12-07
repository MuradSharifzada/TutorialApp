package com.tutorial.Tutorial.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "client_table")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;


    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "client_roles", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<RoleType> roles = new HashSet<>();
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private Tutorial tutorial;


}
