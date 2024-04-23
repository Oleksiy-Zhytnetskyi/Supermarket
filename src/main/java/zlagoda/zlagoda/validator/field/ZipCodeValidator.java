package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class ZipCodeValidator extends AbstractFieldValidatorHandler {

    private static final String ZIP_CODE_REGEX = "^\\d{5}$";

    ZipCodeValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final ZipCodeValidator INSTANCE = new ZipCodeValidator(FieldValidatorKey.ZIP_CODE);
    }

    public static ZipCodeValidator getInstance() {
        return ZipCodeValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isBlank()) { errors.add(Message.ZIP_CODE_NULL_ERROR); }
        else if (!fieldValue.matches(ZIP_CODE_REGEX)) {
            errors.add(Message.ZIP_CODE_INVALID_ERROR);
        }
    }
}
