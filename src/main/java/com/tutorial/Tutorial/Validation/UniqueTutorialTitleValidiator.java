package com.tutorial.Tutorial.Validation;

import com.tutorial.Tutorial.Repository.TutorialRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueTutorialTitleValidiator implements ConstraintValidator<UniqueTitle, String> {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        if (title == null || title.trim().isEmpty()) {
            return true;
        }
        return !tutorialRepository.existsByTitle(title);
    }
}
