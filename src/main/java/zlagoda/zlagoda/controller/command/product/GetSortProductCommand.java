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
import java.util.ArrayList;
import java.util.List;

public class GetSortProductCommand implements Command {

    private static final ProductService productService = ProductService.getInstance();
    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductEntity> products = new ArrayList<>();
        List<CategoryEntity> categories = categoryService.getAllCategories();

        String name = req.getParameter(Attribute.PRODUCTS_NAME);
        String category = req.getParameter(Attribute.PRODUCTS_CATEGORY);

        productService.getAllProducts().stream()
                        .forEach(
                                productEntity -> {
                                    if(productEntity.getName().toLowerCase().contains(name.toLowerCase())) {
                                        if (!category.equals("ALL")) {
                                            if (productEntity.getCategoryId() == Integer.valueOf(category)) {
                                                products.add(productEntity);
                                            }
                                        } else {
                                            products.add(productEntity);
                                        }
                                    }
                                }
                        );
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute(Attribute.CATEGORIES, categories);
        req.setAttribute(Attribute.PRODUCTS_NAME, name);
        req.setAttribute(Attribute.PRODUCTS_CATEGORY, category);
        return Page.ALL_PRODUCTS;
    }
}
