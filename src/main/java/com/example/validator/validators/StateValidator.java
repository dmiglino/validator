package com.example.validator.validators;

public class StateValidator implements Validator {
    private static final int MAX_LENGTH = 255;
    private static final String LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS_REGEX = "^[a-zA-Z0-9 ]+$";

    @Override
    public boolean validate(String value) {
        return value != null && value.length() <= MAX_LENGTH && value.matches(LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS_REGEX);
    }

}
