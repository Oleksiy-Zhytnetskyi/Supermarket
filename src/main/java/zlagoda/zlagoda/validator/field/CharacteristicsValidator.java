package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class CharacteristicsValidator extends AbstractFieldValidatorHandler {

    private static final String CHARACTERISTICS_REGEX = "^(.{1,500})$";

    CharacteristicsValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final CharacteristicsValidator INSTANCE = new CharacteristicsValidator(FieldValidatorKey.CHARACTERISTICS);
    }

    public static CharacteristicsValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isBlank()) { errors.add(Message.CHARACTERISTICS_NULL_ERROR); }
        else if (!fieldValue.matches(CHARACTERISTICS_REGEX)) {
            errors.add(Message.CHARACTERISTICS_INVALID_ERROR);
        }
    }
}
