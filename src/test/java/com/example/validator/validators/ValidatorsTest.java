package com.example.validator.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorsTest {

    @Test
    public void birthDateValidatorInvalidInputTest() {
        Validator birthDateValidator = new BirthDateValidator();
        assertTrue(birthDateValidator.validate("1984-01-01"));
    }

    @Test
    public void birthDateValidatorValidInputTest() {
        Validator birthDateValidator = new BirthDateValidator();
        assertFalse(birthDateValidator.validate("11-11-123"));
    }

    @Test
    public void nameValidatorValidInputTest() {
        Validator firstNameValidator = new NameValidator();
        assertTrue(firstNameValidator.validate("Diego"));
    }

    @Test
    public void nameValidatorInvalidInputTooLongTest() {
        Validator firstNameValidator = new NameValidator();
        assertFalse(firstNameValidator.validate("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tincidunt finibus efficitur."));
    }

    @Test
    public void nameValidatorInvalidInputNonLatinCharactersTest() {
        Validator firstNameValidator = new NameValidator();
        assertFalse(firstNameValidator.validate("Jøhn"));
    }

    @Test
    public void phoneValidatorValidInputTest() {
        Validator phoneValidator = new PhoneValidator();
        assertTrue(phoneValidator.validate("(123)456-7890"));
    }

    @Test
    public void phoneValidatorValidInputNoParenthesesTest() {
        Validator phoneValidator = new PhoneValidator();
        assertTrue(phoneValidator.validate("123-456-7890"));
    }

    @Test
    public void phoneValidatorInvalidInputInvalidFormatTest() {
        Validator phoneValidator = new PhoneValidator();
        assertFalse(phoneValidator.validate("1234567890"));
    }

    @Test
    public void addressValidatorValidInputTest() {
        Validator addressValidator = new AddressValidator();
        assertTrue(addressValidator.validate("123 Main Street"));
    }

    @Test
    public void addressValidatorInvalidInputInvalidCharactersTest() {
        Validator addressValidator = new AddressValidator();
        assertFalse(addressValidator.validate("123 Main Street #456"));
    }

    @Test
    public void addressValidatorInvalidInputTooLongTest() {
        Validator addressValidator = new AddressValidator();
        assertFalse(addressValidator.validate("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tincidunt finibus efficitur."));
    }

    @Test
    public void stateValidatorValidInputTest() {
        Validator stateValidator = new StateValidator();
        assertTrue(stateValidator.validate("California"));
    }

    @Test
    public void stateValidatorInvalidInputInvalidCharactersTest() {
        Validator stateValidator = new StateValidator();
        assertFalse(stateValidator.validate("New York!"));
    }

    @Test
    public void stateValidatorInvalidInputTooLongTest() {
        Validator stateValidator = new StateValidator();
        assertFalse(stateValidator.validate("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tincidunt finibus efficitur."));
    }

}
