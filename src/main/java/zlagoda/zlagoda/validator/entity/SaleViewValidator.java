package zlagoda.zlagoda.validator.entity;

import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.view.SaleView;

import java.util.ArrayList;
import java.util.List;

public class SaleViewValidator implements Validator<SaleView> {

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

        checkProductQuantity(view, errors);
        checkStoreProduct(view, errors);
        checkReceipt(view, errors);

        return errors;
    }

    private void checkReceipt(SaleView view, List<String> errors) {
        if (view.getPk().getReceiptId() == null || receiptService.getReceiptById(view.getPk().getReceiptId()).isEmpty()) {
            errors.add(Message.RECEIPT_NULL_ERROR);
        }
    }

    private void checkStoreProduct(SaleView view, List<String> errors) {
        if (view.getPk().getUPC() == null || storeProductService.getStoreProductById(view.getPk().getUPC()).isEmpty()) {
            errors.add(Message.PRODUCT_NULL_ERROR);
        }
    }

    private void checkProductQuantity(SaleView view, List<String> errors) {
        if (view.getProductQuantity() <= 0) {
            errors.add(Message.PRODUCT_QUANTITY_INVALID_ERROR);
        }
        else if (view.getPk().getUPC() != null && storeProductService.getStoreProductById(view.getPk().getUPC()).isPresent()) {
            StoreProductEntity storeProduct = storeProductService.getStoreProductById(view.getPk().getUPC()).get();
            if (view.getProductQuantity() > storeProduct.getProductQuantity()) {
                errors.add(Message.PRODUCT_QUANTITY_UNAVAILABLE_ERROR);
            }
        }
    }
}
