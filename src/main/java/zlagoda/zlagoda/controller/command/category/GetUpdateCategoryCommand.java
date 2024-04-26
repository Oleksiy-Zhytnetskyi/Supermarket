package zlagoda.zlagoda.controller.command.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.service.CategoryService;

import java.io.IOException;

public class GetUpdateCategoryCommand implements Command {

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryEntity result = categoryService.getCategoryById(Integer.valueOf(req.getParameter(Attribute.ID))).get();
        req.setAttribute(Attribute.CATEGORY_VIEW, result);
        req.setAttribute("update", true);
        return Page.VIEW_CATEGORY;
    }
}
