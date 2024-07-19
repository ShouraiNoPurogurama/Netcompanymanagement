package com.se1863.networkcompany.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.se1863.networkcompany.service.IPattern;

public class CustomEntityIDValidator implements ConstraintValidator<CustomEntityID, String>, IPattern {

    private String idPat;

    @Override
    public void initialize(CustomEntityID constraintAnnotation) {
        idPat = constraintAnnotation.idPat();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value!=null && value.matches(idPat);
    }
    
}
