package zlagoda.zlagoda.validator.entity;

import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorKey;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.CategoryView;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewValidator implements Validator<CategoryView> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorChain();

    CategoryViewValidator() {
    }

    private static class Holder {
        static final CategoryViewValidator INSTANCE = new CategoryViewValidator();
    }

    public static CategoryViewValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(CategoryView view) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.NAME, view.getName(), errors);

        return errors;
    }
}
