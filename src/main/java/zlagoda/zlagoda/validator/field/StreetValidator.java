package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class StreetValidator extends AbstractFieldValidatorHandler {

    private static final String STREET_REGEX = "^[A-Za-zА-ЯІЇЄа-яіїє\\d](?=.*[a-zA-ZА-ЯІЇЄа-яіїє]{2,99})[a-zA-ZА-ЯІЇЄа-яіїє\\d\\s/\\.’'-,]*$";

    StreetValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final StreetValidator INSTANCE = new StreetValidator(FieldValidatorKey.STREET);
    }

    public static StreetValidator getInstance() {
        return StreetValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.STREET_NULL_ERROR); }
        else if (!fieldValue.matches(STREET_REGEX)) {
            errors.add(Message.STREET_INVALID_ERROR);
        }
    }
}
