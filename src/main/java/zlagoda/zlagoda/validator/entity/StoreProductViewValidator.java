package zlagoda.zlagoda.validator.entity;

import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorKey;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.StoreProductView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreProductViewValidator implements Validator<StoreProductView> {

    private static final AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorChain();
    private static final StoreProductService storeProductService = StoreProductService.getInstance();
    private static final ProductService productService = ProductService.getInstance();

    private static class Holder {
        static final StoreProductViewValidator INSTANCE = new StoreProductViewValidator();
    }

    public static StoreProductViewValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(StoreProductView view) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.PRICE, String.valueOf(view.getSellingPrice()), errors);

        checkProductQuantity(view, errors);
        checkProductDropdown(view, errors);
        checkIsPromotionalConstraints(view, errors);
        //checkPromotionalDropdown(view, errors);

        return errors;
    }

    private void checkPromotionalDropdown(StoreProductView view, List<String> errors) {
        if (view.getPromotionalId() != null && view.getProductId() != null) {
            ProductEntity product = productService.getProductById(view.getProductId()).get();
            StoreProductEntity promotionalProduct = storeProductService.getStoreProductById(view.getPromotionalId()).get();
            ProductEntity internalPromotionalProduct = productService.getProductById(promotionalProduct.getProductId()).get();

            if (!Objects.equals(product.getName(), internalPromotionalProduct.getName())) {
                errors.add(Message.PROMOTIONAL_PRODUCT_INVALID_ERROR);
            }
        }
    }

    private void checkIsPromotionalConstraints(StoreProductView view, List<String> errors) {
        if (view.getPromotionalId() != null && view.getPromotionalId() == 0 && view.getIsPromotional()) {
            errors.add(Message.IS_PROMOTIONAL_INVALID_ERROR);
        }
    }

    private void checkProductDropdown(StoreProductView view, List<String> errors) {
        if (productService.getProductById(view.getProductId()).isEmpty()) {
            errors.add(Message.PRODUCT_NULL_ERROR);
        }
    }

    private void checkProductQuantity(StoreProductView view, List<String> errors) {
        if (view.getProductQuantity() <= 0) {
            errors.add(Message.PRODUCT_QUANTITY_INVALID_ERROR);
        }
    }
}
