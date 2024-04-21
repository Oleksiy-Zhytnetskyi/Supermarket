package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class CityValidator extends AbstractFieldValidatorHandler {

    private static final String CITY_REGEX = "^[A-Za-zА-ЯІЇЄа-яіїє]+([\\s’'-][A-Za-zА-ЯІЇЄа-яіїє]+)*$";

    CityValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final CityValidator INSTANCE = new CityValidator(FieldValidatorKey.CITY);
    }

    public static CityValidator getInstance() {
        return CityValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.USER_CITY_NULL_ERROR); }
        else if (!fieldValue.matches(CITY_REGEX)) {
            errors.add(Message.USER_CITY_INVALID_ERROR);
        }
    }
}
