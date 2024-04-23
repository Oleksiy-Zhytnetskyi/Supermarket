package zlagoda.zlagoda.validator.entity;

import lombok.NoArgsConstructor;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorKey;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.UserView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserViewValidator implements Validator<UserView> {
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,14}$";

    private final AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorChain();

    private static class Holder {
        static final UserViewValidator INSTANCE = new UserViewValidator();
    }

    public static UserViewValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(UserView view) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.NAME, view.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.SURNAME, view.getSurname(), errors);
        fieldValidator.validateField(FieldValidatorKey.PATRONYMIC, view.getPatronymic(), errors);
        fieldValidator.validateField(FieldValidatorKey.PHONE, view.getPhone(), errors);
        fieldValidator.validateField(FieldValidatorKey.SALARY, String.valueOf(view.getSalary()), errors);
        fieldValidator.validateField(FieldValidatorKey.CITY, view.getCity(), errors);
        fieldValidator.validateField(FieldValidatorKey.STREET, view.getStreet(), errors);
        fieldValidator.validateField(FieldValidatorKey.ZIP_CODE, view.getZipCode(), errors);
        fieldValidator.validateField(FieldValidatorKey.EMAIL, view.getEmail(), errors);

        checkPassword(view, errors);
        checkRole(view, errors);
        checkDateOfBirth(view, errors);
        checkStartDate(view, errors);

        return errors;
    }

    private void checkPassword(UserView view, List<String> errors) {
        if(view.getPassword().isBlank()) errors.add(Message.PASSWORD_NULL_ERROR);
        else if (!view.getPassword().matches(PASSWORD_REGEX)) {
            errors.add(Message.PASSWORD_INVALID_ERROR);
        }
    }

    private void checkRole(UserView view, List<String> errors) {
        if(view.getRole() == null) errors.add(Message.ROLE_NULL_ERROR);
    }

    private void checkDateOfBirth(UserView view, List<String> errors) {
        System.out.println("today--> " +LocalDate.now().getYear());
        System.out.println("Birth-->" + view.getDateOfBirth().getYear());
        if(view.getDateOfBirth() == null) errors.add(Message.DATE_OF_BIRTH_NULL_ERROR);
        else if (LocalDate.now().getYear() - view.getDateOfBirth().getYear() < 18) {
            errors.add(Message.DATE_OF_BIRTH_INVALID_ERROR);
        }
    }

    private void checkStartDate(UserView view, List<String> errors) {
        System.out.println("Start-->" + view.getStartDate().getYear());
        System.out.println("Birth-->" + view.getDateOfBirth().getYear());
        if(view.getStartDate() == null) errors.add(Message.START_DATE_NULL_ERROR);
        else if (view.getStartDate().getYear() - view.getDateOfBirth().getYear() < 18) {
            errors.add(Message.START_DATE_INVALID_ERROR);
        }
    }
}
