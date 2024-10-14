package com.tutorial.Tutorial.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;


    @Column(nullable = false, length = 30)
    private String username;


    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$"
            , message = "Password must be 8-20 characters long" +
            ", contain at least one uppercase letter, one lowercase letter" +
            ", one number, and one special character (@#$%^&+=!)" +
            ", and must not contain spaces.")

    private String password;

    @Column(name = "joined_date")
    private LocalDateTime joinedDate;

}
