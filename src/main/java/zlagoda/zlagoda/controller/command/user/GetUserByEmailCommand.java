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

public class GetUserByEmailCommand implements Command {

    private final UserService userService;

    public GetUserByEmailCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity result = (UserEntity)req.getSession().getAttribute(Attribute.USER);
        System.out.println(result);
        req.setAttribute(Attribute.USER_DTO, result);
        return Page.MY_PROFILE;
    }
}
