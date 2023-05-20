package com.example.pictgram.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/*独自バリデーションのひな形*/
@Documented
/*ImageByteValidatorクラスの制約*/
@Constraint(validatedBy = ImageByteValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface ImageByte {

    String message() default "{com.example.pictgram.validation.constraints.ImageSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max();
}
