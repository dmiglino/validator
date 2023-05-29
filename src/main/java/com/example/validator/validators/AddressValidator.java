package com.example.validator.validators;

public class AddressValidator implements Validator {
    @Override
    public boolean validate(String value) {
        return value != null && value.length() <= ValidatorUtils.ADDRESS_MAX_LENGTH &&
            value.matches(ValidatorUtils.REGEX_LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS);
    }
}
