package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class EmailValidator extends AbstractFieldValidatorHandler {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w-]+)*@[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\.[A-Za-z]{2,})$";

    EmailValidator(FieldValidatorKey fieldValidatorKey) {
         super(fieldValidatorKey);
    }

    private static class Holder {
        static final EmailValidator INSTANCE = new EmailValidator(FieldValidatorKey.EMAIL);
    }

    public static EmailValidator getInstance() { return Holder.INSTANCE; }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isBlank()) {
            errors.add(Message.EMAIL_NULL_ERROR);
        }
        else if (!fieldValue.matches(EMAIL_REGEX)) {
            errors.add(Message.EMAIL_INVALID_ERROR);
        }
    }
}
