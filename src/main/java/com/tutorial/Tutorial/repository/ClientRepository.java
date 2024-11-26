package com.tutorial.Tutorial.repository;

import com.tutorial.Tutorial.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Client, Long> {
}
