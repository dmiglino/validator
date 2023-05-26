package com.example.validator.service;

import com.example.validator.factory.ValidatorFields;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig
@SpringBootTest
public class ValidatorServiceTest {

    @Autowired
    private ValidatorService validationService;

    @Test
    public void testValidateFields_ValidInput() {
        assertTrue(validationService.validateFields(ValidatorFields.FIRST_NAME, "Diego"));
    }

    @Test
    public void testValidateFields_InvalidInput() {
        assertFalse(validationService.validateFields(ValidatorFields.FIRST_NAME, "1234"));
    }

    @Test
    public void testValidateFields_UnknownValidator() {
        assertFalse(validationService.validateFields("Unknown_Field", "value"));
    }

}
