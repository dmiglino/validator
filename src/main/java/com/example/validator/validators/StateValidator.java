package com.example.validator.validators;

public class StateValidator implements Validator {
    @Override
    public boolean validate(String value) {
        return value != null && value.length() <= ValidatorUtils.STATE_MAX_LENGTH &&
            value.matches(ValidatorUtils.REGEX_LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS);
    }
}
