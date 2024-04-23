package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class SurnameValidator extends AbstractFieldValidatorHandler {

    private static final String SURNAME_REGEX = "^[A-Za-zА-ЯІЇЄа-яіїє]+([\\s’'-][A-Za-zА-ЯІЇЄа-яіїє]+)*$";

    SurnameValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final SurnameValidator INSTANCE = new SurnameValidator(FieldValidatorKey.SURNAME);
    }

    public static SurnameValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.SURNAME_NULL_ERROR); }
        else if (!fieldValue.matches(SURNAME_REGEX)) {
            errors.add(Message.SURNAME_INVALID_ERROR);
        }
    }
}
