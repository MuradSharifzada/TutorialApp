package com.tutorial.Tutorial.Repository;

import com.tutorial.Tutorial.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
