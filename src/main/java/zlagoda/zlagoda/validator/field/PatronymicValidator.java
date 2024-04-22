package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class PatronymicValidator extends AbstractFieldValidatorHandler {

    private static final String NAME_REGEX = "^[A-Za-zА-ЯІЇЄа-яіїє]+([\\s’'-][A-Za-zА-ЯІЇЄа-яіїє]+)*$";

    PatronymicValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PatronymicValidator INSTANCE = new PatronymicValidator(FieldValidatorKey.PATRONYMIC);
    }

    public static PatronymicValidator getInstance() {
        return PatronymicValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.USER_PATRONYMIC_NULL_ERROR); }
        else if (!fieldValue.matches(NAME_REGEX)) {
            errors.add(Message.USER_PATRONYMIC_INVALID_ERROR);
        }
    }
}
