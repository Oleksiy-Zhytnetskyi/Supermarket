package zlagoda.zlagoda.controller.command.store.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.validator.entity.StoreProductValidator;
import zlagoda.zlagoda.view.ProductView;
import zlagoda.zlagoda.view.StoreProductView;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class PostUpdateStoreProductCommand implements Command {

    private final StoreProductService storeProductService = StoreProductService.getInstance();
    private final ProductService productService = ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        StoreProductView storeProductView = getUserInput(req);
        List<String> errors = validateUserInput(storeProductView);

        if (errors.isEmpty()) {
            storeProductService.updateStoreProduct(storeProductView);
            redirectToAllCategoryPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, storeProductView, errors);
        return Page.ADD_STORE_PRODUCT;
    }

    private StoreProductView getUserInput(HttpServletRequest req) throws ParseException {
        System.out.println(req.getParameter(Attribute.IS_PROMOTIONAL));
        System.out.println(Boolean.parseBoolean(req.getParameter(Attribute.IS_PROMOTIONAL)));
        StoreProductView.StoreProductViewBuilder builder = StoreProductView.builder()
                .id(Integer.valueOf(req.getParameter(Attribute.ID)))
                .isPromotional(Boolean.valueOf(req.getParameter(Attribute.IS_PROMOTIONAL)));

        try {
            builder.sellingPrice(Double.parseDouble(req.getParameter(Attribute.SELLING_PRICE)));
        }
        catch (NumberFormatException e) {
            builder.sellingPrice(0.0);
        }

        try {
            builder.productQuantity(Integer.parseInt(req.getParameter(Attribute.PRODUCT_QUANTITY)));
        }
        catch (NumberFormatException e) {
            builder.productQuantity(0);
        }

        try {
            builder.productId(Integer.parseInt(req.getParameter(Attribute.PRODUCT_ID)));
        }
        catch (NumberFormatException e) {
            builder.productId(0);
        }

        if (!req.getParameter(Attribute.SALE_STORE_PRODUCT_ID).equals("null")) {
            if (!Boolean.parseBoolean(req.getParameter(Attribute.IS_PROMOTIONAL))) {
                builder.promotionalId(Integer.valueOf(req.getParameter(Attribute.SALE_STORE_PRODUCT_ID)));
            }
            else {
                builder.promotionalId(0);
            }
        }

        return builder.build();
    }

    private List<String> validateUserInput(StoreProductView storeProductView) {
        return StoreProductValidator.getInstance().validate(storeProductView);
    }

    private void redirectToAllCategoryPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_STORE_PRODUCT_UPDATE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, StoreProductView storeProductView, List<String> errors) {
        List<StoreProductEntity> saleStoreProducts = new ArrayList<>();
        storeProductService.getAllStoreProducts().stream().forEach(
                storeProduct -> {
                    if (storeProduct.getIsPromotional() /*&& Objects.equals(storeProduct.getProductId(), storeProductView.getProductId())*/) saleStoreProducts.add(storeProduct);
                });

        request.setAttribute(Attribute.PRODUCTS, productService.getAllProducts());
        request.setAttribute(Attribute.SALE_STORE_PRODUCTS, saleStoreProducts);
        request.setAttribute(Attribute.STORE_PRODUCT_VIEW, storeProductView);
        request.setAttribute(Attribute.ERRORS, errors);
        request.setAttribute("update", true);
    }
}
