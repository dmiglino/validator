package com.example.validator.beans;

import com.example.validator.constraints.Name;
import lombok.Data;

import javax.validation.constraints.*;

import static com.example.validator.validators.ValidatorUtils.REGEX_LATIN_ALPHABET;

@Data
public class Person {
    /*@NotBlank(message = "First name cannot be null or blank")
    @Size(min = 1, max = 255, message = "First name cannot be less than 1 char or greater than 255 chars")
    @Pattern(regexp = REGEX_LATIN_ALPHABET, message = "First name must not contain special characters")*/
    @Name(message = "First name cannot be null or blank or greater than 255 chars")
    private String firstName;

    @NotBlank(message = "Last name cannot be null or blank")
    @Size(min = 1, max = 255, message = "Last name cannot be less than 1 char or greater than 255 chars")
    @Pattern(regexp = REGEX_LATIN_ALPHABET, message = "Last name must not contain special characters")
    private String lastName;

    @Email
    private String email;
}
