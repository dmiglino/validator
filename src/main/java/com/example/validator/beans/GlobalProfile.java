package com.example.validator.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import javax.validation.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class GlobalProfile {
    /*@JsonIgnoreProperties
    Validator validator;*/

    //@NotNull annotation is needed to invoke validations on custom java objects with @Valid annotation
    @Valid
    @NotNull
    private Person person;

    @Valid
    @NotNull
    private Address address;

    //If you need to invoke spring validations manually then it can done using a default validator as below. This code
    //can go in Constructor or any service method.
    //This can also be called by a method with name "isValid" and annotate it with @AssertTrue as below.
    /*public GlobalProfile() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Validated
    @AssertTrue(message = "ValidateInput method failed")
    public boolean isValid() {
        System.out.println("validateInput method called..");
        Set<ConstraintViolation<Object>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return true;
    }*/

}
