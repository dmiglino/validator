package com.example.validator.factory;

import com.example.validator.validators.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidatorFactory {

    private Map<String, Validator> validationMap;

    public ValidatorFactory() {
        validationMap = createValidatorMap();
    }
    public Map<String, Validator> createValidatorMap() {
        Map<String, Validator> validatorMap = new HashMap<>();

        validatorMap.put(ValidatorFields.FIRST_NAME, new NameValidator());
        validatorMap.put(ValidatorFields.MID_NAME, new NameValidator());
        validatorMap.put(ValidatorFields.LAST_NAME, new NameValidator());
        validatorMap.put(ValidatorFields.GENDER, new GenderValidator());
        validatorMap.put(ValidatorFields.ADDRESS, new AddressValidator());
        validatorMap.put(ValidatorFields.BIRTH_DATE, new BirthDateValidator());
        validatorMap.put(ValidatorFields.STATE, new StateValidator());

        return validatorMap;
    }

    public Map<String, Validator> getValidationMap() {
        return validationMap;
    }

    public Validator getValidator(String validatorName) {
        return validationMap.get(validatorName);
    }
}
