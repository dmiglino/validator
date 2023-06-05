package com.example.validator.constraints;

import com.example.validator.validator.NameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Name {
    String message() default "{com.example.validator.constraints.message}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
