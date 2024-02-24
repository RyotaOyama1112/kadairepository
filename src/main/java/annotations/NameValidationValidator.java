package annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidationValidator implements ConstraintValidator<NameValidation, String> {

    @Override
    public void initialize(NameValidation nameValidation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() >= 1 && value.length() <= 30;
    }
}
