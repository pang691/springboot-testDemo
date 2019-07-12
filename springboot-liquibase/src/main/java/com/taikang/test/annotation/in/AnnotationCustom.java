package com.taikang.test.annotation.in;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = ValidateByKu.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AnnotationCustom {

    String classified();

    String message() default "不是有效的数值";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
