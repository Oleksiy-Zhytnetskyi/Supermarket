package zlagoda.zlagoda.validator.field;

public final class FieldValidatorsChainGenerator {

    private FieldValidatorsChainGenerator() {}

    public static AbstractFieldValidatorHandler getFieldValidatorChain() {
        AbstractFieldValidatorHandler emailFieldValidator = EmailValidator.getInstance();
        AbstractFieldValidatorHandler passwordFieldValidator = PasswordValidator.getInstance();
        AbstractFieldValidatorHandler nameFieldValidator = NameValidator.getInstance();
        AbstractFieldValidatorHandler surnameFieldValidator = SurnameValidator.getInstance();
        AbstractFieldValidatorHandler patronymicFieldValidator = PatronymicValidator.getInstance();
        AbstractFieldValidatorHandler phoneFieldValidator = PhoneValidator.getInstance();
        AbstractFieldValidatorHandler salaryFieldValidator = SalaryValidator.getInstance();
        AbstractFieldValidatorHandler cityFieldValidator = CityValidator.getInstance();
        AbstractFieldValidatorHandler streetFieldValidator = StreetValidator.getInstance();
        AbstractFieldValidatorHandler zipCodeFieldValidator = ZipCodeValidator.getInstance();
        AbstractFieldValidatorHandler priceFieldValidator = PriceValidator.getInstance();

        emailFieldValidator.setNextFieldValidator(passwordFieldValidator);
        passwordFieldValidator.setNextFieldValidator(nameFieldValidator);
        nameFieldValidator.setNextFieldValidator(surnameFieldValidator);
        surnameFieldValidator.setNextFieldValidator(patronymicFieldValidator);
        patronymicFieldValidator.setNextFieldValidator(phoneFieldValidator);
        phoneFieldValidator.setNextFieldValidator(salaryFieldValidator);
        salaryFieldValidator.setNextFieldValidator(cityFieldValidator);
        cityFieldValidator.setNextFieldValidator(streetFieldValidator);
        streetFieldValidator.setNextFieldValidator(zipCodeFieldValidator);
        zipCodeFieldValidator.setNextFieldValidator(priceFieldValidator);

        return emailFieldValidator;
    }
}
