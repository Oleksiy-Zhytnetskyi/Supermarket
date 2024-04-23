package zlagoda.zlagoda.controller.command.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;
import java.util.List;

public class GetAllCategoryCommand implements Command {

    private final CategoryService categoryService;

    public GetAllCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        req.setAttribute(Attribute.CATEGORIES, categories);
        return Page.ALL_CATEGORIES;
    }
}