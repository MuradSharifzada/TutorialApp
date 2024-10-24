package com.tutorial.Tutorial.Service.Impl;

import com.tutorial.Tutorial.Model.DTO.TutorialDTO;
import com.tutorial.Tutorial.Model.Entity.Tutorial;
import com.tutorial.Tutorial.exception.ResourceNotFoundByGivenID;
import com.tutorial.Tutorial.exception.ResourceNotFoundException;
import com.tutorial.Tutorial.Mapper.TutorialMapper;
import com.tutorial.Tutorial.Repository.TutorialRepository;
import com.tutorial.Tutorial.Service.TutorialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorialServiceImpl implements TutorialService {
    private static final Logger log = LoggerFactory.getLogger(TutorialServiceImpl.class);
    private final TutorialRepository tutorialRepository;

    @Autowired
    public TutorialServiceImpl(TutorialRepository tutorialRepo) {
        this.tutorialRepository = tutorialRepo;
    }

    private final TutorialMapper tutorialMapper = TutorialMapper.INSTANCE;


    @Override
    @CachePut(value = "tutorials",key = "#tutorialDto.getId()")
    public void addTutorialDTO(TutorialDTO tutorialDto) {
        log.info("Starting process to add a new tutorial to the database...");
        try {
            Tutorial tutorial = tutorialMapper.toEntity(tutorialDto);
            tutorialRepository.save(tutorial);
            log.info("Successfully added tutorial titled '{}' to the database.", tutorialDto.getTitle());
        } catch (ResourceNotFoundException e) {
            log.error("Failed to add tutorial. Reason: Tutorial not found.");
            throw new ResourceNotFoundException("Tutorial doesn't exist");
        }
    }

    @Override
    @CachePut(value = "tutorials", keyGenerator = "customKeyGenerator")
    public void updateTutorialDTO(TutorialDTO tutorialDTO, Long id) {
        log.info("Attempting to update tutorialDTO with ID: {}", id);
        Tutorial existingTutorial = tutorialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundByGivenID("Tutorial with ID " + id + " not found"));

        log.info("Tutorial with ID: {} found. Proceeding with update...", id);
        existingTutorial.setTitle(tutorialDTO.getTitle());
        //existingTutorial.setAuthor(tutorialDTO.getAuthor());
        existingTutorial.setContent(tutorialDTO.getContent());
        existingTutorial.setIsPublished(tutorialDTO.getIsPublished());
//        existingTutorial.setUpdatedAt(OffsetDateTime.now());

        tutorialRepository.save(existingTutorial);
        log.info("Successfully updated tutorialDTO with ID: {}", id);
    }

    @Override
    @Cacheable(value = "tutorials", keyGenerator = "customKeyGenerator")
    public TutorialDTO findByID(Long id) {
        log.info("Searching for tutorial with ID: {}", id);
        Tutorial tutorial = tutorialRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundByGivenID("Tutorial with ID " + id + " does not exist."));
        log.info("Successfully found tutorial with ID: {}", id);
        return tutorialMapper.toDTO(tutorial);
    }

    @Override
    @CacheEvict(value = "tutorials", keyGenerator = "customKeyGenerator")
    public void deleteById(Long id) {
        log.info("Initiating delete process for tutorial with ID: {}", id);
        Tutorial tutorial = tutorialRepository.findById(id).orElse(null);

        if (tutorial == null) {
            log.warn("Delete operation aborted. Tutorial with ID {} not found.", id);
            throw new ResourceNotFoundByGivenID("Tutorial not found for the given ID: " + id);
        } else {
            log.info("Tutorial with ID: {} found. Proceeding to delete...", id);
            tutorialRepository.deleteById(id);
            log.info("Successfully deleted tutorial with ID: {}", id);
        }
    }

    @Override
    @Cacheable(value = "tutorials", keyGenerator = "customKeyGenerator")
    public List<TutorialDTO> getAll() {
        log.info("Fetching all tutorials from the database...");
        List<Tutorial> tutorials;
        try {
            tutorials = tutorialRepository.findAll();
            if (tutorials.isEmpty()) {
                log.warn("No tutorials found in the database.");
            } else {
                log.info("Successfully retrieved {} tutorials from the database.", tutorials.size());
            }
        } catch (ResourceNotFoundException e) {
            log.error("Failed to fetch tutorials. Reason: No tutorials available.");
            throw new ResourceNotFoundException("No tutorials available in the database.");
        }
        return tutorials.stream().map(tutorialMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    @Cacheable(value = "tutorials", keyGenerator = "customKeyGenerator")
    public List<TutorialDTO> getByPublished(boolean published) {
        log.info("Searching for tutorials with published status: {}", published);
        List<Tutorial> tutorials = tutorialRepository.findByIsPublished(published);

        if (tutorials.isEmpty()) {
            log.warn("No tutorials found with published status: {}", published);
        } else {
            log.info("Successfully found {} tutorials with published status: {}", tutorials.size(), published);
        }
        return tutorials.stream().map(tutorialMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "tutorials", keyGenerator = "customKeyGenerator")
    public void deleteAll() {
        log.info("Attempting to delete all tutorials from the database...");
        try {
            tutorialRepository.deleteAll();
            log.info("All tutorials successfully deleted from the database.");
        } catch (ResourceNotFoundException e) {
            log.error("Delete operation failed. Reason: No tutorials exist to delete.");
            throw new ResourceNotFoundException("No tutorials available for deletion.");
        }
    }

}
