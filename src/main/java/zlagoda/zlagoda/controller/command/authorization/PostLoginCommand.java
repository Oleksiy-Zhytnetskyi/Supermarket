package zlagoda.zlagoda.controller.command.authorization;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.controller.utils.SessionManager;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.UserService;
import zlagoda.zlagoda.validator.entity.CredentialsViewValidator;
import zlagoda.zlagoda.view.CredentialsView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PostLoginCommand implements Command {

    private final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(SessionManager.getInstance().isUserLoggedIn(session)) {
            RedirectionManager.getInstance().redirect(new HttpWrapper(req, resp), ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        CredentialsView credentialsView = buildCredentialView(req);
        List<String> errors = validateUserInput(credentialsView);

        if(!errors.isEmpty()) {
            addRequestAttributes(req, credentialsView, errors);
            return Page.LOGIN_VIEW;
        }

        Optional<UserEntity> user = userService.getUserByCredentials(credentialsView);
        if (user.isPresent()) {
            SessionManager.getInstance().addUserToSession(session, user.get());
            RedirectionManager.getInstance().redirect(new HttpWrapper(req, resp), ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        errors.add(Message.INVALID_CREDENTIALS);

        addRequestAttributes(req, credentialsView, errors);
        return Page.LOGIN_VIEW;
    }


    private CredentialsView buildCredentialView(HttpServletRequest req) {
        return new CredentialsView(req.getParameter(Attribute.EMAIL), req.getParameter(Attribute.PASSWORD));
    }

    private List<String> validateUserInput(CredentialsView view) {
        return CredentialsViewValidator.getInstance().validate(view);
    }

    private void addRequestAttributes(HttpServletRequest req, CredentialsView view, List<String> errors) {
        req.setAttribute(Attribute.LOGIN_USER, view);
        req.setAttribute(Attribute.ERRORS, errors);
    }
}
