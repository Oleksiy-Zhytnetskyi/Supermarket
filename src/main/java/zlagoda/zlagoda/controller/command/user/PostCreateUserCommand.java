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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostCreateUserCommand implements Command {

    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserView userView = getUserInput(req);
        List<String> errors = validateUserInput(userView);
        checkEmailTaken(userView, errors);

        if (errors.isEmpty()) {
            userService.createUser(userView);
            redirectToAllUsersPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, userView, errors);
        return Page.VIEW_USER;
    }

    private void checkEmailTaken(UserView view, List<String> errors) {
        if (UserService.getInstance().getUserByEmail(view.getEmail()).isPresent()) {
            errors.add(Message.EMAIL_TAKEN_ERROR);
        }
    }

    private UserView getUserInput(HttpServletRequest req) {
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

    private void redirectToAllUsersPageWithSuccessMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(req, resp);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_USER_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_USERS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest req, UserView userView, List<String> errors) {
        req.setAttribute(Attribute.USER_VIEW, userView);
        req.setAttribute(Attribute.ERRORS, errors);
        req.setAttribute("create", true);
    }
}
