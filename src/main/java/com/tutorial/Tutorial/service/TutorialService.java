package com.tutorial.Tutorial.service;
import com.tutorial.Tutorial.dto.TutorialDTO;

import java.util.List;

public interface TutorialService {
    void addTutorialDTO(TutorialDTO tutorialDTO);

    void updateTutorialDTO(TutorialDTO tutorialDTO, Long id);

    TutorialDTO findByID(Long id);

    void deleteById(Long id);

    List<TutorialDTO> getAllTutorials();

    List<TutorialDTO> getByPublished(boolean published);

    void deleteAll();


}
