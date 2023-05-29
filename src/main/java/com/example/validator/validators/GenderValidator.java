package com.example.validator.validators;

public class GenderValidator implements Validator {
    @Override
    public boolean validate(String value) {
        return value != null && value.matches(ValidatorUtils.REGEX_ALLOWED_GENDERS);
    }
}
