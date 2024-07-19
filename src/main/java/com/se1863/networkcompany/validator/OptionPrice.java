package com.se1863.networkcompany.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionPriceValidator.class)
public @interface OptionPrice {
    String message() default "Option price must be a positive number.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
