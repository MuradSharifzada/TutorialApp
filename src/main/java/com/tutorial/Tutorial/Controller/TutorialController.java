package com.tutorial.Tutorial.Controller;

import com.tutorial.Tutorial.DTO.TutorialDTO;
import com.tutorial.Tutorial.Service.Impl.TutorialServiceImpl;
import com.tutorial.Tutorial.Service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {
    private final TutorialService tutorialService;

    @Autowired
    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @GetMapping("/tutorials")
    public List<TutorialDTO> getAllTutorials() {
        return tutorialService.getAll();
    }

    @GetMapping("/tutorials/{id}")
    public TutorialDTO findByID(@PathVariable Long id) {
        return tutorialService.findByID(id);
    }

    @PostMapping("/tutorials")
    public void addTutorial(@RequestBody TutorialDTO tutorialDTO) {
        tutorialService.addTutorialDTO(tutorialDTO);
    }

    @PutMapping("/tutorials/{id}")
    public void updateTutorial(@PathVariable Long id, @RequestBody TutorialDTO tutorialDTO) {
        tutorialService.updateTutorialDTO(tutorialDTO, id);
    }


    @DeleteMapping("/tutorials")
    public void deleteAllTutorial() {
        tutorialService.deleteAll();
    }

    @DeleteMapping("/tutorials/{id}")
    public void deleteById(@PathVariable Long id) {
        tutorialService.deleteById(id);
    }

}
