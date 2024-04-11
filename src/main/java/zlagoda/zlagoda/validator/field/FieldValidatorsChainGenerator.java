package zlagoda.zlagoda.validator.field;

public final class FieldValidatorsChainGenerator {

    private FieldValidatorsChainGenerator() {}

    public static AbstractFieldValidatorHandler getFieldValidatorChain() {
        AbstractFieldValidatorHandler emailFieldValidator = EmailValidator.getInstance();
        AbstractFieldValidatorHandler passwordFieldValidator = PasswordValidator.getInstance();

        return emailFieldValidator;
    }
}
