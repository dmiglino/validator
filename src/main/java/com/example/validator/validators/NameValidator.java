package com.example.validator.validators;

public class NameValidator implements Validator {
    @Override
    public boolean validate(String value) {
        return value != null && value.length() <= ValidatorUtils.NAME_MAX_LENGTH &&
            value.matches(ValidatorUtils.REGEX_LATIN_ALPHABET);
    }
}
