package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    String message() default "Invalid age. Age must be between 16 and 100.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}