package com.tutorial.Tutorial.Controller;

import com.tutorial.Tutorial.Entity.Tutorial;
import com.tutorial.Tutorial.Service.TutorialService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public List<Tutorial> getAllTutorials() {
        return tutorialService.getAll();
    }

    @GetMapping("/tutorials/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",ref = "Successfully retrieved tutorial"),
            @ApiResponse(responseCode = "404", ref = "Tutorial not found")
    })
    public Tutorial findByID(@PathVariable Long id) {
        return tutorialService.findByID(id);
    }

    @PostMapping("/tutorials")
    public void addTutorial(@RequestBody Tutorial tutorial) {
        tutorialService.addTutorial(tutorial);
    }


    @PutMapping("/tutorials/{id}")
    public void updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorial) {
        tutorialService.updateTutorial(tutorial, id);
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
