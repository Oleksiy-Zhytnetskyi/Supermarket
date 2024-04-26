package zlagoda.zlagoda.validator.field;

import zlagoda.zlagoda.locale.Message;

import java.util.List;

public class PriceValidator extends AbstractFieldValidatorHandler {

    private static final String PRICE_REGEX = "\\b\\d{1,13}(\\.\\d{1,10})?\\b";

    PriceValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PriceValidator INSTANCE = new PriceValidator(FieldValidatorKey.PRICE);
    }

    public static PriceValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isBlank()) { errors.add(Message.PRICE_NULL_ERROR); }
        else if (!fieldValue.matches(PRICE_REGEX)) {
            errors.add(Message.PRICE_INVALID_ERROR);
        }
        else if (Double.parseDouble(fieldValue) <= 0) {
            errors.add(Message.PRICE_INVALID_ERROR);
        }
    }
}
