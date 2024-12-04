package com.tutorial.Tutorial.validation;

import com.tutorial.Tutorial.repository.TutorialRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueTutorialTitleValidiator implements ConstraintValidator<UniqueTitle, String> {
    private final TutorialRepository tutorialRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        if (title == null || title.trim().isEmpty()) {
            return true;
        }
        boolean exists = tutorialRepository.existsByTitle(title);
        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("This title already exists please use other title")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
