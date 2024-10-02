package com.tutorial.Tutorial.Service;
import com.tutorial.Tutorial.Entity.Tutorial;

import java.util.List;

public interface TutorialService {
    void addTutorial(Tutorial tutorial);

    void updateTutorial(Tutorial tutorial, Long id);

    Tutorial findByID(Long id);

    void deleteById(Long id);

    List<Tutorial> getAll();

    List<Tutorial> getByPublished(boolean published);

    void deleteAll();


}
