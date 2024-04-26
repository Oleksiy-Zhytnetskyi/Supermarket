package zlagoda.zlagoda.controller.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;

public class GetViewUserCommand implements Command {

    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity result = userService.getUserByEmail(req.getParameter(Attribute.EMAIL)).get();
        System.out.println(result);
        req.setAttribute(Attribute.USER_VIEW, result);
        req.setAttribute("view", true);
        return Page.VIEW_USER;
    }
}
