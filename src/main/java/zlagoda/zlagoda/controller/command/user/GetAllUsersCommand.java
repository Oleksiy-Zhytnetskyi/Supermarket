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
import java.util.List;

public class GetAllUsersCommand implements Command {

    private final UserService userService;

    public GetAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserEntity> users = userService.getAllUsers();

        req.setAttribute(Attribute.USERS, users);
        return Page.ALL_USERS;
    }
}
