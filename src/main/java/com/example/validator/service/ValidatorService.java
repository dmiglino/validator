package com.example.validator.service;

import com.example.validator.factory.ValidatorFactory;
import com.example.validator.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ValidatorService {

    private Map<String, Validator> validatorMap;

    public ValidatorService(ValidatorFactory validatorFactory) {
        validatorMap = validatorFactory.createValidatorMap();
    }

    public boolean validateFields(String fieldName, String value) {
        return Optional.ofNullable(validatorMap.get(fieldName))
                .map(validator -> validator.validate(value))
                .orElse(false);
    }

}
