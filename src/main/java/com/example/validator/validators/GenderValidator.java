package com.example.validator.validators;

public class GenderValidator implements Validator {
    private static final String ALLOWED_GENDERS_REGEX = "[M|F|U]";

    @Override
    public boolean validate(String value) {
        return value != null && value.matches(ALLOWED_GENDERS_REGEX);
    }
}
