package com.tutorial.Tutorial.service;

import com.tutorial.Tutorial.dto.TutorialDTO;
import com.tutorial.Tutorial.entity.Tutorial;
import com.tutorial.Tutorial.exception.ResourceNotFoundByGivenID;
import com.tutorial.Tutorial.mapper.TutorialMapper;
import com.tutorial.Tutorial.repository.TutorialRepository;
import com.tutorial.Tutorial.service.impl.TutorialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TutorialServiceImplTest {

    @Mock
    private TutorialRepository tutorialRepository;

    @Mock
    private TutorialMapper tutorialMapper;

    @InjectMocks
    private TutorialServiceImpl tutorialService;

    private Tutorial tutorial;
    private TutorialDTO tutorialDTO;

    private static final Long TUTORIAL_ID = 1L;

    @BeforeEach
    void setUp() {
        tutorial = Tutorial.builder()
                .id(TUTORIAL_ID)
                .title("Test Title")
                .content("Test Content")
                .isPublished(true)
                .build();

        tutorialDTO = TutorialDTO.builder()
                .id(TUTORIAL_ID)
                .title("Test Title")
                .content("Test Content")
                .isPublished(true)
                .build();
    }

//    @Test
//    @DisplayName("Add a new tutorial successfully")
//    void testAddTutorialDTO() {
//        // Arrange
//        when(tutorialMapper.toEntity(tutorialDTO)).thenReturn(tutorial);
//
//        // Act
//        assertDoesNotThrow(() -> tutorialService.addTutorialDTO(tutorialDTO));
//
//        // Assert
//        verify(tutorialMapper, times(1)).toEntity(tutorialDTO);
//        verify(tutorialRepository, times(1)).save(tutorial);
//    }

    @Test
    @DisplayName("Update an existing tutorial successfully")
    void testUpdateTutorialDTO() {
        // Arrange
        when(tutorialRepository.findById(TUTORIAL_ID)).thenReturn(Optional.of(tutorial));

        // Act
        assertDoesNotThrow(() -> tutorialService.updateTutorialDTO(tutorialDTO, TUTORIAL_ID));

        // Assert
        verify(tutorialRepository, times(1)).findById(TUTORIAL_ID);
        verify(tutorialRepository, times(1)).save(tutorial);
    }

    @Test
    @DisplayName("Fail to find a tutorial by ID when not found")
    void testFindByID_NotFound() {
        // Arrange
        when(tutorialRepository.findById(TUTORIAL_ID)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundByGivenID exception = assertThrows(ResourceNotFoundByGivenID.class,
                () -> tutorialService.findByID(TUTORIAL_ID));
        assertEquals("Tutorial with ID " + TUTORIAL_ID + " does not exist.", exception.getMessage());
        verify(tutorialRepository, times(1)).findById(TUTORIAL_ID);
    }

    @Test
    @DisplayName("Delete a tutorial by ID successfully")
    void testDeleteById_Success() {
        // Arrange
        when(tutorialRepository.findById(TUTORIAL_ID)).thenReturn(Optional.of(tutorial));

        // Act
        assertDoesNotThrow(() -> tutorialService.deleteById(TUTORIAL_ID));

        // Assert
        verify(tutorialRepository, times(1)).findById(TUTORIAL_ID);
        verify(tutorialRepository, times(1)).deleteById(TUTORIAL_ID);
    }

    @Test
    @DisplayName("Fail to delete a tutorial by ID when not found")
    void testDeleteById_NotFound() {
        // Arrange
        when(tutorialRepository.findById(TUTORIAL_ID)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundByGivenID exception = assertThrows(ResourceNotFoundByGivenID.class,
                () -> tutorialService.deleteById(TUTORIAL_ID));
        assertEquals("Tutorial not found for the given ID: " + TUTORIAL_ID, exception.getMessage());
        verify(tutorialRepository, times(1)).findById(TUTORIAL_ID);
        verify(tutorialRepository, never()).deleteById(TUTORIAL_ID);
    }

    @Test
    @DisplayName("Delete all tutorials successfully")
    void testDeleteAll() {
        // Act
        assertDoesNotThrow(() -> tutorialService.deleteAll());

        // Assert
        verify(tutorialRepository, times(1)).deleteAll();
    }
}
