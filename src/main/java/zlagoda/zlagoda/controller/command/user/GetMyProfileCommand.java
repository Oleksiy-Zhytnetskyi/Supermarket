package zlagoda.zlagoda.controller.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.UserEntity;

import java.io.IOException;

public class GetMyProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity result = (UserEntity)req.getSession().getAttribute(Attribute.USER);
        req.setAttribute(Attribute.USER_VIEW, result);
        return Page.MY_PROFILE;
    }
}
