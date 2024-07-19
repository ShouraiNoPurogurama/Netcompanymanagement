package com.se1863.networkcompany.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomEntityIDValidator.class)
public @interface CustomEntityID {
    String message() default "Invalid ID.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String idPat();
}
