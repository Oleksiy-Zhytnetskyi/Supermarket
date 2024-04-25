package zlagoda.zlagoda.controller.command.store.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.view.ProductView;
import zlagoda.zlagoda.view.StoreProductView;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class PostUpdateStoreProductCommand implements Command {

//    private final StoreProductService storeProductService;
//    private final ProductService productService;
//    private final CategoryService categoryService;
//
//    public PostUpdateStoreProductCommand(StoreProductService storeProductService, ProductService productService, CategoryService categoryService) {
//        this.storeProductService = storeProductService;
//        this.productService = productService;
//        this.categoryService = categoryService;
//    }
//
//    @Override
//    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
//        StoreProductView storeProductView = getUserInput(req);
//        List<String> errors = validateUserInput(productView);
//
//        if (errors.isEmpty()) {
//            productService.updateProduct(productView);
//            redirectToAllCategoryPageWithSuccessMessage(req, resp);
//            return RedirectionManager.REDIRECTION;
//        }
//
//        addRequestAttributes(req, productView, errors);
//        return Page.VIEW_CATEGORY;
//    }
//
//    private StoreProductView getUserInput(HttpServletRequest req) throws ParseException {
//        return StoreProductView.builder()
//                .id(req.getParameter(Attribute.ID))
//                .sellingPrice(Double.valueOf(req.getParameter(Attribute.SELLING_PRICE)))
//                .productQuantity(Integer.valueOf(req.getParameter(Attribute.PRODUCT_QUANTITY)))
//                .isPromotional(Boolean.valueOf(req.getParameter(Attribute.IS_PROMOTIONAL)))
//                .promotionalView()
//                .build();
//    }
//
//    private List<String> validateUserInput(ProductView productView) {
////        return CategoryViewValidator.getInstance().validate(productView);
//        return  new ArrayList<>();
//    }
//
//    private void redirectToAllCategoryPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpWrapper httpWrapper = new HttpWrapper(request, response);
//        Map<String, String> urlParams = new HashMap<>();
//        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_PRODUCT_UPDATE);
//        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
//    }
//
//    private void addRequestAttributes(HttpServletRequest request, ProductView productView, List<String> errors) {
//        request.setAttribute(Attribute.PRODUCT_VIEW, productView);
//        request.setAttribute(Attribute.ERRORS, errors);
//        request.setAttribute("update", true);
//    }
//}
