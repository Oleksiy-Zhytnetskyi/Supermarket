package zlagoda.zlagoda.controller.command.sale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.SaleService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.validator.entity.SaleViewValidator;
import zlagoda.zlagoda.view.SaleView;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostCreateSaleCommand implements Command {

    private static final SaleService saleService = SaleService.getInstance();
    private static final StoreProductService storeProductService = StoreProductService.getInstance();
    private static final ProductService productService = ProductService.getInstance();
    private static final ReceiptService receiptService = ReceiptService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        SaleView saleView = getUserInput(req);
        List<String> errors = validateUserInput(saleView);

        if (errors.isEmpty()) {
            saleService.createSale(saleView);
            redirectToAllProductsPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, saleView, errors);
        return Page.CREATE_SALE;
    }

    private SaleView getUserInput(HttpServletRequest req) throws ParseException {
        SaleView.SaleViewBuilder builder = SaleView.builder();
        try {
            builder.productQuantity(Integer.valueOf(req.getParameter(Attribute.PRODUCT_QUANTITY)));
        }
        catch (NumberFormatException e) {
            builder.productQuantity(0);
        }
        SaleEntityComplexKey saleEntityComplexKey = new SaleEntityComplexKey();
        if(!req.getParameter(Attribute.RECEIPT).equals("null")) {
            saleEntityComplexKey.setReceiptId(Integer.valueOf(req.getParameter(Attribute.RECEIPT)));
        }
        if(!req.getParameter(Attribute.STORE_PRODUCT_ID).equals("null")) {
            saleEntityComplexKey.setUPC(Integer.valueOf(req.getParameter(Attribute.STORE_PRODUCT_ID)));
        }
        builder.pk(saleEntityComplexKey);
        return builder.build();
    }

    private List<String> validateUserInput(SaleView saleView) {
        return SaleViewValidator.getInstance().validate(saleView);
    }

    private void redirectToAllProductsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_SALE_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_SALES, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, SaleView saleView, List<String> errors) {
        List<StoreProductEntity> storeProducts = storeProductService.getAllStoreProducts();
        List<ProductEntity> products = productService.getAllProducts();
        List<ReceiptEntity> receipts = receiptService.getAllReceipts();
        request.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        request.setAttribute(Attribute.PRODUCTS, products);
        request.setAttribute(Attribute.RECEIPTS, receipts);
        request.setAttribute(Attribute.SALE_VIEW, saleView);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}

