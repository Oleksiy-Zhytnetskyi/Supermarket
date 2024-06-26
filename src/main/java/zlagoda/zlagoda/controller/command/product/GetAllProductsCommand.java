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

public class GetAllProductsCommand implements Command {

    private static final ProductService productService = ProductService.getInstance();
    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductEntity> products = productService.getAllProducts();
        List<CategoryEntity> categories = categoryService.getAllCategories();
        req.setAttribute(Attribute.CATEGORIES, categories);
        req.setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_PRODUCTS;
    }
}
