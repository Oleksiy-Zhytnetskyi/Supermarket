package zlagoda.zlagoda.controller.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.ProductService;

import java.io.IOException;
import java.util.List;

public class GetCreateProductCommand implements Command {

    private ProductService productService;
    private final CategoryService categoryService;

    public GetCreateProductCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        req.setAttribute("create", true);
        return Page.VIEW_PRODUCT;
    }
}
