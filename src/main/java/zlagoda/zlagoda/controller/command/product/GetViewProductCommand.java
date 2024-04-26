package zlagoda.zlagoda.controller.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.ProductService;

import java.io.IOException;
import java.util.List;

public class GetViewProductCommand implements Command {

    private static final ProductService productService = ProductService.getInstance();
    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductEntity productEntity = productService.getProductById(Integer.parseInt(req.getParameter(Attribute.ID))).get();
        List<CategoryEntity> categories = categoryService.getAllCategories();
        req.setAttribute(Attribute.PRODUCT_VIEW, productEntity);
        req.setAttribute(Attribute.CATEGORIES, categories);
        req.setAttribute("view", true);
        return Page.VIEW_PRODUCT;
    }
}
