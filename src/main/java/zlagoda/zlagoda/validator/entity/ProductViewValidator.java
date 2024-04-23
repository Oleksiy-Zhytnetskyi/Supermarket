package zlagoda.zlagoda.validator.entity;

import lombok.NoArgsConstructor;
import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.ProductView;

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
        return List.of();
    }
}
