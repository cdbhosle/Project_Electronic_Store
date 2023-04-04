package com.shruteekatech.electronic.store.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    //error msg
    String message() default "Invalid Image Name!!";
    //represent group of constraints
    Class<?>[] groups() default { };
    //additional info abt annotation
    Class<? extends Payload>[] payload() default {};
}
