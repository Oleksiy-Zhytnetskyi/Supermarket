package zlagoda.zlagoda.validator.entity;

import lombok.NoArgsConstructor;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.view.ProductView;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProductViewValidator implements Validator<ProductView> {

    private static class Holder {
        static final ProductViewValidator INSTANCE = new ProductViewValidator();
    }

    public static ProductViewValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(ProductView view) {
        List<String> errors = new ArrayList<>();

        checkProductName(view, errors);
        checkCharacteristics(view, errors);
        checkCategory(view, errors);

        return errors;
    }

    private void checkProductName(ProductView view, List<String> errors) {
        if (view.getName().isBlank()) {
            errors.add(Message.NAME_NULL_ERROR);
        }
        else if (view.getName().length() > 50) {
            errors.add(Message.NAME_INVALID_ERROR);
        }
    }

    private void checkCharacteristics(ProductView view, List<String> errors) {
        if (view.getCharacteristics().isBlank()) {
            errors.add(Message.CHARACTERISTICS_NULL_ERROR);
        }
        else if (view.getCharacteristics().length() > 500) {
            errors.add(Message.CHARACTERISTICS_INVALID_ERROR);
        }
    }

    private void checkCategory(ProductView view, List<String> errors) {
        if (CategoryService.getInstance().getCategoryById(view.getCategoryId()).isEmpty()) {
            errors.add(Message.CATEGORY_NULL_ERROR);
        }
    }
}
