package com.tutorial.Tutorial.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueTutorialTitleValidiator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTitle {
    String message() default "Title name must be unique, this title name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

