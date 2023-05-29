package com.example.validator.validators;

public class PhoneValidator implements Validator {
    @Override
    public boolean validate(String value) {
        return value != null && value.matches(ValidatorUtils.REGEX_PHONE);
    }
}
