package com.example.validator.service;

import com.example.validator.factory.ValidatorFactory;
import com.example.validator.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ValidatorService {
    private Map<String, Validator> validatorMap;

    public ValidatorService(ValidatorFactory validatorFactory) {
        validatorMap = validatorFactory.getValidationMap();
    }

    public boolean validateFields(String fieldName, String value) {
        Validator validator = validatorMap.get(fieldName);
        return validator != null ? validator.validate(value) : false;
    }
}
