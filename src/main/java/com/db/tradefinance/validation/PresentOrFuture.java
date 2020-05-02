package com.db.tradefinance.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PresentOrFutureValidator.class)
@Documented
public @interface PresentOrFuture {
//    String message() default "{PresentOrFuture.message}";
    String message() default "{PresentOrFuture.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}