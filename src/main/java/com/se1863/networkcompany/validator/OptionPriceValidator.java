package com.se1863.networkcompany.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionPriceValidator implements ConstraintValidator<OptionPrice, Integer> {
    
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value > 1000;
    }
    
}
