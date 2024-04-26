package zlagoda.zlagoda.validator.entity;

import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.validator.field.AbstractFieldValidatorHandler;
import zlagoda.zlagoda.validator.field.FieldValidatorKey;
import zlagoda.zlagoda.validator.field.FieldValidatorsChainGenerator;
import zlagoda.zlagoda.view.SaleView;

import java.util.ArrayList;
import java.util.List;

public class SaleViewValidator implements Validator<SaleView> {

    private static final AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorChain();
    private static final StoreProductService storeProductService = StoreProductService.getInstance();
    private static final ReceiptService receiptService = ReceiptService.getInstance();

    private static class Holder {
        static final SaleViewValidator INSTANCE = new SaleViewValidator();
    }

    public static SaleViewValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(SaleView view) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.PRICE, String.valueOf(view.getSellingPrice()), errors);

        checkProductQuantity(view, errors);
        checkStoreProduct(view, errors);
        checkReceipt(view, errors);

        return errors;
    }

    private void checkReceipt(SaleView view, List<String> errors) {
        if (receiptService.getReceiptById(view.getPk().getUPC()).isEmpty()) {
            errors.add(Message.RECEIPT_NULL_ERROR);
        }
    }

    private void checkStoreProduct(SaleView view, List<String> errors) {
        if (storeProductService.getStoreProductById(view.getPk().getReceiptId()).isEmpty()) {
            errors.add(Message.PRODUCT_NULL_ERROR);
        }
    }

    private void checkProductQuantity(SaleView view, List<String> errors) {
        if (view.getProductQuantity() <= 0) {
            errors.add(Message.PRODUCT_QUANTITY_INVALID_ERROR);
        }
    }
}
