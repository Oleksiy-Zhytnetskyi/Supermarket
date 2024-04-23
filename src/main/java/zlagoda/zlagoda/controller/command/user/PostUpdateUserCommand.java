package zlagoda.zlagoda.controller.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.UserService;
import zlagoda.zlagoda.validator.entity.UserViewValidator;
import zlagoda.zlagoda.view.UserView;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateUserCommand implements Command {

    private final UserService userService;

    public PostUpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        UserView userView = getUserInput(req);
        List<String> errors = validateUserInput(userView);

        if (errors.isEmpty()) {
            userService.updateUser(userView);
            redirectToAllUsersPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, userView, errors);
        return Page.VIEW_USER;
    }

    private UserView getUserInput(HttpServletRequest req) throws ParseException {
        UserView.UserViewBuilder userViewBuilder = UserView.builder()
                .name(req.getParameter(Attribute.NAME))
                .surname(req.getParameter(Attribute.SURNAME))
                .patronymic(req.getParameter(Attribute.PATRONYMIC))
                .phone(req.getParameter(Attribute.PHONE))
                .city(req.getParameter(Attribute.CITY))
                .street(req.getParameter(Attribute.STREET))
                .zipCode(req.getParameter(Attribute.ZIP_CODE))
                .email(req.getParameter(Attribute.EMAIL))
                .password(req.getParameter(Attribute.PASSWORD));
        if (!req.getParameter(Attribute.ROLE).equals("Choose a role")) {
            userViewBuilder.role(UserRole.valueOf(req.getParameter(Attribute.ROLE)));
        }
        if (!req.getParameter(Attribute.SALARY).isBlank()) {
            userViewBuilder.salary(req.getParameter(Attribute.SALARY));
        }
        if (!req.getParameter(Attribute.DATE_OF_BIRTH).isBlank()) {
            userViewBuilder.dateOfBirth(LocalDate.parse(req.getParameter(Attribute.DATE_OF_BIRTH)));
        }
        if (!req.getParameter(Attribute.START_DATE).isBlank()) {
            userViewBuilder.startDate(LocalDate.parse(req.getParameter(Attribute.START_DATE)));
        }
        return userViewBuilder.build();
    }

    private List<String> validateUserInput(UserView user) {
        return UserViewValidator.getInstance().validate(user);
    }

    private void redirectToAllUsersPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_USER_UPDATE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_USERS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, UserView userView, List<String> errors) {
        request.setAttribute(Attribute.USER_VIEW, userView);
        request.setAttribute(Attribute.ERRORS, errors);
        request.setAttribute("update", true);
    }
}
