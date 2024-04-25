package zlagoda.zlagoda.controller.command.category;

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
import zlagoda.zlagoda.validator.entity.CategoryViewValidator;
import zlagoda.zlagoda.view.CategoryView;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostCreateCategoryCommand implements Command {

    private final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        CategoryView categoryView = getUserInput(req);
        List<String> errors = validateUserInput(categoryView);

        if (errors.isEmpty()) {
            categoryService.createCategory(categoryView);
            redirectToAllCategoryPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, categoryView, errors);
        return Page.VIEW_CATEGORY;
    }

    private CategoryView getUserInput(HttpServletRequest req) throws ParseException {
        return CategoryView.builder()
                .name(req.getParameter(Attribute.NAME))
                .build();
    }

    private List<String> validateUserInput(CategoryView category) {
        return CategoryViewValidator.getInstance().validate(category);
    }

    private void redirectToAllCategoryPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CATEGORY_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CATEGORIES, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, CategoryView categoryView, List<String> errors) {
        request.setAttribute(Attribute.CATEGORY_VIEW, categoryView);
        request.setAttribute(Attribute.ERRORS, errors);
        request.setAttribute("update", true);
    }
}


