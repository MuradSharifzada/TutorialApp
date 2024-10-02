package com.tutorial.Tutorial.Service.Impl;

import com.tutorial.Tutorial.Entity.Tutorial;
import com.tutorial.Tutorial.Exception.TutorialNotFoundByGivenID;
import com.tutorial.Tutorial.Exception.TutorialNotFoundException;
import com.tutorial.Tutorial.Exception.TutorialNotPublishedException;
import com.tutorial.Tutorial.Repository.TutorialRepository;
import com.tutorial.Tutorial.Service.TutorialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService {
    private static final Logger log = LoggerFactory.getLogger(TutorialServiceImpl.class);
    private final TutorialRepository tutorialRepo;

    @Autowired
    public TutorialServiceImpl(TutorialRepository tutorialRepo) {
        this.tutorialRepo = tutorialRepo;
    }

    @Override
    public void addTutorial(Tutorial tutorialDto) {
        log.info("Starting process to add a new tutorial to the database...");
        try {
            tutorialRepo.save(tutorialDto);
            log.info("Successfully added tutorial titled '{}' to the database.", tutorialDto.getTitle());
        } catch (TutorialNotFoundException e) {
            log.error("Failed to add tutorial. Reason: Tutorial not found.");
            throw new TutorialNotFoundException("Tutorial doesn't exist");
        }
    }

    @Override
    public void updateTutorial(Tutorial tutorial, Long id) {
        log.info("Attempting to update tutorial with ID: {}", id);
        Tutorial newTutorial = tutorialRepo.findById(id).orElseThrow(
                () -> new TutorialNotFoundByGivenID("Tutorial with ID " + id + " not found"));

        log.info("Tutorial with ID: {} found. Proceeding with update...", id);
        newTutorial.setId(tutorial.getId());
        newTutorial.setTitle(tutorial.getTitle());
        newTutorial.setAuthor(tutorial.getAuthor());
        newTutorial.setContent(tutorial.getContent());
        newTutorial.setIsPublished(tutorial.getIsPublished());
        newTutorial.setUpdatedAt(LocalDateTime.now());

        tutorialRepo.save(newTutorial);
        log.info("Successfully updated tutorial with ID: {}", id);
    }

    @Override
    public Tutorial findByID(Long id) {
        log.info("Searching for tutorial with ID: {}", id);
        Tutorial tutorial = tutorialRepo
                .findById(id)
                .orElseThrow(() -> new TutorialNotFoundByGivenID("Tutorial with ID " + id + " does not exist."));
        log.info("Successfully found tutorial with ID: {}", id);
        return tutorial;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Initiating delete process for tutorial with ID: {}", id);
        Tutorial tutorial = tutorialRepo.findById(id).orElse(null);

        if (tutorial == null) {
            log.warn("Delete operation aborted. Tutorial with ID {} not found.", id);
            throw new TutorialNotFoundByGivenID("Tutorial not found for the given ID: " + id);
        } else {
            log.info("Tutorial with ID: {} found. Proceeding to delete...", id);
            tutorialRepo.deleteById(id);
            log.info("Successfully deleted tutorial with ID: {}", id);
        }
    }

    @Override
    public List<Tutorial> getAll() {
        log.info("Fetching all tutorials from the database...");
        List<Tutorial> list;
        try {
            list = tutorialRepo.findAll();
            if (list.isEmpty()) {
                log.warn("No tutorials found in the database.");
            } else {
                log.info("Successfully retrieved {} tutorials from the database.", list.size());
            }
        } catch (TutorialNotFoundException e) {
            log.error("Failed to fetch tutorials. Reason: No tutorials available.");
            throw new TutorialNotFoundException("No tutorials available in the database.");
        }
        return list;
    }

    @Override
    public List<Tutorial> getByPublished(boolean published) {
        log.info("Searching for tutorials with published status: {}", published);
        List<Tutorial> list;
        try {
            list = tutorialRepo.findByisPublished(published);
            if (list.isEmpty()) {
                log.warn("No tutorials found with published status: {}", published);
            } else {
                log.info("Successfully found {} tutorials with published status: {}", list.size(), published);
            }
        } catch (TutorialNotPublishedException e) {
            log.error("Failed to retrieve tutorials. Reason: No tutorials found with published status: {}", published);
            return null;
        }
        return list;
    }

    @Override
    public void deleteAll() {
        log.info("Attempting to delete all tutorials from the database...");
        try {
            tutorialRepo.deleteAll();
            log.info("All tutorials successfully deleted from the database.");
        } catch (TutorialNotFoundException e) {
            log.error("Delete operation failed. Reason: No tutorials exist to delete.");
            throw new TutorialNotFoundException("No tutorials available for deletion.");
        }
    }
}
