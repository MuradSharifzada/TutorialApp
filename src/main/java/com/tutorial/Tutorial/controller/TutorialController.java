package com.tutorial.Tutorial.controller;

import com.tutorial.Tutorial.dto.TutorialDTO;
import com.tutorial.Tutorial.service.TutorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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


    @Operation(summary = "Get all tutorials", description = "getting for all tutorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retrieving all tutorial"),
            @ApiResponse(responseCode = "500", description = "failed retrieving all tutorials")
    })
    @GetMapping("/tutorials")
    public List<TutorialDTO> getAllTutorials() {
        return tutorialService.getAllTutorials();
    }


    @Operation(summary = "Get Tutorial by Id", description = "This method responsible retrieving tutorial by given id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorial retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Tutorial not found ")
    })
    @GetMapping("/tutorials/{id}")
    public TutorialDTO findTutorialById(@PathVariable Long id) {
        return tutorialService.findByID(id);
    }

    @Operation(summary = "Create Tutorial", description = "This method responsible creating tutorial ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorial created successfully"),
            @ApiResponse(responseCode = "409", description = "Tutorial Already exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void createTutorial(@Valid @RequestBody TutorialDTO tutorial) {
        tutorialService.addTutorialDTO(tutorial);
    }

    @Operation(summary = "Update Tutorial", description = "This method responsible updating tutorial ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorial updated successfully"),
            @ApiResponse(responseCode = "404", description = "Tutorial not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
     @PutMapping("/tutorials/{id}")
    public void updateTutorial(@PathVariable Long id, @RequestBody TutorialDTO tutorialDTO) {
        tutorialService.updateTutorialDTO(tutorialDTO, id);
    }

    @Operation(summary = "Delete Tutorials", description = "This method responsible deleting all tutorial ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorials deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Tutorials doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/tutorials")
    public void deleteAllTutorial() {
        tutorialService.deleteAll();
    }

    @Operation(summary = "Delete Tutorial with ID", description = "This method responsible deleting  tutorial by id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorial deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Tutorial doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/tutorials/{id}")
    public void deleteById(@PathVariable Long id) {
        tutorialService.deleteById(id);
    }

}
