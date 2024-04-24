package zlagoda.zlagoda.validator.field;

import lombok.Setter;

import java.util.List;

public abstract class AbstractFieldValidatorHandler {

    private final FieldValidatorKey fieldValidatorKey;
    @Setter
    private AbstractFieldValidatorHandler nextFieldValidator;

    public AbstractFieldValidatorHandler(FieldValidatorKey fieldValidatorKey) {
        this.fieldValidatorKey = fieldValidatorKey;
    }

    public void validateField(FieldValidatorKey fieldValidatorKey, String fieldValue, List<String> errors) {
        if (fieldValidatorKey.equals(this.fieldValidatorKey)) {
            validateField(fieldValue, errors);
        }
        if (nextFieldValidator != null) {
            nextFieldValidator.validateField(fieldValidatorKey, fieldValue, errors);
        }
    }

    abstract void validateField(String fieldValue, List<String> errors);
}
