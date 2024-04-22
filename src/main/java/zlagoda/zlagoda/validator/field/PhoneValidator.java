package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class PhoneValidator  extends AbstractFieldValidatorHandler {

    private static final String PHONE_REGEX = "^(\\+)?\\d{7,12}$";

    PhoneValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PhoneValidator INSTANCE = new PhoneValidator(FieldValidatorKey.PHONE);
    }

    public static PhoneValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.USER_PHONE_NULL_ERROR); }
        else if (!fieldValue.matches(PHONE_REGEX)) {
            errors.add(Message.USER_PHONE_INVALID_ERROR);
        }
    }
}
