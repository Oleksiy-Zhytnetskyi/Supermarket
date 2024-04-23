package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class SalaryValidator extends AbstractFieldValidatorHandler {

    //private static final String SALARY_REGEX = "^\\d{1,10}.\\d{1,10}$";
    private static final String SALARY_REGEX = "\\b\\d{1,13}(\\.\\d{1,10})?\\b";

    SalaryValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final SalaryValidator INSTANCE = new SalaryValidator(FieldValidatorKey.SALARY);
    }

    public static SalaryValidator getInstance() {
        return SalaryValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.SALARY_NULL_ERROR); }
        else if (!fieldValue.matches(SALARY_REGEX)) {
            errors.add(Message.SALARY_INVALID_ERROR);
        }
    }
}
