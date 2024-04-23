package zlagoda.zlagoda.validator.entity;

import lombok.NoArgsConstructor;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorKey;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.ProductView;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProductViewValidator implements Validator<ProductView> {

    private final AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorChain();

    private static class Holder {
        static final ProductViewValidator INSTANCE = new ProductViewValidator();
    }

    public static ProductViewValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(ProductView view) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.NAME, view.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.CHARACTERISTICS, view.getCharacteristics(), errors);
        checkCategory(view, errors);

        return errors;
    }

    private void checkCategory(ProductView view, List<String> errors) {
        if (CategoryService.getInstance().getCategoryById(view.getCategoryId()).isEmpty()) {
            errors.add(Message.CATEGORY_NULL_ERROR);
        }
    }
}
