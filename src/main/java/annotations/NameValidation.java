package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidationValidator.class)
public @interface NameValidation {
    String message() default "1文字以上30文字以下である必要があります";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
