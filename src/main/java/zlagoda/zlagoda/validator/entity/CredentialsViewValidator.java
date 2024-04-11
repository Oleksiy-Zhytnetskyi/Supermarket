package zlagoda.zlagoda.validator.entity;

import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorKey;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.CredentialsView;

import java.util.ArrayList;
import java.util.List;

public class CredentialsViewValidator implements Validator<CredentialsView>{

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorChain();

    private static class Holder {
        static final CredentialsViewValidator INSTANCE = new CredentialsViewValidator();
    }

    public static CredentialsViewValidator getInstance() { return Holder.INSTANCE; }

    @Override
    public List<String> validate(CredentialsView view) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.EMAIL, view.getEmail(), errors);
        fieldValidator.validateField(FieldValidatorKey.PASSWORD, view.getPassword(), errors);

        return errors;
    }
}
