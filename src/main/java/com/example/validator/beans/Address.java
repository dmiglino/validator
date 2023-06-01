package com.example.validator.beans;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.example.validator.validators.ValidatorUtils.REGEX_ALLOWED_PHONE_TYPES;
import static com.example.validator.validators.ValidatorUtils.REGEX_LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS;

@Data
public class Address {
    @NotBlank(message = "Address line1 cannot be null or blank")
    @Pattern(regexp = REGEX_LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS, message = "Address line1 must not contain special characters")
    private String addressLine1;

    @NotBlank(message = "Address line2 cannot be null or blank")
    @Pattern(regexp = REGEX_LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS, message = "Address line2 must not contain special characters")
    private String addressLine2;

    @NotBlank(message = "Phone cannot be null or blank")
    @Pattern(regexp = REGEX_ALLOWED_PHONE_TYPES, message = "Phone cannot be null or blank and must contain HOME|WORK")
    private String phone;
}
