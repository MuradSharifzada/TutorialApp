package com.tutorial.Tutorial.Validation;

import com.tutorial.Tutorial.Repository.TutorialRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UniqueTutorialTitleValidator implements ConstraintValidator<UniqueTutorialTitle, String> {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        if (title == null || title.isEmpty()) {
            return false;
        }
        try {
            return !tutorialRepository.findByTitle(title);
        } catch (Exception e) {
            return false;
        }
    }
}
