package com.tutorial.Tutorial.Service;
import com.tutorial.Tutorial.DTO.TutorialDTO;

import java.util.List;

public interface TutorialService {
    void addTutorialDTO(TutorialDTO tutorialDTO);

    void updateTutorialDTO(TutorialDTO tutorialDTO, Long id);

    TutorialDTO findByID(Long id);

    void deleteById(Long id);

    List<TutorialDTO> getAll();

    List<TutorialDTO> getByPublished(boolean published);

    void deleteAll();


}
