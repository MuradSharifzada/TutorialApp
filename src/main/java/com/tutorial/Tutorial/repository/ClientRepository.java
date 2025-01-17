package com.tutorial.Tutorial.repository;

import com.tutorial.Tutorial.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT c.email FROM Client c")
    List<String> findAllEmails();
}
