package com.example.validator.validators;

public class PhoneValidator implements Validator {
    private static final String PHONE_REGEX = "\\(\\d{3}\\)\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";

    @Override
    public boolean validate(String value) {
        return value != null && value.matches(PHONE_REGEX);
    }

}
