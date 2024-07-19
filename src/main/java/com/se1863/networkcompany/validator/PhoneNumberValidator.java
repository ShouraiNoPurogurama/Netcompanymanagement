package com.se1863.networkcompany.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    String phonePat = "[0]{1}[1-9]{1}[0-9]{8}";
    Pattern pat = Pattern.compile(phonePat);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher mat = pat.matcher(value);
        return mat.find();
    }
    
}
