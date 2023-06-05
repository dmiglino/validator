package com.example.validator.factory;

import com.example.validator.validators.GenderValidator;
import com.example.validator.validators.NameValidator;
import com.example.validator.validators.Validator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorFactoryTest {

    @Test
    public void getValidationFieldsTest() {
        ValidatorFields fields = new ValidatorFields();
        assertEquals(ValidatorFields.LAST_NAME, "Last_Name");
    }

    @Test
    public void getValidationMapTest() {
        ValidatorFactory factory = new ValidatorFactory();
        Map<String, Validator> validationMap = factory.getValidationMap();
        assertNotNull(validationMap);
        assertTrue(validationMap.containsKey(ValidatorFields.LAST_NAME));
        assertFalse(validationMap.containsKey("Invalid"));
    }

    @Test
    public void getValidatorFirstNameTest() {
        ValidatorFactory factory = new ValidatorFactory();
        Validator validator = factory.getValidator(ValidatorFields.FIRST_NAME);
        assertNotNull(validator);
        assertTrue(validator instanceof NameValidator);
    }

    @Test
    public void getValidatorGenderTest() {
        ValidatorFactory factory = new ValidatorFactory();
        Validator validator = factory.getValidator(ValidatorFields.GENDER);
        assertNotNull(validator);
        assertTrue(validator instanceof GenderValidator);
    }

    @Test
    public void getValidatorInvalidValidatorNameTest() {
        ValidatorFactory factory = new ValidatorFactory();
        Validator validator = factory.getValidator("InvalidValidator");
        assertNull(validator);
    }

}
