package com.tutorial.Tutorial.Repository;

import com.tutorial.Tutorial.Entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByIsPublished(boolean published);


}
