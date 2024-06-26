package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class NameValidator extends AbstractFieldValidatorHandler {

    private static final String NAME_REGEX = "^[A-Za-zА-ЯІЇЄа-яіїє]+([\\s’'-][A-Za-zА-ЯІЇЄа-яіїє]+)*$";

    NameValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final NameValidator INSTANCE = new NameValidator(FieldValidatorKey.NAME);
    }

    public static NameValidator getInstance() {
        return NameValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.NAME_NULL_ERROR); }
        else if (!fieldValue.matches(NAME_REGEX)) {
            errors.add(Message.NAME_INVALID_ERROR);
        }
    }
}
