package com.example.validator.validators;

public class BirthDateValidator implements Validator {
    private static final String ISO8601_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    @Override
    public boolean validate(String value) {
        return value != null && value.matches(ISO8601_REGEX);
    }

}
