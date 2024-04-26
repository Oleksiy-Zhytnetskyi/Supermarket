package zlagoda.zlagoda.validator.entity;

import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorKey;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.CardView;

import java.util.ArrayList;
import java.util.List;

public class CardViewValidator implements Validator<CardView> {

    private static final AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorChain();

    private static class Holder {
        static final CardViewValidator INSTANCE = new CardViewValidator();
    }

    public static CardViewValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(CardView view) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.NAME, view.getCustomerName(), errors);
        fieldValidator.validateField(FieldValidatorKey.SURNAME, view.getCustomerSurname(), errors);
        fieldValidator.validateField(FieldValidatorKey.PATRONYMIC, view.getCustomerPatronymic(), errors);
        fieldValidator.validateField(FieldValidatorKey.PHONE, view.getPhoneNumber(), errors);
        fieldValidator.validateField(FieldValidatorKey.CITY, view.getCity(), errors);
        fieldValidator.validateField(FieldValidatorKey.STREET, view.getStreet(), errors);
        fieldValidator.validateField(FieldValidatorKey.ZIP_CODE, view.getZipCode(), errors);

        checkPercent(view, errors);

        return errors;
    }

    private void checkPercent(CardView view, List<String> errors) {
        if (view.getPercent() <= 0 || view.getPercent() > 30) {
            errors.add(Message.PERCENT_INVALID_ERROR);
        }
    }
}
