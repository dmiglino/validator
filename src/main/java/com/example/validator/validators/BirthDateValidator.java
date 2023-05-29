package com.example.validator.validators;

public class BirthDateValidator implements Validator {
    @Override
    public boolean validate(String value) {
        return value != null && value.matches(ValidatorUtils.REGEX_ISO8601_DATE);
    }
}
