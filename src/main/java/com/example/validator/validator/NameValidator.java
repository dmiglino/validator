package com.example.validator.validator;

import com.example.validator.constraints.Name;
import com.example.validator.validators.ValidatorUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {
    @Override
    public boolean isValid(String strValue, ConstraintValidatorContext constraintValidatorContext) {
        return strValue != null && strValue.length() <= ValidatorUtils.NAME_MAX_LENGTH &&
                strValue.matches(ValidatorUtils.REGEX_LATIN_ALPHABET);
    }
}
