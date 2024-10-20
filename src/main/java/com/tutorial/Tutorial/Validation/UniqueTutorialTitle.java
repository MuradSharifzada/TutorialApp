package com.tutorial.Tutorial.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UniqueTutorialTitleValidator.class)
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTutorialTitle {

    String message() default "Tutorial Title must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
