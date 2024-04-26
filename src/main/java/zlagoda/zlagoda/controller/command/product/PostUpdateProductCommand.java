package zlagoda.zlagoda.controller.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.validator.entity.ProductViewValidator;
import zlagoda.zlagoda.view.ProductView;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateProductCommand implements Command {

    private static final ProductService productService = ProductService.getInstance();
    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        ProductView productView = getUserInput(req);
        List<String> errors = validateUserInput(productView);

        if (errors.isEmpty()) {
            productService.updateProduct(productView);
            redirectToAllProductsPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, productView, errors);
        return Page.VIEW_PRODUCT;
    }

    private ProductView getUserInput(HttpServletRequest req) throws ParseException {
        ProductView.ProductViewBuilder result = ProductView.builder()
                .id(Integer.valueOf(req.getParameter(Attribute.ID)))
                .name(req.getParameter(Attribute.NAME))
                .characteristics(req.getParameter(Attribute.CHARACTERISTICS));
        if (!req.getParameter(Attribute.CATEGORY).equals("Choose a category")) {
            result.categoryId(Integer.parseInt(req.getParameter(Attribute.CATEGORY)));
        }
        else {
            result.categoryId(-1);
        }
        return result.build();
    }

    private List<String> validateUserInput(ProductView productView) {
        return ProductViewValidator.getInstance().validate(productView);
    }

    private void redirectToAllProductsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_PRODUCT_UPDATE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, ProductView productView, List<String> errors) {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        request.setAttribute(Attribute.CATEGORIES, categories);
        request.setAttribute(Attribute.PRODUCT_VIEW, productView);
        request.setAttribute(Attribute.ERRORS, errors);
        request.setAttribute("update", true);
    }
}


