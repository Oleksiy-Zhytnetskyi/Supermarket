package zlagoda.zlagoda.controller.command.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteCategoryCommand implements Command {

    private final CategoryService categoryService;

    public DeleteCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer categoryId = Integer.parseInt(req.getParameter(Attribute.ID));

        categoryService.deleteCategory(categoryId);

        redirectToAllUsersPageWithSuccessMessage(req, resp);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllUsersPageWithSuccessMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(req, resp);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CATEGORY_DELETE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CATEGORIES, urlParams);
    }
}
